package pe.com.restapibank.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
  private String id;
  private String name;
  private CustomerType type;
}
