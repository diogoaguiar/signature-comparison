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
	static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

	private Mat templ;
	private Mat img;
	
	/**
	 * Constructor for local images.
	 * 
	 * @param clientSgnatureImagePath - path for the client's signature image
	 * @param checkImagePath - path for the check's image
	 * */
	public Comparison(String clientSgnatureImagePath, String checkImagePath) {
		templ = Imgcodecs.imread(clientSgnatureImagePath);
		img = Imgcodecs.imread(checkImagePath);
		
		//Check if images were successfully loaded
		if(templ.empty() || img.empty()) {
			Error.report("Couldn't load file.");
		}
	}
	
	/**
	 * Constructor for buffered images.
	 * 
	 * @param clientSgnatureImage - image with the client's signature image
	 * @param checkImage - image with the check's image
	 * */
	public Comparison(BufferedImage clientSgnatureImage, BufferedImage checkImage) {
		templ = new Mat();
		templ.put(0, 0, ((DataBufferByte)clientSgnatureImage.getRaster().getDataBuffer()).getData());
		//img = Imgcodecs.imread(checkImage);
	}
	
	public void compare(String inFile, String templateFile) {
		compare(inFile, templateFile, false, false);
	}

	public void compare(String inFile, String templateFile, boolean drawKP, boolean drawFilteredMatches) {

		//Import images
		Mat img = Imgcodecs.imread(inFile);
		Mat templ = Imgcodecs.imread(templateFile);

		//Convert to gray
		Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);
		Imgproc.cvtColor(templ, templ, Imgproc.COLOR_RGB2GRAY);

		//Keypoints
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.BRISK);
		final MatOfKeyPoint imgKP = new MatOfKeyPoint();
		final MatOfKeyPoint templKP = new MatOfKeyPoint();

		fd.detect(img, imgKP);
		fd.detect(templ, templKP);

		System.out.println("Image KeyPoints: " + imgKP.size());
		System.out.println("Template KeyPoints: " + templKP.size());

		//Draw keypoints
		if(drawKP) {
			Mat imgKPDraw = img.clone();
			Mat templKPDraw = templ.clone();

			Features2d.drawKeypoints(img, imgKP, imgKPDraw);
			Features2d.drawKeypoints(templ, templKP, templKPDraw);
			
			new ImageProcessor().show(imgKPDraw);
			new ImageProcessor().show(templKPDraw);

			try {
				System.in.read();
			} catch (Exception e) {
				
			}
		}
		
		//Descriptors
		Mat imgDescriptors = new Mat();
		Mat templDescriptors = new Mat();

		DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
		extractor.compute(img, imgKP, imgDescriptors);
		extractor.compute(templ, templKP, templDescriptors);

		System.out.println("Image Descriptors: " + imgDescriptors.size());
		System.out.println("Template Descriptors: " + templDescriptors.size());

		//Descriptors matches
		MatOfDMatch matches = new MatOfDMatch();

		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT);
		matcher.match(imgDescriptors, templDescriptors, matches);

		System.out.println("Num of matches: " + matches.size());

		//Best matches
		List<DMatch> matchesList = matches.toList();
		List<DMatch> bestMatches = new ArrayList<DMatch>();
		int numMatches = 4817;
		float limit = 0;
		
		Collections.sort(matchesList, new Comparator<DMatch>(){
			@Override
			public int compare(DMatch a, DMatch b) {
				return (a.distance < b.distance) ? -1 : (a.distance == b.distance) ? 0 : 1;
			}
		});
		
		//System.out.println(matchesList.toString());
		
		for(int i = 0; bestMatches.size() < numMatches; i++) {
			float dist = matchesList.get(i).distance;
			
			if(dist <= limit) {
				bestMatches.add(matchesList.get(i));
			} else {
				System.out.println("Not enought matches withing the threshold. (" + i + ")");
				return;
			}
		}

	    System.out.println("Filtered matches: " + bestMatches.size());
	    System.out.println(bestMatches.toString());
	    
	    System.out.println("Match found");

		if(drawFilteredMatches) {
			MatOfDMatch matchesFiltered = new MatOfDMatch();
			matchesFiltered.fromList(bestMatches);
			
			Mat outImage = new Mat();

			Features2d.drawMatches(img, imgKP, templ, templKP, matchesFiltered, outImage);
			
			new ImageProcessor().show(outImage);

			try {
				System.in.read();
			} catch (Exception e) {
				
			}
		}
	}
}
