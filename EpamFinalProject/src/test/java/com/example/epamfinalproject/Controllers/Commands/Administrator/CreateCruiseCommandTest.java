package com.example.epamfinalproject.Controllers.Commands.Administrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Services.StaffService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

//  @ParameterizedTest
//  @MethodSource("validCruiseData")
//  void executeValidTest(Cruise cruise1) {
//
//    when(request.getSession()).thenReturn(session);
//
//    when(request.getParameter("ship_name")).thenReturn(cruise1.getShip().getName());
//    when(request.getParameter(FieldKey.PASSENGER_CAPACITY)).thenReturn(String.valueOf(cruise1.getShip().getPassengerCapacity()));
//    when(shipService.getShipByName(any(String.class))).thenReturn(null);
//
//    when(request.getParameter(FieldKey.DEPARTURE)).thenReturn(cruise1.getRoute().getDeparture());
//    when(request.getParameter(FieldKey.DESTINATION)).thenReturn(cruise1.getRoute().getDestination());
//    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn(String.valueOf(cruise1.getRoute().getTransitTime()));
//    when(routeService.getRouteByAllParameters(any(Route.class))).thenReturn(null);
//
//    for (int i = 1; i <= Constants.STAFF_NUMBER; i++) {
//      when(request.getParameter(FieldKey.FIRST_NAME + i)).thenReturn("Name");
//      when(request.getParameter(FieldKey.LAST_NAME + i)).thenReturn("Name");
//    }
//    when(request.getParameter(FieldKey.CRUISE_NAME)).thenReturn(cruise1.getName());
//    when(request.getParameter(FieldKey.CRUISE_PRICE)).thenReturn(String.valueOf(cruise1.getPrice()));
//    when(request.getParameter(FieldKey.CRUISE_LEAVING)).thenReturn(String.valueOf(cruise1.getStartOfTheCruise()));
//    when(request.getParameter(FieldKey.CRUISE_ARRIVING)).thenReturn(String.valueOf(cruise1.getEndOfTheCruise()));
//
////    when(shipService.getShipByName(any(String.class))).thenReturn(cruise1.getShip());
////    when(routeService.getRouteByAllParameters(any(Route.class))).thenReturn(cruise1.getRoute());
//
//    assertEquals(Constants.REDIRECT + Path.CREATE_CRUISE_PAGE, command.execute(request));
//  }

  @ParameterizedTest
  @MethodSource("invalidNameData")
  void executeInvalidShipTest(String name) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);

    when(request.getParameter("ship_name")).thenReturn(name);
    when(request.getParameter(FieldKey.PASSENGER_CAPACITY)).thenReturn("0");

    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("invalidRouteData")
  void executeInvalidRouteTest(String departure, String destination) {

    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getParameter("ship_name")).thenReturn("Valid name");
    when(request.getParameter(FieldKey.PASSENGER_CAPACITY)).thenReturn("1");
    when(shipService.getShipByName(any(String.class))).thenReturn(new Ship());

    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn("10");

    // Invalid Departure
    when(request.getParameter(FieldKey.DEPARTURE)).thenReturn(departure);
    when(request.getParameter(FieldKey.DESTINATION)).thenReturn(destination);
    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("invalidStaffData")
  void executeInvalidStaffTest(String name, String surname) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);

    when(request.getParameter("ship_name")).thenReturn("Valid name");
    when(request.getParameter(FieldKey.PASSENGER_CAPACITY)).thenReturn("1");
    when(shipService.getShipByName(any(String.class))).thenReturn(new Ship());

    when(request.getParameter(FieldKey.DEPARTURE)).thenReturn("Departure");
    when(request.getParameter(FieldKey.DESTINATION)).thenReturn("Destination");
    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn("0");
    when(routeService.getRouteByAllParameters(any(Route.class))).thenReturn(new Route());
    for (int i = 0; i < Constants.STAFF_NUMBER; i++) {
      when(request.getParameter(FieldKey.FIRST_NAME + i)).thenReturn(name);
      when(request.getParameter(FieldKey.LAST_NAME + i)).thenReturn(surname);
    }
    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
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
        Arguments.of("Valid", "Invalid_value"), // invalid destination value
        Arguments.of("Invalid2232value", "Valid Value"), // invalid departure value
        Arguments.of("invalid Value", "invalidValue"), // invalid both values
        Arguments.of(" ", "Invalid=Value") // invalid both values
        );
  }

  static Stream<Arguments> invalidStaffData() {
    return Stream.of(
        Arguments.of("invalid", "Value"),
        Arguments.of("Very-invalid-Value", " "),
        Arguments.of("", "Invalid_value"));
  }

  static Stream<Arguments> validCruiseData() {
    return Stream.of(
        Arguments.of(
            new Cruise(
                new Ship(1, "Valid name", 1),
                new Route(1, "Departure", "Destination", 10),
                "Name",
                100,
                LocalDate.parse("2020-12-12"),
                LocalDate.parse("2020-12-15"))),
        Arguments.of(
            new Cruise(
                new Ship(1, "Valid name", 1),
                new Route(1, "Valid-Departure", "Valid-destination", 10),
                "Very valid Name",
                100,
                LocalDate.parse("2020-01-02"),
                LocalDate.parse("2020-01-05"))));
  }
}
