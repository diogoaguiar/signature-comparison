package middleware.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Data;
import io.swagger.model.Data.TypeEnum;
import middleware.DBManager;
import middleware.Img;
import middleware.Logger;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.SecurityContext;

import org.bson.Document;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class InsertImageApiServiceImpl extends InsertImageApiService {

	@Override
	public Response insertImagePost(Data data, SecurityContext securityContext) throws NotFoundException {
		try {
			DBManager dbm = new DBManager();
			if (!dbm.isConnected()) {
				Logger.error("Not connected to the database");
				return Response.serverError().build();
			}

			String collection = (data.getType().equals(TypeEnum.CHECK)) ? "checks" : "signatures";
			String imageBase64 = data.getImage();
			Img image = new Img(imageBase64);
			String name = data.getName();
			dbm.insertImage(collection, image, name);

			return Response.status(201).build();
		} catch (Exception e) {
			Logger.error(e);
			return Response.serverError().build();
		}
	}
}
