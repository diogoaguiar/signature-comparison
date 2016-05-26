package middleware.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import middleware.Comparison;
import middleware.ComparisonResult;
import middleware.DBManager;
import middleware.Img;
import middleware.Logger;

public class CompareApiServiceImpl {
	public Response compareGet(String clientSignatureImageName, String checkImageName, SecurityContext securityContext)
			throws NotFoundException {
		try {
			// Connect to the DB
			DBManager dbm = new DBManager();
			if (!dbm.isConnected()) { // Check if connected successfully
				Logger.error("Couldn't connect to the database.");
				return Response.serverError().build();
			}

			// Get images to compare from DB
			Document signDoc = dbm.getImage("signatures", clientSignatureImageName);
			Document checkDoc = dbm.getImage("checks", checkImageName);
			if (signDoc.isEmpty() || checkDoc.isEmpty()) { // Check if retrived
															// images
															// successfully
				Logger.error("Couldn't retrive image for comparison from the database.");
				return Response.serverError().build();
			}
			dbm.close(); // Close DB connection

			// Comparison
			Img signImg = new Img((String) signDoc.get("image")); // Get image
																	// from
																	// Document
			Img checkImg = new Img((String) checkDoc.get("image"));

			Comparison comp = new Comparison(signImg, checkImg);
			ComparisonResult result = comp.featureMatching(); // Compare images

			// Return response
			return Response.ok(result.toJson()).build();
		} catch (Exception e) {
			Logger.error(e);
			return Response.serverError().build();
		}
	}
}
