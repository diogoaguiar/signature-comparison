package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GetAlgorithmParamsApiService;
import io.swagger.api.factories.GetAlgorithmParamsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.InlineResponse2002;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/getAlgorithmParams")


@io.swagger.annotations.Api(description = "the getAlgorithmParams API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class GetAlgorithmParamsApi  {
   private final GetAlgorithmParamsApiService delegate = GetAlgorithmParamsApiServiceFactory.getGetAlgorithmParamsApi();

    @GET
    
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Gets the algorithm parameters", response = InlineResponse2002.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = InlineResponse2002.class),
        @io.swagger.annotations.ApiResponse(code = 500, message = "Couldn't retrive algoritms list", response = InlineResponse2002.class) })
    public Response getAlgorithmParamsGet(
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAlgorithmParamsGet(securityContext);
    }
}
