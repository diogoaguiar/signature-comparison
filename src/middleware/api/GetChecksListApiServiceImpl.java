package middleware.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

import com.mongodb.util.JSON;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import middleware.DBManager;

public class GetChecksListApiServiceImpl {
	public Response getCheckSignaturesListGet(SecurityContext securityContext) throws NotFoundException {
		DBManager dbm = new DBManager();

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
