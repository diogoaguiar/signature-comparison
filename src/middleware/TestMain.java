package middleware;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

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
	}
}
