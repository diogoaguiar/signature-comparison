package middleware.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.GetImageApi;
import io.swagger.api.NotFoundException;
import middleware.DBManager;

public class GetImageApiServiceImpl {
	public Response getImageGet(String type, String name, SecurityContext securityContext) throws NotFoundException {
		DBManager dbm = new DBManager();
		Document doc;
		switch(type) {
		case "client":
			doc = dbm.getImage("signatures", name);
			break;
		case "check":
			doc = dbm.getImage("checks", name);
			break;
		default:
			return Response.serverError().build();
		}
		return Response.ok(doc.toJson()).build();
	}
}
