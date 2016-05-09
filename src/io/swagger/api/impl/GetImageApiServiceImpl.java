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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-09T01:24:47.522Z")
public class GetImageApiServiceImpl extends GetImageApiService {
    
    @Override
    public Response getImageGet(String type, String name, SecurityContext securityContext)
    throws NotFoundException {
        return new middleware.api.GetImageApiServiceImpl().getSignatureImageGet(type, name, securityContext);
    }
    
}
