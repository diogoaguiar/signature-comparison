package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GetImageApiService;
import io.swagger.api.factories.GetImageApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.InlineResponse2004;

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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class GetImageApi  {
   private final GetImageApiService delegate = GetImageApiServiceFactory.getGetImageApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Retrieves the image specified as a base64 string", response = InlineResponse2004.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = InlineResponse2004.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Couldn't retrive the resquested image", response = InlineResponse2004.class) })
    public Response getImageGet(
        @ApiParam(value = "Type of image (client's signature or check)",required=true, allowableValues="client, check") @QueryParam("type") String type,
        @ApiParam(value = "Name of the image to be returned",required=true) @QueryParam("name") String name,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getImageGet(type,name,securityContext);
    }
}
