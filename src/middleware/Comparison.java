package middleware;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Size;
import org.opencv.features2d.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Comparison {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private Mat img1; // Image 1
	private Mat img2; // Image 2
	private float threshold; // Limit difference between descriptors to be
								// considered
	private boolean debugMode; // Prints debuging information and shows visuals

	// private Size normMinSize;
	private Size normMaxSize;

	/**
	 * Constructor for local images.
	 * 
	 * @param clientSgnatureImagePath
	 *            - path for the client's signature image
	 * @param checkImagePath
	 *            - path for the check's image
	 */
	public Comparison(String image1, String image2) {
		img1 = Imgcodecs.imread(image1);
		img2 = Imgcodecs.imread(image2);

		// Check if images were successfully loaded
		if (img1.empty() || img2.empty()) {
			Error.report("Couldn't load file.");
		}

		init();
	}

	/**
	 * Constructor for buffered images.
	 * 
	 * @param image1
	 *            - image with the client's signature image
	 * @param image2
	 *            - image with the check's image
	 */
	public Comparison(BufferedImage image1, BufferedImage image2) {
		// Convert images to Mat object
		img1 = ImageProcessor.toMat(image1);
		img2 = ImageProcessor.toMat(image2);

		init();
	}

	/**
	 * Initialize parameters and variables.
	 */
	private void init() {
		// normMinSize = new Size(200, 50);
		normMaxSize = new Size(800, 800);
		debugMode = true;
		threshold = 50;

		normalize(img1);
		normalize(img2);
	}

	/**
	 * Prepare an image for comparison.
	 * 
	 * @param image
	 *            to be normalized (Mat)
	 */
	public void normalize(Mat image) {
		// Convert to gray
		ImageProcessor.toGray(image);

		// Resize
		if (image.size().width > normMaxSize.width) {
			ImageProcessor.resizeWidth(image, normMaxSize.width, true);
		}
		if (image.size().height > normMaxSize.height) {
			ImageProcessor.resizeHeight(image, normMaxSize.height, true);
		}
	}

	/**
	 * Compare base image with the template with the default settings
	 */
	public double compare() {
		// Keypoints
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.BRISK);
		MatOfKeyPoint img1KP = new MatOfKeyPoint();
		MatOfKeyPoint img2KP = new MatOfKeyPoint();

		fd.detect(img1, img1KP);
		fd.detect(img2, img2KP);

		Mat queryImg, trainImg;
		MatOfKeyPoint queryKP, trainKP;

		if (img2KP.size().height > img1KP.size().height) {
			queryImg = img2;
			trainImg = img1;
			queryKP = img2KP;
			trainKP = img1KP;
		} else {
			queryImg = img1;
			trainImg = img2;
			queryKP = img1KP;
			trainKP = img2KP;
		}

		System.out.println("QueryImage KeyPoints: " + queryKP.size());
		System.out.println("TrainImage KeyPoints: " + trainKP.size());

		// Draw keypoints
		if (debugMode) {
			// Draw Keypoints
			Mat queryKPDraw = queryImg.clone();
			Mat trainKPDraw = trainImg.clone();

			Features2d.drawKeypoints(queryImg, queryKP, queryKPDraw);
			Features2d.drawKeypoints(trainImg, trainKP, trainKPDraw);

			new ImageProcessor().show(queryKPDraw);
			new ImageProcessor().show(trainKPDraw);
		}

		// Descriptors
		Mat queryDescriptors = new Mat();
		Mat trainDescriptors = new Mat();

		DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
		extractor.compute(queryImg, queryKP, queryDescriptors);
		extractor.compute(trainImg, trainKP, trainDescriptors);

		System.out.println("Query Descriptors: " + queryDescriptors.size());
		System.out.println("Train Descriptors: " + trainDescriptors.size());

		// Descriptors matches
		MatOfDMatch matches = new MatOfDMatch();

		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT);
		matcher.match(queryDescriptors, trainDescriptors, matches);

		System.out.println("Num of matches: " + matches.size());

		// Best matches
		List<DMatch> matchesList = matches.toList();
		List<DMatch> bestMatches = new ArrayList<DMatch>();

		Collections.sort(matchesList, new Comparator<DMatch>() {
			@Override
			public int compare(DMatch a, DMatch b) {
				return (a.distance < b.distance) ? -1 : (a.distance == b.distance) ? 0 : 1;
			}
		});

		// System.out.println(matchesList.toString());
		for (int i = 0; i < matchesList.size(); i++) {
			float dist = matchesList.get(i).distance;

			if (dist <= threshold) {
				bestMatches.add(matchesList.get(i));
			} else {
				//System.out.println("Not enought matches withing the threshold. (" + i + ")");
				break;
			}
		}
		System.out.println("Found " + bestMatches.size() + " matches from a total of " + (int)trainDescriptors.size().height + " possibilities.\n"
				+ "Match rate is of " + (float)(((double)bestMatches.size()/trainDescriptors.size().height)*100) + "%.");

		//System.out.println(bestMatches.toString());

		if (bestMatches.size() >= threshold) {
			System.out.println("Match found (" + bestMatches.size() + ")");
		}

		if (debugMode) {
			// Draw matches
			MatOfDMatch matchesFiltered = new MatOfDMatch();
			matchesFiltered.fromList(bestMatches);

			Mat outImage = new Mat();

			Features2d.drawMatches(queryImg, queryKP, trainImg, trainKP, matchesFiltered, outImage);

			new ImageProcessor().show(outImage);
		}

		return 0;
	}

	/*
	 * Settes and Getters
	 */
	public void setDebugMode(boolean mode) {
		debugMode = mode;
	}

	public boolean getDebugMode() {
		return debugMode;
	}

	public void setThreshold(int value) {
		threshold = value;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setCheckImage(String path) {
		img2 = Imgcodecs.imread(path);
	}

	public void setCheckImage(BufferedImage image) {
		img2 = ImageProcessor.toMat(image);
	}

	public void setSignatureImage(String path) {
		img1 = Imgcodecs.imread(path);
	}

	public void setSignatureImage(BufferedImage image) {
		img1 = ImageProcessor.toMat(image);
	}
}
