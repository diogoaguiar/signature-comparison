package middleware;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.Properties;

public class Logger {
	public static final String ERROR_LOG = getPath() + "error.log";
	public static final String PROP_FILE = "config.properties";
	
	private static String getPath() {
		String path = "";
		InputStream inputStream;
		try {
			Properties prop = new Properties();
 
			inputStream = Logger.class.getClassLoader().getResourceAsStream(PROP_FILE);
 
			if (inputStream != null) {
				prop.load(inputStream);
				path = prop.getProperty("log_path"); // Get the property values
			} else {
				System.out.println("Property file '" + PROP_FILE + "' not found. Logger properties not imported.");
			}
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Couldn't import log path from '" + PROP_FILE + "'. Default path will be used.");
		}
		
		path = path.replace("/", "\\");
		if(!path.endsWith("\\")) {
			path += "\\";
		}
		
		return path;
	}

	public static void error(String description) {
		System.out.println(description);
		try {
		FileWriter outStream = new FileWriter(ERROR_LOG, true);
		BufferedWriter bW = new BufferedWriter(outStream);
		PrintWriter out = new PrintWriter(bW);
		
		GregorianCalendar now = new GregorianCalendar();
		
		String entry = formatDate(now) + " " + description;
		out.println(entry);
		out.close();
		} catch(IOException e) {
			System.out.println("Couldn't write to logger.");
			e.printStackTrace();
		}
	}
	
	private static String formatDate(GregorianCalendar date) {
		String dmy = "" + date.get(GregorianCalendar.DAY_OF_MONTH) + "/" + date.get(GregorianCalendar.MONTH) + "/" + date.get(GregorianCalendar.YEAR);
		String hms = "" + formatNumber(date.get(GregorianCalendar.HOUR_OF_DAY)) + ":" + formatNumber(date.get(GregorianCalendar.MINUTE)) + ":" + formatNumber(date.get(GregorianCalendar.SECOND));
		
		return "[" + dmy + " " + hms + "]";
	}
	
	private static String formatNumber(int num) {
		if(num < 10 && num >= 0) {
			return "0" + num;
		} else {
			return "" + num;
		}
	}
	
	public static void error(Exception e) {
		error(e.getMessage());
		System.out.println("Stacktrace:");
		e.printStackTrace();
	}

	public static void msg(String msg) {
		System.out.println(msg);
	}
}
