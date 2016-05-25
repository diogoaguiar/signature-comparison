package io.swagger.api.factories;

import io.swagger.api.CheckIfExistsApiService;
import io.swagger.api.impl.CheckIfExistsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class CheckIfExistsApiServiceFactory {

   private final static CheckIfExistsApiService service = new CheckIfExistsApiServiceImpl();

   public static CheckIfExistsApiService getCheckIfExistsApi()
   {
      return service;
   }
}
