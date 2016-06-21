package middleware;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class SCMServlet extends HttpServlet {

	private static final long serialVersionUID = 5011983518809923948L;
	
	private static SCMServlet instance;
	
	// Config file variables
	private String ip;
	private int port;
	private String database;
	private String credentialDB;
	private String user;
	private String password;
	private String logPath;
	private String opencvDllName;
	
	// Database variables
	private MongoClient mc;
	private MongoDatabase db;
	private boolean isConnected;
	
	public SCMServlet() {}
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		instance = this;
		
		// Config
		getPropValues();
		
		// Database
		connectToDatabase();
	}

	@Override
	public void destroy() {
		super.destroy();
		
		if(isConnected) {
			mc.close();
			isConnected = false;
		}
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
			logPath = prop.getProperty("log_path");
			opencvDllName = prop.getProperty("opencv_dll_name");
			credentialDB = prop.getProperty("credential_db");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
			File opencvDll = new File(opencvDllName);
			if(!opencvDll.isAbsolute()) {
				String separator = "";
				if(!opencvDllName.startsWith("/") && !opencvDllName.startsWith("\\")) {
					separator = "/";
				}
				String absolutePath = getServletContext().getRealPath("/WEB-INF/lib/opencv_310" + separator + opencvDllName);
				opencvDllName = absolutePath;
				//System.out.println(opencvDllName);
			}

			inputStream.close();
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	/**
	 * Connect to the database.
	 */
	private void connectToDatabase() {
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
	private boolean testConnection() {
		boolean response;
		MongoClientOptions options = MongoClientOptions.builder().serverSelectionTimeout(3000).build();
		if(user.isEmpty() || credentialDB.isEmpty() || password.isEmpty()) {
	    	Logger.msg("Connecting to database without credentials.");
	    	mc = new MongoClient(new ServerAddress(ip, port), options);
		} else {
	    	Logger.msg("Connecting to database with credentials.");
			MongoCredential credential = MongoCredential.createMongoCRCredential(user, credentialDB, password.toCharArray());
	    	mc = new MongoClient(new ServerAddress(ip, port), Collections.singletonList(credential), options);
		}
			
		try {
			mc.getAddress();
	    	Logger.msg("Connected to database successfully.");
			response = true;
		} catch (Exception e) {
			Logger.error("Couldn't connect to the database.");
			response = false;
		}
		//mc.close();
		
		return response;
	}

	public static SCMServlet getInstance() {
		return instance;
	}

	public String getLogPath() {
		return logPath;
	}

	public String getOpencvDllName() {
		return opencvDllName;
	}

	public MongoClient getMc() {
		return mc;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public MongoDatabase getDb() {
		return db;
	}
}
