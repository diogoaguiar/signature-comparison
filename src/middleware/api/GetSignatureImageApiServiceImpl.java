package middleware.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;

public class GetSignatureImageApiServiceImpl {
	public Response getSignatureImageGet(String type, Integer id, SecurityContext securityContext)
		    throws NotFoundException {
		        // do some magic!
		        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
		    }
}
