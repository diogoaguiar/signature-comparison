package io.swagger.api.factories;

import io.swagger.api.CompareApiService;
import io.swagger.api.impl.CompareApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-28T17:19:59.707Z")
public class CompareApiServiceFactory {

   private final static CompareApiService service = new CompareApiServiceImpl();

   public static CompareApiService getCompareApi()
   {
      return service;
   }
}
