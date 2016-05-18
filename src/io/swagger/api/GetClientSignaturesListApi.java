package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GetClientSignaturesListApiService;
import io.swagger.api.factories.GetClientSignaturesListApiServiceFactory;

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

@Path("/getClientSignaturesList")


@io.swagger.annotations.Api(description = "the getClientSignaturesList API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-11T22:40:50.677Z")
public class GetClientSignaturesListApi  {
   private final GetClientSignaturesListApiService delegate = GetClientSignaturesListApiServiceFactory.getGetClientSignaturesListApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = String.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Couldn't connect to the database", response = String.class) })
    public Response getClientSignaturesListGet(
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getClientSignaturesListGet(securityContext);
    }
}
