package middleware.api;

import io.swagger.api.*;
import io.swagger.api.NotFoundException;
import middleware.DBManager;
import middleware.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T11:49:39.894Z")
public class CheckIfExistsApiServiceImpl extends CheckIfExistsApiService {
    
    @Override
    public Response checkIfExistsGet(String name, String type, SecurityContext securityContext)
    throws NotFoundException {

    	DBManager dbm = new DBManager();
    	
    	if(!dbm.isConnected()) {
    		Logger.error("Couldn't connect to the database.");
    		return Response.serverError().build();
    	}
    	String collection = (type.equals("check")) ? "checks" : "signatures";
    	boolean respValue = dbm.checkIfExists(collection, name);
    	dbm.close();
    	Document response = new Document();
    	response.put("exists", respValue);
        return Response.ok().entity(response.toJson()).build();
    }
    
}
