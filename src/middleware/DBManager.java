package middleware;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBManager {

	private MongoClient mc;
	private MongoDatabase db;
	
	/**
	 * Constructor with DB connection
	 * @param serverIP
	 * @param port
	 * @param dbName
	 */
	public DBManager(String serverIP, int port, String dbName) {
		connect(serverIP, port, dbName);
	}
	
	/**
	 * Simple constructor
	 */
	public DBManager() {}

	/**
	 * Connect to the database.
	 */
	public void connect(String serverIP, int port, String dbName) {
		mc = new MongoClient(serverIP, port);
		db = mc.getDatabase(dbName);
	}

	/**
	 * Insert an image into de database as a Base64 string.
	 * @param collection
	 * @param image
	 * @param name
	 */
	public void insertImage(String collection, BufferedImage image, String name) {
		//Create DB document
		Document doc = new Document();
		
		//Add image
		String base64String = ImageProcessor.toBase64String(image);		//Encode to base 64 string
		doc.put("image", base64String);									//Add to document
		
		//Add name
		doc.put("name", name);
		
		//Add empty fields
		doc.put("match_image", "");
		doc.put("match_score", "");
		
		//Insert into DB collection
		MongoCollection<Document> col = db.getCollection(collection);
		col.insertOne(doc);
	}
	
	public String getImage(String collection, String name) {
		//Get document from DB
		Document query = new Document();
		query.put("name", name);
	    Document doc = (db.getCollection(collection)).find(query).first();
	    
	    //Get image from document
	    String base64String = (String)doc.get("image");
	    return base64String;
	}

	/**
	 * Get the image (BufferedImage) of a check from the database.
	 * @param id
	 * @return Check as image (BufferedImage)
	 */
	public BufferedImage getCheck(int id) {
		return new BufferedImage(0, 0, 0);
	}

	/**
	 * Get the image (BufferedImage) of a client's signature from the database.
	 * 
	 * @param id client's signature image ID
	 * @return Client's signature as image (BufferedImage)
	 */
	public BufferedImage getClientSignature(int id) {
		
		
		return new BufferedImage(0, 0, 0);
	}
	
	public List<String> getCheckList() {
		FindIterable<Document> results = db.getCollection("checks").find();
		final ArrayList<String> resultList = new ArrayList<String>();
		results.forEach(new Block<Document>() {

			@Override
			public void apply(final Document doc) {
				resultList.add((String) doc.get("name"));
			}
			
		});
		
		return resultList;
	}
	
	public List<String> getSignatureList() {
		FindIterable<Document> results = db.getCollection("signatures").find();
		final ArrayList<String> resultList = new ArrayList<String>();
		results.forEach(new Block<Document>() {

			@Override
			public void apply(final Document doc) {
				resultList.add((String) doc.get("name"));
			}
			
		});
		
		return resultList;
	}
}
