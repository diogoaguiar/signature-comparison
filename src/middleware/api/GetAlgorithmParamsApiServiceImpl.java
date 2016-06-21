package middleware.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.InlineResponse2002;
import middleware.DBManager;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class GetAlgorithmParamsApiServiceImpl extends GetAlgorithmParamsApiService {
    
    @Override
    public Response getAlgorithmParamsGet(SecurityContext securityContext)
    throws NotFoundException {
    	DBManager dbm = new DBManager();
    	Document response = dbm.getConfig("featureMatching");
    	response.remove("_id");
    	response.remove("algorithm");
        return Response.ok(response.toJson()).build();
    }
}
