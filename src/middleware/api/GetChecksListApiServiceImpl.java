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

public class GetChecksListApiServiceImpl {
	public Response getChecksListGet(SecurityContext securityContext) throws NotFoundException {
		// DB connection
		DBManager dbm = new DBManager();
		
		if(!dbm.isConnected()) {
			Logger.error("Not connected to the database");
			return Response.serverError().build();
		}
		
		List<Document> checkList = dbm.getCheckList();
		
		List<Document> checkListFiltered = new ArrayList<Document>();
		for(Document d : checkList) {
			Document tempDoc = new Document();
			tempDoc.put("name", d.get("name"));
			checkListFiltered.add(tempDoc);
		}
		Document doc = new Document();
		doc.put("data", checkListFiltered);
		
		return Response.ok(doc.toJson()).build();
	}
}
