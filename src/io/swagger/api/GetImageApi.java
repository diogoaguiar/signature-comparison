package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GetImageApiService;
import io.swagger.api.factories.GetImageApiServiceFactory;

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

@Path("/getImage")


@io.swagger.annotations.Api(description = "the getImage API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-11T22:40:50.677Z")
public class GetImageApi  {
   private final GetImageApiService delegate = GetImageApiServiceFactory.getGetImageApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = String.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Returns the requested image.", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid **type** passed. Type must be either *client* or *check*.", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 501, message = "Image missing from database.", response = String.class) })
    public Response getImageGet(
        @ApiParam(value = "Type of image. (client's signature or check)",required=true, allowableValues="client, check") @QueryParam("type") String type,
        @ApiParam(value = "Name of the image to be returned",required=true) @QueryParam("name") String name,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getImageGet(type,name,securityContext);
    }
}
