package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GetChecksListApiService;
import io.swagger.api.factories.GetChecksListApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.InlineResponse2003;

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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class GetChecksListApi  {
   private final GetChecksListApiService delegate = GetChecksListApiServiceFactory.getGetChecksListApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Gets a list of all the images names from the collection checks", response = InlineResponse2003.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = InlineResponse2003.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Couldn't retrive checks list", response = InlineResponse2003.class) })
    public Response getChecksListGet(
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getChecksListGet(securityContext);
    }
}
