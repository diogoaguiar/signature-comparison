package io.swagger.api.factories;

import io.swagger.api.GetAlgorithmParamsApiService;
import io.swagger.api.impl.GetAlgorithmParamsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class GetAlgorithmParamsApiServiceFactory {

   private final static GetAlgorithmParamsApiService service = new GetAlgorithmParamsApiServiceImpl();

   public static GetAlgorithmParamsApiService getGetAlgorithmParamsApi()
   {
      return service;
   }
}
