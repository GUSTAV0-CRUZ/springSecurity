package com.gustavo.cruzs.dev.springSecurity.entities.enums;

public enum RolesUserEnum {
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_COMMON("ROLE_COMMON");

  private final String authority;


  RolesUserEnum(String authority) {
    this.authority = authority;
  }

  public String getValue() {
    return authority;
  }
}
