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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-28T17:19:59.707Z")
public class GetCheckSignaturesListApiServiceImpl extends GetCheckSignaturesListApiService {
    
    @Override
    public Response getCheckSignaturesListGet(SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return new middleware.api.GetCheckSignaturesListApiServiceImpl().getCheckSignaturesListGet(securityContext);
    }
    
}
