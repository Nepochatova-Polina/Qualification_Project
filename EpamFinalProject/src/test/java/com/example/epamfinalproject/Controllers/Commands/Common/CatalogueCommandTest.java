package com.example.epamfinalproject.Controllers.Commands.Common;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CatalogueCommandTest {

  private static HttpServletRequest request;
  private static HttpSession session;
  private static Command command;
  private static Cruise cruise;
  private static User userAdmin;
  private static User userClient;

  @BeforeAll
  static void beforeAll() {
    session = mock(HttpSession.class);
    request = mock(HttpServletRequest.class);
    userAdmin = new User.UserBuilder().role(UserRole.ADMINISTRATOR).build();
    userClient = new User.UserBuilder().role(UserRole.CLIENT).build();
    CruiseDAO cruiseDAO = mock(CruiseDAO.class);
    cruise = new Cruise(new Ship(), new Route(), null, 0, null, null);
    command = new CatalogueCommand(new CruiseService(cruiseDAO));
  }

  @ParameterizedTest
  @MethodSource("adminInvalidData")
  void executeInvalidPageAdminTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userAdmin);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + Path.MAIN_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("clientInvalidData")
  void executeInvalidPageClientTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userClient);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + Path.MAIN_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("adminValidData")
  void executeValidPageAdminTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userAdmin);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + path, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("clientValidData")
  void executeValidPageClientTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userClient);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + path, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("validFilterData")
  void executeValidFilterTest(String leavingDate, String arrivingDate, String transitTime) {
    session.setAttribute(FieldKey.CRUISE_LEAVING, leavingDate);
    session.setAttribute(FieldKey.CRUISE_ARRIVING, arrivingDate);
    session.setAttribute(FieldKey.TRANSIT_TIME, transitTime);

    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userClient);

    when(request.getParameter("page-path")).thenReturn("/test.jsp");
    when(request.getParameter("page")).thenReturn("1");

    assertEquals(Constants.REDIRECT + "/test.jsp", command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("invalidFilterData")
  void executeInvalidFilterTest(String leavingDate, String arrivingDate, String transitTime) {
    session.setAttribute(FieldKey.CRUISE_LEAVING, leavingDate);
    session.setAttribute(FieldKey.CRUISE_ARRIVING, arrivingDate);
    session.setAttribute(FieldKey.TRANSIT_TIME, transitTime);

    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userClient);

    when(request.getParameter("page-path")).thenReturn("/test.jsp");
    when(request.getParameter("page")).thenReturn("1");

    assertEquals(Constants.REDIRECT + "/test.jsp", command.execute(request));
  }

  static Stream<Arguments> adminInvalidData() {
    return Stream.of(
        Arguments.of("/Admin/adminAccount.jsp", "0"),
        Arguments.of("/Admin/createCruise.jsp", "-5"),
        Arguments.of(null, "-5"));
  }

  static Stream<Arguments> clientInvalidData() {
    return Stream.of(
        Arguments.of("/catalogue.jsp", "-5"),
        Arguments.of("/index.jsp", "0"),
        Arguments.of(null, "4"));
  }

  static Stream<Arguments> adminValidData() {
    return Stream.of(
        Arguments.of("/Admin/deleteCruise.jsp", "1"),
        Arguments.of("/Admin/deleteCruise.jsp", "3"),
        Arguments.of("/Admin/editCruise.jsp", null));
  }

  static Stream<Arguments> clientValidData() {
    return Stream.of(
        Arguments.of("/catalogue.jsp", "1"),
        Arguments.of("/catalogue.jsp", "3"),
        Arguments.of("/catalogue.jsp", null));
  }

  static Stream<Arguments> validFilterData() {
    return Stream.of(
        Arguments.of(null, null, "2"),
        Arguments.of(null, "2022-12-12", null),
        Arguments.of("2022-12-12", null, null),
        Arguments.of("2022-12-12", "2022-12-12", null),
        Arguments.of("2022-12-12", null, "2"),
        Arguments.of(null, "2022-12-12", "2"),
        Arguments.of("2022-12-12", "2022-12-12", "2"));
  }

  static Stream<Arguments> invalidFilterData() {
    return Stream.of(
        Arguments.of("2022-11-11", "2022-11-11", ""), // invalid transit time
        Arguments.of("2022-11-11", "202-12-12", "1"), // invalid arriving date
        Arguments.of("", "2022-11-11", "1"), // invalid leaving time
        Arguments.of("2022-12-100", "20-22-12-12", "1"), // invalid arriving and leaving dates
        Arguments.of("2022-2-2", "2022-11-11", "-2"), // invalid leaving date and transit time
        Arguments.of("2022-11-11", "", "-2"), // invalid arriving and transit time
        Arguments.of("2-0-2-2-12-12", "abvgt", "-2")); // invalid all values
  }
}
