package middleware.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import middleware.DBManager;

public class GetImageApiServiceImpl {
	public Response getSignatureImageGet(String type, String name, SecurityContext securityContext) {
		DBManager dbm = new DBManager("localhost", 27017, "scm_db");
		String response = "";
		switch(type) {
		case "client":
			//imageData = dbm.getImage("signatures", name);
			response = dbm.getImage("test", name);
			break;
		case "check":
			//imageData = dbm.getImage("checks", name);
			response = dbm.getImage("test", name);
			break;
		default:
			return Response.serverError().build();
		}
		
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, response)).build();
	}
}
