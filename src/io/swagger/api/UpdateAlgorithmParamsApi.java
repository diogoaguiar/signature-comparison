package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UpdateAlgorithmParamsApiService;
import io.swagger.api.factories.UpdateAlgorithmParamsApiServiceFactory;

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

@Path("/updateAlgorithmParams")


@io.swagger.annotations.Api(description = "the updateAlgorithmParams API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class UpdateAlgorithmParamsApi  {
   private final UpdateAlgorithmParamsApiService delegate = UpdateAlgorithmParamsApiServiceFactory.getUpdateAlgorithmParamsApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Updates the algorithm parameters", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Update successful", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Couldn't update the configuration", response = void.class) })
    public Response updateAlgorithmParamsGet(
        @ApiParam(value = "Maximum width size for the comparing images",required=true) @QueryParam("width") Integer width,
        @ApiParam(value = "Maximum height size for the comparing images",required=true) @QueryParam("height") Integer height,
        @ApiParam(value = "Threshold for image comparison",required=true) @QueryParam("threshold") Integer threshold,
        @ApiParam(value = "Number of matches needed to be a matching image",required=true) @QueryParam("minMatches") Integer minMatches,
        @ApiParam(value = "Percentage of success that minNumMatches is equivalent to",required=true) @QueryParam("minMatchesPercent") Double minMatchesPercent,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateAlgorithmParamsGet(width,height,threshold,minMatches,minMatchesPercent,securityContext);
    }
}
