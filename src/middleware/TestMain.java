package middleware;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

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
	static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

	public static void main(String[] args) {
		//comparisonTest();
	}
	
	public static void comparisonTest() {
		String rootPath = "C:\\Users\\Diogo\\Desktop\\img\\";
		String signaturePath = rootPath + "sig1a.jpg";
		String checkPath = rootPath + "sig2aa.jpg";
		Comparison comp = new Comparison(signaturePath, checkPath);
		comp.compare();
	}
}
