package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.InsertImageApiService;
import io.swagger.api.factories.InsertImageApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Data;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/insertImage")


@io.swagger.annotations.Api(description = "the insertImage API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class InsertImageApi  {
   private final InsertImageApiService delegate = InsertImageApiServiceFactory.getInsertImageApi();

    @POST
    
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Adds the given image to de database", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Image inserted successfully", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Image couldn't be inserted", response = void.class) })
    public Response insertImagePost(
        @ApiParam(value = "Image data" ,required=true) Data data,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.insertImagePost(data,securityContext);
    }
}
