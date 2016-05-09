package middleware.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;

public class CompareApiServiceImpl {
	public Response compareGet(String clientSignatureImageName, String checkImageName,
			SecurityContext securityContext) {
		
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
	}
}
