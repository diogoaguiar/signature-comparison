package middleware.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import middleware.Comparison;
import middleware.DBManager;
import middleware.Img;

public class CompareApiServiceImpl {
	public Response compareGet(String clientSignatureImageName, String checkImageName,
			SecurityContext securityContext) throws NotFoundException {
		
		DBManager dbm = new DBManager();
		Document signDoc = dbm.getImage("signatures", clientSignatureImageName);
		Document checkDoc = dbm.getImage("checks", checkImageName);

		Img signImg = new Img((String) signDoc.get("image"));
		Img checkImg = new Img((String) checkDoc.get("image"));

		Comparison comp;
		comp = new Comparison(signImg, checkImg);
		
		double score = comp.featureMatching();
		
		Document response = new Document();
		response.put("verdict", 1);
		response.put("score", score);
		
		return Response.ok(response.toJson()).build();
	}
}
