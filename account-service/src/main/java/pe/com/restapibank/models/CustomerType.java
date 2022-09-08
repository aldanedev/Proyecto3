package pe.com.restapibank.models;

public enum CustomerType {
  PERSONAL, BUSINESS, PERSONAL_VIP, BUSINESS_PYME;

  static public boolean isValid(String type) {
    for (CustomerType customerType : CustomerType.values()) {
      if (customerType.name().equals(type)) {
        return true;
      }
    }
    return false;
  }
}
