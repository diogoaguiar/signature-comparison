package middleware;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	private String ip;
	private int port;
	private String database;

	private MongoClient mc;
	private MongoDatabase db;

	/**
	 * Simple constructor
	 */
	public DBManager() {
		getPropValues();
		connect();
	}

	/**
	 * Connect to the database.
	 */
	public void connect() {
		mc = new MongoClient(ip, port);
		db = mc.getDatabase(database);
	}
	
	private void getPropValues() {
		InputStream inputStream;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				Logger.error("property file '" + propFileName + "' not found in the classpath");
			}
 
			// Get the property values
			ip = prop.getProperty("ip");
			System.out.println(ip);
			port = Integer.parseInt(prop.getProperty("port"));
			System.out.println(""+port);
			database = prop.getProperty("database");
			System.out.println(database);
			
			inputStream.close();
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}

	/**
	 * Insert an image into de database as a Base64 string.
	 * 
	 * @param collection
	 * @param path
	 * @param name
	 */
	public void insertImage(String collection, Img image, String name) {
		try {
			// Create DB document
			Document doc = new Document();

			// Add image
			String base64String = image.getBase64(); // Encode to base 64 string
			doc.put("image", base64String); // Add to document

			// Add name
			doc.put("name", name);

			// Add empty fields
			doc.put("match_image", "");
			doc.put("match_score", "");

			// Insert into DB collection
			MongoCollection<Document> col = db.getCollection(collection);
			col.insertOne(doc);
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}

	}

	public Document getImage(String collection, String name) {
		// Get document from DB
		Document query = new Document();
		query.put("name", name);
		Document doc = (db.getCollection(collection)).find(query).first();

		return doc;
	}

	/**
	 * Get the image (BufferedImage) of a check from the database.
	 * 
	 * @param id
	 * @return Check as image (BufferedImage)
	 */
	public BufferedImage getCheck(int id) {
		return new BufferedImage(0, 0, 0);
	}

	/**
	 * Get the image (BufferedImage) of a client's signature from the database.
	 * 
	 * @param id
	 *            client's signature image ID
	 * @return Client's signature as image (BufferedImage)
	 */
	public BufferedImage getClientSignature(int id) {

		return new BufferedImage(0, 0, 0);
	}

	public List<Document> getCheckList() {
		FindIterable<Document> results = db.getCollection("checks").find();
		final ArrayList<Document> resultList = new ArrayList<Document>();
		results.forEach(new Block<Document>() {

			@Override
			public void apply(final Document doc) {
				resultList.add(doc);
			}

		});

		return resultList;
	}

	public List<Document> getSignatureList() {
		FindIterable<Document> results = db.getCollection("signatures").find();
		final ArrayList<Document> resultList = new ArrayList<Document>();
		results.forEach(new Block<Document>() {

			@Override
			public void apply(final Document doc) {
				resultList.add(doc);
			}

		});

		return resultList;
	}
}
