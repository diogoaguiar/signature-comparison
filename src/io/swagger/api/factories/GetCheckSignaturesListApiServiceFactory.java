package io.swagger.api.factories;

import io.swagger.api.GetCheckSignaturesListApiService;
import io.swagger.api.impl.GetCheckSignaturesListApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-28T17:19:59.707Z")
public class GetCheckSignaturesListApiServiceFactory {

   private final static GetCheckSignaturesListApiService service = new GetCheckSignaturesListApiServiceImpl();

   public static GetCheckSignaturesListApiService getGetCheckSignaturesListApi()
   {
      return service;
   }
}
