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
  void catalogueCommandInvalidPageAdminTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userAdmin);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("clientInvalidData")
  void catalogueCommandInvalidPageClientTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userClient);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + Path.MAIN_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("adminValidData")
  void catalogueCommandValidPageAdminTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userAdmin);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + path, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("clientValidData")
  void catalogueCommandValidPageClientTest(String path, String page) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);
    when(request.getSession().getAttribute("user")).thenReturn(userClient);

    when(request.getParameter("page-path")).thenReturn(path);
    when(request.getParameter("page")).thenReturn(page);

    assertEquals(Constants.REDIRECT + path, command.execute(request));
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
}
