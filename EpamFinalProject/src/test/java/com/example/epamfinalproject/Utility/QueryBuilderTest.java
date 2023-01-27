package com.example.epamfinalproject.Utility;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QueryBuilderTest {
  private static HttpServletRequest request;
  private static HttpSession session;
  private static QueryBuilder builder;
  private static String cruiseQuery;
  private static String countQuery;

  @BeforeAll
  static void beforeAll() {
    request = mock(HttpServletRequest.class);
    session = mock(HttpSession.class);
    builder = new QueryBuilder();
    cruiseQuery =
        "select * from cruises "
            + "inner join routes r on r.id = cruises.route_id"
            + " inner join ships s on s.id = cruises.ship_id "
            + " where deleted = false";
    countQuery =
        "select count(cruises.id) from cruises inner join routes r on r.id = cruises.route_id"
            + " where deleted = false";
  }

  @ParameterizedTest
  @MethodSource("filterCruiseValues")
  void cruiseQueryBuilderTest(
      String leavingDate, String arrivingDate, String transitTime, String expectedResult) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute(FieldKey.CRUISE_LEAVING)).thenReturn(leavingDate);
    when(request.getSession().getAttribute(FieldKey.CRUISE_ARRIVING)).thenReturn(arrivingDate);
    when(request.getSession().getAttribute(FieldKey.TRANSIT_TIME)).thenReturn(transitTime);

    assertEquals(builder.cruiseQueryBuilder(request, 1, null), expectedResult);
  }

  @ParameterizedTest
  @MethodSource("filterCountValues")
  void cruiseCountQueryBuilderTest(
      String leavingDate, String arrivingDate, String transitTime, String expectedResult) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute(FieldKey.CRUISE_LEAVING)).thenReturn(leavingDate);
    when(request.getSession().getAttribute(FieldKey.CRUISE_ARRIVING)).thenReturn(arrivingDate);
    when(request.getSession().getAttribute(FieldKey.TRANSIT_TIME)).thenReturn(transitTime);

    assertEquals(builder.cruiseCountQueryBuilder(request, null), expectedResult);
  }

  @ParameterizedTest
  @MethodSource("filterCruiseAdminValues")
  void cruiseQueryBuilderAdminTest(
      String leavingDate, String arrivingDate, String transitTime, String expectedResult) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute(FieldKey.CRUISE_LEAVING)).thenReturn(leavingDate);
    when(request.getSession().getAttribute(FieldKey.CRUISE_ARRIVING)).thenReturn(arrivingDate);
    when(request.getSession().getAttribute(FieldKey.TRANSIT_TIME)).thenReturn(transitTime);

    assertEquals(
        builder.cruiseQueryBuilder(
            request, 1, new User.UserBuilder().role(UserRole.ADMINISTRATOR).build()),
        expectedResult);
  }

  @ParameterizedTest
  @MethodSource("filterCountAdminValues")
  void cruiseCountQueryBuilderAdminTest(
      String leavingDate, String arrivingDate, String transitTime, String expectedResult) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute(FieldKey.CRUISE_LEAVING)).thenReturn(leavingDate);
    when(request.getSession().getAttribute(FieldKey.CRUISE_ARRIVING)).thenReturn(arrivingDate);
    when(request.getSession().getAttribute(FieldKey.TRANSIT_TIME)).thenReturn(transitTime);

    assertEquals(builder.cruiseCountQueryBuilder(request,
            new User.UserBuilder().role(UserRole.ADMINISTRATOR).build()), expectedResult);
  }

  Stream<Arguments> filterCruiseValues() {
    return Stream.of(
        Arguments.of(
            "2022-10-10",
            null,
            null,
            cruiseQuery + " and start_date >= '2022-10-10' limit 6 offset 0"),
        Arguments.of(
            "",
            "2022-10-13",
            "",
            cruiseQuery + " and start_date >= now() and end_date <= '2022-10-13' limit 6 offset 0"),
        Arguments.of(
            "",
            "",
            "3",
            cruiseQuery + " and start_date >= now() and r.transit_time >= 3 limit 6 offset 0"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            cruiseQuery
                + " and start_date >= '2022-10-12' and end_date <= '2022-10-15' limit 6 offset 0"),
        Arguments.of(
            "2022-10-25",
            "2022-10-13",
            "4",
            cruiseQuery
                + " and start_date >= '2022-10-25' and end_date <= '2022-10-13' and"
                + " r.transit_time >= 4 limit 6 offset 0"),
        // Invalid data arguments
        Arguments.of(
            "2022-40-10", null, null, cruiseQuery + " and start_date >= now() limit 6 offset 0"),
        Arguments.of(
            "", "202-10-13", "", cruiseQuery + " and start_date >= now() limit 6 offset 0"),
        Arguments.of("", "", "-3", cruiseQuery + " and start_date >= now() limit 6 offset 0"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            cruiseQuery
                + " and start_date >= '2022-10-12' and end_date <= '2022-10-15' limit 6 offset 0"),
        Arguments.of(
            "200022-10-25",
            "2022-1-0-13",
            "4",
            cruiseQuery
                + " and start_date >= now() and"
                + " r.transit_time >= 4 limit 6 offset 0"));
  }

  Stream<Arguments> filterCountValues() {
    return Stream.of(
        Arguments.of("2022-10-10", null, null, countQuery + " and start_date >= '2022-10-10'"),
        Arguments.of(
            "",
            "2022-10-13",
            "",
            countQuery + " and start_date >= now() and end_date <= '2022-10-13'"),
        Arguments.of("", "", "3", countQuery + " and start_date >= now() and r.transit_time >= 3"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            countQuery + " and start_date >= '2022-10-12' and end_date <= '2022-10-15'"),
        Arguments.of(
            "2022-10-25",
            "2022-10-13",
            "4",
            countQuery
                + " and start_date >= '2022-10-25' and end_date <= '2022-10-13' and"
                + " r.transit_time >= 4"),
        // Invalid data arguments
        Arguments.of("2022-40-10", null, null, countQuery + " and start_date >= now()"),
        Arguments.of("", "202-10-13", "", countQuery + " and start_date >= now()"),
        Arguments.of("", "", "-3", countQuery + " and start_date >= now()"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            countQuery + " and start_date >= '2022-10-12' and end_date <= '2022-10-15'"),
        Arguments.of(
            "200022-10-25",
            "2022-1-0-13",
            "4",
            countQuery + " and start_date >= now() and" + " r.transit_time >= 4"));
  }

  Stream<Arguments> filterCruiseAdminValues() {
    return Stream.of(
        Arguments.of(
            "2022-10-10",
            null,
            null,
            cruiseQuery + " and start_date >= '2022-10-10' and confirmed = false limit 6 offset 0"),
        Arguments.of(
            "",
            "2022-10-13",
            "",
            cruiseQuery
                + " and confirmed = false and end_date <= '2022-10-13'"
                + " limit 6 offset 0"),
        Arguments.of(
            "",
            "",
            "3",
            cruiseQuery
                + " and confirmed = false and r.transit_time >= 3 limit 6"
                + " offset 0"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            cruiseQuery
                + " and start_date >= '2022-10-12' and confirmed = false and end_date <="
                + " '2022-10-15' limit 6 offset 0"),
        Arguments.of(
            "2022-10-25",
            "2022-10-13",
            "4",
            cruiseQuery
                + " and start_date >= '2022-10-25' and confirmed = false and end_date <="
                + " '2022-10-13' and r.transit_time >= 4 limit 6 offset 0"),
        // Invalid data arguments
        Arguments.of(
            "2022-40-10",
            null,
            null,
            cruiseQuery + " and confirmed = false limit 6 offset 0"),
        Arguments.of(
            "",
            "202-10-13",
            "",
            cruiseQuery + " and confirmed = false limit 6 offset 0"),
        Arguments.of(
            "",
            "",
            "-3",
            cruiseQuery + " and confirmed = false limit 6 offset 0"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            cruiseQuery
                + " and start_date >= '2022-10-12' and confirmed = false and end_date <="
                + " '2022-10-15' limit 6 offset 0"),
        Arguments.of(
            "200022-10-25",
            "2022-1-0-13",
            "4",
            cruiseQuery
                + " and confirmed = false and"
                + " r.transit_time >= 4 limit 6 offset 0"));
  }

  Stream<Arguments> filterCountAdminValues() {
    return Stream.of(
        Arguments.of(
            "2022-10-10",
            null,
            null,
            countQuery + " and start_date >= '2022-10-10' and confirmed = false"),
        Arguments.of(
            "",
            "2022-10-13",
            "",
            countQuery + " and confirmed = false and end_date <= '2022-10-13'"),
        Arguments.of(
            "",
            "",
            "3",
            countQuery + " and confirmed = false and r.transit_time >= 3"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            countQuery
                + " and start_date >= '2022-10-12' and confirmed = false and end_date <="
                + " '2022-10-15'"),
        Arguments.of(
            "2022-10-25",
            "2022-10-13",
            "4",
            countQuery
                + " and start_date >= '2022-10-25' and confirmed = false and end_date <="
                + " '2022-10-13' and r.transit_time >= 4"),
        // Invalid data arguments
        Arguments.of("2022-40-10", null, null, countQuery + " and confirmed = false"),
        Arguments.of("", "202-10-13", "", countQuery + " and confirmed = false"),
        Arguments.of("", "", "-3", countQuery + " and confirmed = false"),
        Arguments.of(
            "2022-10-12",
            "2022-10-15",
            "",
            countQuery
                + " and start_date >= '2022-10-12' and confirmed = false and end_date <="
                + " '2022-10-15'"),
        Arguments.of(
            "200022-10-25",
            "2022-1-0-13",
            "4",
            countQuery
                + " and confirmed = false and"
                + " r.transit_time >= 4"));
  }
}
