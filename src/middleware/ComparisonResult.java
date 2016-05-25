package middleware;

import org.bson.Document;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;

public class ComparisonResult {
	//Constants
	private final Img IMAGE_1, IMAGE_2;
	private final float THRESHOLD;
	private final int MIN_MATCHES;
	private final double MIN_MATCHES_PERCENT;
	
	//Variables
	private Mat queryImg, trainImg;
	private MatOfKeyPoint queryKP, trainKP;
	private Mat queryKPDraw, trainKPDraw;
	private Mat queryDescriptors, trainDescriptors;
	private MatOfDMatch matches, bestMatches;
	private Img resultImg;
	
	//Constructor
	public ComparisonResult(Img image1, Img image2, float threshold, int minNumMatches, double minMatchesPercent) {
		IMAGE_1 = image1;
		IMAGE_2 = image2;
		THRESHOLD = threshold;
		MIN_MATCHES = minNumMatches;
		MIN_MATCHES_PERCENT = minMatchesPercent;
	}
	
	// Methods
	public double calcMatchRate() {
		double matchRate = (((double) bestMatches.size().height / trainDescriptors.size().height) * 100);
		return matchRate;
	}
	
	public boolean calcVerdict() {
		if(bestMatches.size().height < MIN_MATCHES) {
			return false;
		} else {
			return true;
		}
	}
	
	private double calcScore() {
		double numBestMatches = bestMatches.size().height;
		double totalMatches = matches.size().height;
		double score;
		try {
			if(MIN_MATCHES_PERCENT < 100 && numBestMatches > MIN_MATCHES) {
				score = ((numBestMatches * (100 - MIN_MATCHES_PERCENT)) / totalMatches) + MIN_MATCHES_PERCENT;
			} else {
				score = (numBestMatches * MIN_MATCHES_PERCENT) / MIN_MATCHES;
			}
		} catch(Exception e) {
			score = -1;
		}
		return score;
	}
	
	public String toJson() {
		Document doc = new Document();

		doc.put("image1", IMAGE_1.getBase64());
		doc.put("image2", IMAGE_2.getBase64());
		doc.put("threshold", THRESHOLD);
		doc.put("minNumMatches", MIN_MATCHES);
		doc.put("minMatchesPercent", MIN_MATCHES_PERCENT);
		doc.put("queryImg", new Img(queryImg).getBase64());
		doc.put("trainImg", new Img(trainImg).getBase64());
		doc.put("queryKP", queryKP.size().height);
		doc.put("trainKP", trainKP.size().height);
		doc.put("queryKPDraw", new Img(queryKPDraw).getBase64());
		doc.put("trainKPDraw", new Img(trainKPDraw).getBase64());
		doc.put("queryDescriptors", queryDescriptors.size().height);
		doc.put("trainDescriptors", trainDescriptors.size().height);
		doc.put("matches", matches.size().height);
		doc.put("bestMatches", bestMatches.size().height);
		doc.put("resultImg", resultImg.getBase64());
		doc.put("matchRate", calcMatchRate());
		doc.put("verdict", calcVerdict());
		doc.put("score", calcScore());
		
		return doc.toJson();
	}

	// Getters and Setters
	public Mat getQueryImg() {
		return queryImg;
	}


	public void setQueryImg(Mat queryImg) {
		this.queryImg = queryImg;
	}


	public Mat getTrainImg() {
		return trainImg;
	}


	public void setTrainImg(Mat trainImg) {
		this.trainImg = trainImg;
	}


	public MatOfKeyPoint getQueryKP() {
		return queryKP;
	}


	public void setQueryKP(MatOfKeyPoint queryKP) {
		this.queryKP = queryKP;
	}


	public MatOfKeyPoint getTrainKP() {
		return trainKP;
	}


	public void setTrainKP(MatOfKeyPoint trainKP) {
		this.trainKP = trainKP;
	}


	public Mat getQueryKPDraw() {
		return queryKPDraw;
	}


	public void setQueryKPDraw(Mat queryKPDraw) {
		this.queryKPDraw = queryKPDraw;
	}


	public Mat getTrainKPDraw() {
		return trainKPDraw;
	}


	public void setTrainKPDraw(Mat trainKPDraw) {
		this.trainKPDraw = trainKPDraw;
	}


	public Mat getQueryDescriptors() {
		return queryDescriptors;
	}


	public void setQueryDescriptors(Mat queryDescriptors) {
		this.queryDescriptors = queryDescriptors;
	}


	public Mat getTrainDescriptors() {
		return trainDescriptors;
	}


	public void setTrainDescriptors(Mat trainDescriptors) {
		this.trainDescriptors = trainDescriptors;
	}


	public MatOfDMatch getMatches() {
		return matches;
	}


	public void setMatches(MatOfDMatch matches) {
		this.matches = matches;
	}


	public MatOfDMatch getBestMatches() {
		return bestMatches;
	}


	public void setBestMatches(MatOfDMatch bestMatches) {
		this.bestMatches = bestMatches;
	}


	public Img getResultImg() {
		return resultImg;
	}


	public void setResultImg(Img resultImg) {
		this.resultImg = resultImg;
	}


	public Img getImage1() {
		return IMAGE_1;
	}


	public Img getImage2() {
		return IMAGE_2;
	}


	public float getThreshold() {
		return THRESHOLD;
	}

	public int getMinNumMatches() {
		return MIN_MATCHES;
	}


}
