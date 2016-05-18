package middleware;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Logger {
	public static final String ERROR_LOG = "error.log";

	public static void error(String description) {
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
	}
}
