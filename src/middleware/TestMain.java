package middleware;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class TestMain {

	static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

	public static void main(String[] args) {
		String inFile = "C:\\Users\\Diogo\\Desktop\\imgs\\largesignature.jpg";
		String template = "C:\\Users\\Diogo\\Desktop\\imgs\\largesignature.jpg";
		
		Mat img = Imgcodecs.imread(inFile);
		BufferedImage bimg = ImageProcessor.toBufferedImage(img);
		img = ImageProcessor.toMat(bimg);
		new ImageProcessor().show(img);
		new ImageProcessor().show(bimg);
	}
}
