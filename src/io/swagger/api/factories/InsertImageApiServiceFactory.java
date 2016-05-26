package io.swagger.api.factories;

import io.swagger.api.InsertImageApiService;
import io.swagger.api.impl.InsertImageApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class InsertImageApiServiceFactory {

   private final static InsertImageApiService service = new InsertImageApiServiceImpl();

   public static InsertImageApiService getInsertImageApi()
   {
      return service;
   }
}
