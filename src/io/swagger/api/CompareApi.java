package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CompareApiService;
import io.swagger.api.factories.CompareApiServiceFactory;

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

@Path("/compare")


@io.swagger.annotations.Api(description = "the compare API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-28T17:19:59.707Z")
public class CompareApi  {
   private final CompareApiService delegate = CompareApiServiceFactory.getCompareApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Integer.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Comparison was successful. The comparison score is returned.", response = Integer.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Comparison failed.", response = Integer.class) })
    public Response compareGet(
        @ApiParam(value = "ID of the client's signature to be compared",required=true) @QueryParam("clientSignatureID") Integer clientSignatureID,
        @ApiParam(value = "ID of the check's signature to be compared",required=true) @QueryParam("checkSignatureID") Integer checkSignatureID,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.compareGet(clientSignatureID,checkSignatureID,securityContext);
    }
}
