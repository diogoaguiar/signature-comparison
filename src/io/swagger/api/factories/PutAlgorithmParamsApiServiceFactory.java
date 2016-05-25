package io.swagger.api.factories;

import io.swagger.api.PutAlgorithmParamsApiService;
import io.swagger.api.impl.PutAlgorithmParamsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class PutAlgorithmParamsApiServiceFactory {

   private final static PutAlgorithmParamsApiService service = new PutAlgorithmParamsApiServiceImpl();

   public static PutAlgorithmParamsApiService getPutAlgorithmParamsApi()
   {
      return service;
   }
}
