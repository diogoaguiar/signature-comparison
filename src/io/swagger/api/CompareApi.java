package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CompareApiService;
import io.swagger.api.factories.CompareApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.ComparisonResponse;

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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-11T22:40:50.677Z")
public class CompareApi  {
   private final CompareApiService delegate = CompareApiServiceFactory.getCompareApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = ComparisonResponse.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Comparison was successful. The comparison score is returned.", response = ComparisonResponse.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Comparison failed.", response = ComparisonResponse.class) })
    public Response compareGet(
        @ApiParam(value = "Name of the client's signature image",required=true) @QueryParam("clientSignatureImageName") String clientSignatureImageName,
        @ApiParam(value = "Name of the check's image",required=true) @QueryParam("checkImageName") String checkImageName,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.compareGet(clientSignatureImageName,checkImageName,securityContext);
    }
}
