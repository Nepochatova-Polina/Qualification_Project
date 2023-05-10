package org.acme;

import io.vertx.mutiny.sqlclient.Row;

public class TestResponse {
  private  String firstname;
  private  String lastname;

  public TestResponse(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }
  public TestResponse() {}

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public static TestResponse from(Row row) {
    return new TestResponse(row.getString("firstname"),row.getString("lastname"));
  }
}
