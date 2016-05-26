package io.swagger.api.factories;

import io.swagger.api.UpdateAlgorithmParamsApiService;
import io.swagger.api.impl.UpdateAlgorithmParamsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class UpdateAlgorithmParamsApiServiceFactory {

   private final static UpdateAlgorithmParamsApiService service = new UpdateAlgorithmParamsApiServiceImpl();

   public static UpdateAlgorithmParamsApiService getUpdateAlgorithmParamsApi()
   {
      return service;
   }
}
