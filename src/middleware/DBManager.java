package middleware;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	private MongoClient mc;
	private MongoDatabase db;

	/**
	 * Constructor - Imports connection properties and connects to the database
	 * using those properties
	 */
	public DBManager() {
		mc = SCMServlet.getInstance().getMc();
		db = SCMServlet.getInstance().getDb();
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
			// Remove image with the same name
			Document query = new Document("name", name);
			db.getCollection(collection).findOneAndDelete(query);
			
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

	public void updateConfig(String algorithm, int width, int height, int threshold, int minNumMatches, double minNumPercent) {
		Document query = new Document();
		query.put("algorithm", algorithm);
		MongoCollection<Document> collection = db.getCollection("config");

		Document doc = new Document();
		doc.put("algorithm", algorithm);
		Document normMaxSize = new Document();
		normMaxSize.put("width", width);
		normMaxSize.put("height", height);
		doc.put("normMaxSize", normMaxSize);
		doc.put("threshold", threshold);
		doc.put("minNumMatches", minNumMatches);
		doc.put("minMatchesPercent", minNumPercent);

		collection.findOneAndReplace(query, doc);
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

	public boolean isConnected() {
		return SCMServlet.getInstance().isConnected();
	}
}
