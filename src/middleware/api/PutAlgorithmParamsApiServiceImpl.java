package middleware.api;

import io.swagger.api.*;
import io.swagger.model.*;
import middleware.DBManager;

import com.sun.jersey.multipart.FormDataParam;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class PutAlgorithmParamsApiServiceImpl extends PutAlgorithmParamsApiService {

	@Override
	public Response putAlgorithmParamsPut(Integer width, Integer height, Integer threshold, Integer minMatches, Double minMatchesPercent,
			SecurityContext securityContext) throws NotFoundException {
		DBManager dbm = new DBManager();

		if (!dbm.isConnected()) {
			return Response.serverError().build();
		}
		System.out.println(width);
		System.out.println(height);
		System.out.println(threshold);
		System.out.println(minMatches);
		System.out.println(minMatchesPercent);
		dbm.updateConfig("featureMatching", width, height, threshold, minMatches, minMatchesPercent);
		dbm.close();
		
		return Response.ok().build();
	}

}
