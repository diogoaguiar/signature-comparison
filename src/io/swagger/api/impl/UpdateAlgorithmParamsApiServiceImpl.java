package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;


import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class UpdateAlgorithmParamsApiServiceImpl extends UpdateAlgorithmParamsApiService {
    
    @Override
    public Response updateAlgorithmParamsGet(Integer width, Integer height, Integer threshold, Integer minMatches, Double minMatchesPercent, SecurityContext securityContext)
    throws NotFoundException {
        return new middleware.api.UpdateAlgorithmParamsApiServiceImpl().updateAlgorithmParamsGet(width, height, threshold, minMatches, minMatchesPercent, securityContext);
    }
    
}
