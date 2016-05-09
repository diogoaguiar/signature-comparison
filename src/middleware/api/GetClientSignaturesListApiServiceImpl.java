package middleware.api;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import middleware.DBManager;

public class GetClientSignaturesListApiServiceImpl {
	public Response getClientSignaturesListGet(SecurityContext securityContext) throws NotFoundException {

		DBManager dbm = new DBManager("localhost", 27017, "scm_db");
		
		List<String> checkList = dbm.getSignatureList();
		String response = "{";
		for(String s : checkList) {
			response += "{'name' : '" + s + "'}";
		}
		response += "}";
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, response)).build();
	}
}
