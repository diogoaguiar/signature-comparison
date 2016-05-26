package io.swagger.api.factories;

import io.swagger.api.GetImageApiService;
import io.swagger.api.impl.GetImageApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class GetImageApiServiceFactory {

   private final static GetImageApiService service = new GetImageApiServiceImpl();

   public static GetImageApiService getGetImageApi()
   {
      return service;
   }
}
