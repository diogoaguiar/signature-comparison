package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GetChecksListApiService;
import io.swagger.api.factories.GetChecksListApiServiceFactory;

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

@Path("/getChecksList")


@io.swagger.annotations.Api(description = "the getChecksList API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-09T02:54:52.433Z")
public class GetChecksListApi  {
   private final GetChecksListApiService delegate = GetChecksListApiServiceFactory.getGetChecksListApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = String.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = String.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Couldn't connect to the database", response = String.class, responseContainer = "List") })
    public Response getChecksListGet(
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getChecksListGet(securityContext);
    }
}
