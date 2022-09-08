package pe.com.restapibank.client;

import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, feign.Response response) {

    switch (response.status()) {
      case 404: {
        System.out.println(methodKey);
        throw new NotFoundException("Not found");
      }
      default:
        return new Exception("Generic Error");
    }
  }

}
