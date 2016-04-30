package io.swagger.api.factories;

import io.swagger.api.GetSignatureImageApiService;
import io.swagger.api.impl.GetSignatureImageApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-28T17:19:59.707Z")
public class GetSignatureImageApiServiceFactory {

   private final static GetSignatureImageApiService service = new GetSignatureImageApiServiceImpl();

   public static GetSignatureImageApiService getGetSignatureImageApi()
   {
      return service;
   }
}
