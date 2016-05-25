package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.InlineResponse200;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class CheckIfExistsApiServiceImpl extends CheckIfExistsApiService {
    
    @Override
    public Response checkIfExistsGet(String name, String type, SecurityContext securityContext)
    throws NotFoundException {
        return new middleware.api.CheckIfExistsApiServiceImpl().checkIfExistsGet(name, type, securityContext);
    }
    
}
