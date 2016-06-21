package middleware.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import middleware.DBManager;
import middleware.Logger;

public class GetClientSignaturesListApiServiceImpl {
	public Response getClientSignaturesListGet(SecurityContext securityContext) throws NotFoundException {
		DBManager dbm = new DBManager();
		
		if(!dbm.isConnected()) {
			Logger.error("Not connected to the database");
			return Response.serverError().build();
		}
		
		List<Document> signatureList = dbm.getSignatureList();
		List<Document> signatureListFiltered = new ArrayList<Document>();
		for(Document d : signatureList) {
			Document tempDoc = new Document();
			tempDoc.put("name", d.get("name"));
			signatureListFiltered.add(tempDoc);
		}
		Document doc = new Document();
		doc.put("data", signatureListFiltered);
		
		return Response.ok(doc.toJson()).build();
	}
}
