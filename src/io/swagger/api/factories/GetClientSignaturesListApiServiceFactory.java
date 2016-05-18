package io.swagger.api.factories;

import io.swagger.api.GetClientSignaturesListApiService;
import io.swagger.api.impl.GetClientSignaturesListApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-11T22:40:50.677Z")
public class GetClientSignaturesListApiServiceFactory {

   private final static GetClientSignaturesListApiService service = new GetClientSignaturesListApiServiceImpl();

   public static GetClientSignaturesListApiService getGetClientSignaturesListApi()
   {
      return service;
   }
}
