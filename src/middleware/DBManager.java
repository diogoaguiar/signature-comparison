package middleware;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	private String ip;
	private int port;
	private String database;

	private MongoClient mc;
	private MongoDatabase db;
	private boolean isConnected;

	/**
	 * Constructor - Imports connection properties and connects to the database
	 * using those properties
	 */
	public DBManager() {
		getPropValues();
		connect();
	}

	/**
	 * Connect to the database.
	 */
	public void connect() {
		if(testConnection()) {
			mc = new MongoClient(ip, port);
			db = mc.getDatabase(database);
			isConnected = true;
		} else {
			isConnected = false;
		}
	}

	/**
	 * Checks if it's able to connect to the database
	 * 
	 * @return boolean
	 */
	public boolean testConnection() {
		boolean response;
		MongoClientOptions options = MongoClientOptions.builder().serverSelectionTimeout(3000).build();
		MongoClient testConnection = new MongoClient(new ServerAddress(ip, port), options);
		try {
			testConnection.getAddress();
			response = true;
		} catch (Exception e) {
			Logger.error("Couldn't connect to the database.");
			response = false;
		}
		testConnection.close();
		
		return response;
	}

	/**
	 * Import connection properties
	 */
	private void getPropValues() {
		InputStream inputStream;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				Logger.error("Property file '" + propFileName
						+ "' not found in the classpath. Connection properties not imported.");
				return;
			}

			// Get the property values
			ip = prop.getProperty("ip");
			port = Integer.parseInt(prop.getProperty("port"));
			database = prop.getProperty("database");

			inputStream.close();
		} catch (Exception e) {
			Logger.error(e);
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

			// Insert into DB collection
			MongoCollection<Document> col = db.getCollection(collection);
			col.insertOne(doc);
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	/**
	 * Gets an image Document from the database.
	 * 
	 * @param collection
	 * @param name
	 * @return Image's Document object
	 */
	public Document getImage(String collection, String name) {
		// Get document from DB
		Document query = new Document();
		query.put("name", name);
		Document doc = (db.getCollection(collection)).find(query).first();

		return doc;
	}

	/**
	 * Returns a list with the Documents from checks collection
	 * 
	 * @return List with the Documents from checks collection
	 */
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

	/**
	 * Returns the config Document for the given algorithm
	 * 
	 * @param algorithm
	 * @return Document with the configuration properties
	 */
	public Document getConfig(final String algorithm) {
		Document query = new Document();
		query.put("algorithm", algorithm);
		FindIterable<Document> results = db.getCollection("config").find(query);

		Document resultDoc = results.first();
		return resultDoc;
	}

	/**
	 * Returns a list with the Documents from signatures collection
	 * 
	 * @return List with the Documents from signatures collection
	 */
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
	
	public boolean checkIfExists(String collection, String name) {
		Document query = new Document();
		query.put("name", name);
		FindIterable<Document> results = db.getCollection(collection).find(query);
		if(results.first() == null) {
			return false;
		} else {
			return true;
		}
	}

	public void close() {
		isConnected = false;
		mc.close();
	}

	public boolean isConnected() {
		return isConnected;
	}
}
