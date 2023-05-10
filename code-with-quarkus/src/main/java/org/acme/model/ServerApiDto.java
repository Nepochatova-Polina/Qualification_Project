package org.acme.model;

public class ServerApiDto {

  private String firstname;

  private String lastname;

  public ServerApiDto(String firstname, String lastname ) {
    this.firstname = firstname;
    this.lastname = lastname;
  }
  public ServerApiDto() {}

  public String getFirstname() {
    return firstname;
  }
  public String getLastname() {
    return lastname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
}
