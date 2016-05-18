package io.swagger.api.factories;

import io.swagger.api.GetChecksListApiService;
import io.swagger.api.impl.GetChecksListApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-11T22:40:50.677Z")
public class GetChecksListApiServiceFactory {

   private final static GetChecksListApiService service = new GetChecksListApiServiceImpl();

   public static GetChecksListApiService getGetChecksListApi()
   {
      return service;
   }
}
