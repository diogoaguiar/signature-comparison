package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.PutAlgorithmParamsApiService;
import io.swagger.api.factories.PutAlgorithmParamsApiServiceFactory;

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

@Path("/putAlgorithmParams")


@io.swagger.annotations.Api(description = "the putAlgorithmParams API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class PutAlgorithmParamsApi  {
   private final PutAlgorithmParamsApiService delegate = PutAlgorithmParamsApiServiceFactory.getPutAlgorithmParamsApi();

    @PUT
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Updates the algorithm parameters", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Update successful", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Couldn't update the configuration", response = void.class) })
    public Response putAlgorithmParamsPut(
        @ApiParam(value = "Maximum width size for the comparing images",required=true) @QueryParam("width") Integer width,
        @ApiParam(value = "Maximum height size for the comparing images",required=true) @QueryParam("height") Integer height,
        @ApiParam(value = "Threshold for image comparison",required=true) @QueryParam("threshold") Integer threshold,
        @ApiParam(value = "Number of matches needed to be a matching image",required=true) @QueryParam("minMatches") Integer minMatches,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.putAlgorithmParamsPut(width,height,threshold,minMatches,securityContext);
    }
}
