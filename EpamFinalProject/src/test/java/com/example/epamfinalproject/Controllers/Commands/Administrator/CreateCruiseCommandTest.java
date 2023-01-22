package com.example.epamfinalproject.Controllers.Commands.Administrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Database.Interfaces.RouteDAO;
import com.example.epamfinalproject.Database.Interfaces.ShipDAO;
import com.example.epamfinalproject.Database.Interfaces.StaffDAO;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Entities.Staff;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Services.StaffService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import java.util.*;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

class CreateCruiseCommandTest {
  private static HttpServletRequest request;
  private static HttpSession session;
  private static Command command;
  private static Cruise cruise;
  private static ShipService shipService;
  private static RouteService routeService;

  @BeforeAll
  static void beforeAll() {
    session = mock(HttpSession.class);
    request = mock(HttpServletRequest.class);
    CruiseDAO cruiseDAO = mock(CruiseDAO.class);
    ShipDAO shipDAO = mock(ShipDAO.class);
    RouteDAO routeDAO = mock(RouteDAO.class);
    StaffDAO staffDAO = mock(StaffDAO.class);
    cruise = new Cruise(new Ship(), new Route(), null, 0, null, null);
    command =
        new CreateCruiseCommand(
            new CruiseService(cruiseDAO),
            routeService = new RouteService(routeDAO),
            shipService = new ShipService(shipDAO),
            new StaffService(staffDAO));
  }

  @Test
  void executeValidTest() {
    Ship ship = new Ship("Valid name", 1);
    Route route = new Route(1, "Departure", "Destination", 10);

    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getParameter("ship_name")).thenReturn("Valid name");
    when(request.getParameter(FieldKey.PASSENGER_CAPACITY)).thenReturn("1");
    when(shipService.getShipByName(ship.getName())).thenReturn(null);

    when(request.getParameter(FieldKey.DEPARTURE)).thenReturn("Departure");
    when(request.getParameter(FieldKey.DESTINATION)).thenReturn("Destination");
    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn("10");
    when(routeService.getRouteByAllParameters(route)).thenReturn(null);
    //
    for (int i = 1; i <= Constants.STAFF_NUMBER; i++) {
      when(request.getParameter(FieldKey.FIRST_NAME + i)).thenReturn("Name");
      when(request.getParameter(FieldKey.LAST_NAME + i)).thenReturn("Name");
    }
    when(request.getParameter(FieldKey.CRUISE_NAME)).thenReturn("Name");
    when(request.getParameter(FieldKey.CRUISE_PRICE)).thenReturn("10");
    when(request.getParameter(FieldKey.CRUISE_LEAVING)).thenReturn("2020-10-12");
    when(request.getParameter(FieldKey.CRUISE_ARRIVING)).thenReturn("2020-10-12");

    //    Mockito.when(shipService.getShipByName(ship.getName())).thenReturn(new Ship(1, "Valid
    // name", 1));
    //    Mockito.when(routeService.getRouteByAllParameters(route))
    //        .thenReturn(new Route("D", "D", 10));

    assertEquals(Constants.REDIRECT + Path.CREATE_CRUISE_PAGE, command.execute(request));
  }

  @Test
  void executeInvalidShipTest() {
    Ship ship = new Ship("", 0);

    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getParameter("ship_name")).thenReturn(ship.getName());
    when(request.getParameter(FieldKey.PASSENGER_CAPACITY))
        .thenReturn(String.valueOf(ship.getPassengerCapacity()));

    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
  }

  @Test
  void executeInvalidRouteTest() {
    Ship ship = new Ship("", 0);
    Route route = new Route(1, "", "", 10);

    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getParameter("ship_name")).thenReturn("Valid name");
    when(request.getParameter(FieldKey.PASSENGER_CAPACITY)).thenReturn("1");
    when(shipService.getShipByName(ship.getName())).thenReturn(null);

    when(request.getParameter(FieldKey.DEPARTURE)).thenReturn(route.getDeparture());
    when(request.getParameter(FieldKey.DESTINATION)).thenReturn(route.getDestination());
    when(request.getParameter(FieldKey.TRANSIT_TIME))
        .thenReturn(String.valueOf(route.getTransitTime()));

    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("invalidRouteData")
  void createRouteInvalidTest(String departure, String destination) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);

    when(request.getParameter(FieldKey.DEPARTURE)).thenReturn(departure);
    when(request.getParameter(FieldKey.DESTINATION)).thenReturn(destination);
    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn("1");

//    assertNull(command.createRoute(request));
  }

  @ParameterizedTest
  @MethodSource("validRouteData")
  void createRouteValidTest(String departure, String destination) {
    Route route = new Route(departure, destination, 1);

    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);

    when(request.getParameter(FieldKey.DEPARTURE)).thenReturn(route.getDeparture());
    when(request.getParameter(FieldKey.DESTINATION)).thenReturn(route.getDestination());
    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn("1");
    when(routeService.getRouteByAllParameters(route)).thenReturn(null);

//    assertTrue(new ReflectionEquals(route, "id").matches(command.createRoute(request)));
  }


  static Stream<Arguments> invalidNameData() {
    return Stream.of(
        Arguments.of("Invalid_value"),
        Arguments.of("invalid value"),
        Arguments.of("invalid Value"),
        Arguments.of(" "),
        Arguments.of("Invalid=Value"),
        Arguments.of(" Invalid2232value"));
  }

  static Stream<Arguments> validNameData() {
    return Stream.of(
        Arguments.of("Valid-value"),
        Arguments.of("Valid Value"),
        Arguments.of("Valid"),
        Arguments.of("Very valid Value"),
        Arguments.of("Very-valid-Value"));
  }

  static Stream<Arguments> validRouteData() {
    return Stream.of(
        Arguments.of("Valid-value", "Valid-value"),
        Arguments.of("Valid Value", "Valid Value"),
        Arguments.of("Valid", "Valid"),
        Arguments.of("Very valid Value", "Very valid Value"),
        Arguments.of("Very-valid-Value", "Very-valid-Value"));
  }

  static Stream<Arguments> invalidRouteData() {
    return Stream.of(
        Arguments.of("Valid-value", "Invalid_value"), // invalid destination value
        Arguments.of("Invalid2232value", "Valid Value"), // invalid departure value
        Arguments.of("invalid Value", "invalidValue"), // invalid both values
        Arguments.of(" ", "Invalid=Value") // invalid both values
        );
  }

  static Stream<Arguments> validStaffData() {
    return Stream.of(Arguments.of("Name"), Arguments.of("Valid"));
  }

  static Stream<Arguments> invalidStaffData() {
    return Stream.of(
        Arguments.of("invalid Value"),
        Arguments.of("Very-invalid-Value"),
        Arguments.of("Invalid_value"));
  }
}
