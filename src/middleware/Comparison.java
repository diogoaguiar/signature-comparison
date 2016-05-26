package middleware;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;
import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Size;
import org.opencv.features2d.*;
import org.opencv.imgproc.Imgproc;

public class Comparison {
	// Load OpenCV
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	// Variables
	private Img img1; // Image 1
	private Img img2; // Image 2
	private float threshold; // Limit difference between descriptors to be
								// considered
	private int minNumMatches; // Number of best matches needed do have a matching image
	private double minMatchesPercent; // Score percentage corresponding to the minNumMatches value
	private boolean debugMode; // Prints debuging information and shows visuals

	private Size normMaxSize; // Maximum image size for comparison. Bigger images will be resized

	// Constructors and initializations
	/**
	 * Constructor for local images.
	 * 
	 * @param clientSgnatureImagePath
	 *            - path for the client's signature image
	 * @param checkImagePath
	 *            - path for the check's image
	 * @throws IOException 
	 */
	public Comparison(String image1, String image2) throws IOException {
		img1 = new Img();
		img1.setImageByPath(image1);
		img2 = new Img();
		img2.setImageByPath(image2);

		// Check if images were successfully loaded
		if (img1.empty() || img2.empty()) {
			Logger.error("Couldn't load file.");
		}

		init();
	}

	/**
	 * Constructor for Img images.
	 * 
	 * @param image1
	 *            - image with the signature
	 * @param image2
	 *            - image with the check
	 */
	public Comparison(Img image1, Img image2) {
		// Make a copy of the images
		img1 = image1.clone();
		img2 = image2.clone();

		init();
	}

	/**
	 * Initialize parameters and variables.
	 */
	private void init() {
		debugMode = false;
		
	}

	// Comparison methods
	/**
	 * Compare base image with the template with the default settings
	 */
	public ComparisonResult featureMatching() {
		// Import config from database
		DBManager dbm = new DBManager();
		Document configDoc = dbm.getConfig("featureMatching");
		normMaxSize = new Size(((Document)(configDoc.get("normMaxSize"))).getInteger("width"), ((Document)(configDoc.get("normMaxSize"))).getInteger("height"));
		threshold = configDoc.getInteger("threshold");
		minNumMatches = configDoc.getInteger("minNumMatches");
		minMatchesPercent = configDoc.getDouble("minMatchesPercent");
		dbm.close();
		
		// Initialize the resulting object
		ComparisonResult result = new ComparisonResult(img1.clone(), img2.clone(), threshold, minNumMatches, minMatchesPercent);
		
		// Normalization
		Mat normImg1 = normalize(img1);
		Mat normImg2 = normalize(img2);

		// Keypoints
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.BRISK);
		MatOfKeyPoint img1KP = new MatOfKeyPoint();
		MatOfKeyPoint img2KP = new MatOfKeyPoint();

		fd.detect(normImg1, img1KP);
		fd.detect(normImg2, img2KP);

		// Set query and train image
		Mat queryImg, trainImg;
		MatOfKeyPoint queryKP, trainKP;

		queryImg = normImg1;
		trainImg = normImg2;
		queryKP = img1KP;
		trainKP = img2KP;
		/*if (img2KP.size().height < img1KP.size().height) {
			queryImg = normImg2;
			trainImg = normImg1;
			queryKP = img2KP;
			trainKP = img1KP;
		} else {
			queryImg = normImg1;
			trainImg = normImg2;
			queryKP = img1KP;
			trainKP = img2KP;
		}*/
		result.setQueryImg(queryImg);
		result.setTrainImg(trainImg);
		result.setQueryKP(queryKP);
		result.setTrainKP(trainKP);

		if (debugMode) {
			System.out.println("QueryImage KeyPoints: " + queryKP.size());
			System.out.println("TrainImage KeyPoints: " + trainKP.size());
		}

		// Draw keypoints
		Mat queryKPDraw = queryImg.clone();
		Mat trainKPDraw = trainImg.clone();

		Features2d.drawKeypoints(queryImg, queryKP, queryKPDraw);
		Features2d.drawKeypoints(trainImg, trainKP, trainKPDraw);
		
		result.setQueryKPDraw(queryKPDraw);
		result.setTrainKPDraw(trainKPDraw);
		
		if (debugMode) {
			// Show Keypoints
			new Img(queryKPDraw).show();
			new Img(trainKPDraw).show();
		}

		// Descriptors
		Mat queryDescriptors = new Mat();
		Mat trainDescriptors = new Mat();

		DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
		extractor.compute(queryImg, queryKP, queryDescriptors);
		extractor.compute(trainImg, trainKP, trainDescriptors);
		
		result.setQueryDescriptors(queryDescriptors);
		result.setTrainDescriptors(trainDescriptors);

		if (debugMode) {
			System.out.println("Query Descriptors: " + queryDescriptors.size());
			System.out.println("Train Descriptors: " + trainDescriptors.size());
		}

		// Descriptors matches
		MatOfDMatch matches = new MatOfDMatch();

		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT);
		matcher.match(queryDescriptors, trainDescriptors, matches);
		
		result.setMatches(matches);

		if (debugMode) {
			System.out.println("Num of matches: " + matches.size());
		}

		// Best matches
		List<DMatch> matchesList = matches.toList();
		List<DMatch> bestMatches = new ArrayList<DMatch>();

		Collections.sort(matchesList, new Comparator<DMatch>() {
			@Override
			public int compare(DMatch a, DMatch b) {
				return (a.distance < b.distance) ? -1 : (a.distance == b.distance) ? 0 : 1;
			}
		});

		for (int i = 0; i < matchesList.size(); i++) {
			float dist = matchesList.get(i).distance;

			if (dist <= threshold) {
				bestMatches.add(matchesList.get(i));
			} else {
				break;
			}
		}
		
		MatOfDMatch matchesFiltered = new MatOfDMatch();
		matchesFiltered.fromList(bestMatches);
		result.setBestMatches(matchesFiltered);
		
		Mat outImage = new Mat();
		Features2d.drawMatches(queryImg, queryKP, trainImg, trainKP, matchesFiltered, outImage);
		result.setResultImg(new Img(outImage));
		
		if (debugMode) {
			System.out.println("Found " + bestMatches.size() + " matches from a total of "
					+ (int) trainDescriptors.size().height + " possibilities.\n" + "Match rate is of "
					+ result.calcMatchRate() + "%.");
		}

		if (debugMode && bestMatches.size() >= threshold) {
			System.out.println("Match found (" + bestMatches.size() + ")");
		}
		
		if (debugMode) {
			// Draw matches
			result.getResultImg().show();
		}

		return result;
	}

	// Settes and Getters
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

	// Auxiliar methods
	/**
	 * Prepare an image for comparison.
	 * 
	 * @param image
	 *            to be normalized (Mat)
	 */
	private Mat normalize(Img image) {
		// Resize
		if (image.getWidth() > normMaxSize.width) {
			image = image.resizeWidth((int) normMaxSize.width, true);
		}
		if (image.getHeight() > normMaxSize.height) {
			image = image.resizeHeight((int) normMaxSize.height, true);
		}

		// Convert to Mat
		Mat matImg = image.getMat();

		// Convert to gray scale
		Imgproc.cvtColor(matImg, matImg, Imgproc.COLOR_RGB2GRAY);

		return matImg;
	}

}
