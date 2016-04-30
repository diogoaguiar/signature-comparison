package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GetSignatureImageApiService;
import io.swagger.api.factories.GetSignatureImageApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;


import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/getSignatureImage")


@io.swagger.annotations.Api(description = "the getSignatureImage API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-28T17:19:59.707Z")
public class GetSignatureImageApi  {
   private final GetSignatureImageApiService delegate = GetSignatureImageApiServiceFactory.getGetSignatureImageApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Integer.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Returns the image requeted", response = Integer.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Image not found", response = Integer.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid **type** passed. Type must be either *client* or *check*", response = Integer.class, responseContainer = "List") })
    public Response getSignatureImageGet(
        @ApiParam(value = "Type of image that the ID referes to. (signature of the client or signature on the check)",required=true, allowableValues="client, check") @QueryParam("type") String type,
        @ApiParam(value = "ID of the image to be returned",required=true) @QueryParam("id") Integer id,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSignatureImageGet(type,id,securityContext);
    }
}
