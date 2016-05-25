package middleware;

import org.opencv.core.Core;

public class TestMain {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		/*
		 * String path = "C:\\Users\\Diogo\\Desktop\\img\\mediumcheck.jpg"; Img
		 * temp = new Img(); temp.setImageByPath(path); Img test = new
		 * Img(temp.getMat()); test.show();
		 */
		comparisonTest();
	}

	public static void comparisonTest() {
		String rootPath = "C:\\Users\\Diogo\\Desktop\\img\\";
		String signaturePath = rootPath + "largesignature2.jpg";
		String checkPath = rootPath + "largecheck.jpg";
		Comparison comp = new Comparison(signaturePath, checkPath);
		comp.setDebugMode(true);
		comp.featureMatching();
	}

	public static void insertTest() {
		String path = "C:\\Users\\Diogo\\Desktop\\img\\largecheck.jpg";
		DBManager dbm = new DBManager();
		Img image = new Img();
		image.setImageByPath(path);
		dbm.insertImage("checks", image, "l-chek123");
		dbm.close();
	}
}
