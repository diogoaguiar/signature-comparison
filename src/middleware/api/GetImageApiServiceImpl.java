package middleware.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.GetImageApi;
import io.swagger.api.NotFoundException;
import middleware.DBManager;

public class GetImageApiServiceImpl {
	public Response getSignatureImageGet(String type, String name, SecurityContext securityContext) throws NotFoundException {
		DBManager dbm = new DBManager();
		String response = "[";
		switch(type) {
		case "client":
			return Response.ok(dbm.getImage("signatures", name).toJson()).build();
		case "check":
			return Response.ok(dbm.getImage("checks", name).toJson()).build();
		default:
			return Response.serverError().build();
		}
	}
}
