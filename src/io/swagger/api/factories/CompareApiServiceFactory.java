package io.swagger.api.factories;

import io.swagger.api.CompareApiService;
import io.swagger.api.impl.CompareApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-09T02:54:52.433Z")
public class CompareApiServiceFactory {

   private final static CompareApiService service = new CompareApiServiceImpl();

   public static CompareApiService getCompareApi()
   {
      return service;
   }
}
