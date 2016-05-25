package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CheckIfExistsApiService;
import io.swagger.api.factories.CheckIfExistsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.InlineResponse200;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/checkIfExists")


@io.swagger.annotations.Api(description = "the checkIfExists API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class CheckIfExistsApi  {
   private final CheckIfExistsApiService delegate = CheckIfExistsApiServiceFactory.getCheckIfExistsApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Verifies if there's already an image with the given name", response = InlineResponse200.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Resquest failed", response = InlineResponse200.class) })
    public Response checkIfExistsGet(
        @ApiParam(value = "Name of the image to be inserted",required=true) @QueryParam("name") String name,
        @ApiParam(value = "Type of image (client's signature or check)",required=true, allowableValues="client, check") @QueryParam("type") String type,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.checkIfExistsGet(name,type,securityContext);
    }
}
