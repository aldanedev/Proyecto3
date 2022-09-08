package pe.com.restapibank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.com.restapibank.models.CustomerResponse;

@FeignClient(name = "customerService", url = "http://localhost:8083", path = "/api/v1/customers", configuration = ClientConfiguration.class)
public interface CustomerClient {
  @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerResponse> get(@PathVariable String id);
}
