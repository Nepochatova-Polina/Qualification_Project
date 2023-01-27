package com.example.epamfinalproject.Controllers.Commands.Common;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Database.Interfaces.*;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.*;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DisplayFormWithCruiseInfoCommandTest {

  private static HttpServletRequest request;
  private static HttpSession session;
  private static Command command;
  private static Cruise cruise;
  private static CruiseService cruiseService;
  private static OrderService orderService;
  private static User admin;
  private static User client;

  @BeforeAll
  static void beforeAll() {
    session = mock(HttpSession.class);
    request = mock(HttpServletRequest.class);
    admin = new User.UserBuilder().role(UserRole.ADMINISTRATOR).build();
    client = new User.UserBuilder().role(UserRole.CLIENT).build();
    cruiseService = new CruiseService(mock(CruiseDAO.class));
    orderService = new OrderService(mock(OrderDAO.class));
    cruise =
        new Cruise(
            new Ship(1, "Name", 1),
                new Route(1, "Value", "Value", 4),
                "Name", 0, null, null);
    command = new DisplayFormWithCruiseInfoCommand(cruiseService, orderService);
  }

  @Test
  void DisplayFormCruiseNullTest() {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("user")).thenReturn(client);
    when(request.getParameter(FieldKey.ENTITY_ID)).thenReturn("1");
    when(cruiseService.getCruiseByID(any(Long.class))).thenReturn(null);
    assertEquals(Constants.REDIRECT + Path.MAIN_PAGE, command.execute(request));
  }

  @Test
  void DisplayFormNotNullClientTest() {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("user")).thenReturn(client);
    when(request.getParameter(FieldKey.ENTITY_ID)).thenReturn("1");
    when(cruiseService.getCruiseByID(any(Long.class))).thenReturn(cruise);
    when(orderService.getBookedSeatsByCruiseID(cruise.getId())).thenReturn(0);
    assertEquals(Constants.REDIRECT + Path.ORDER_PAGE, command.execute(request));
  }

  @Test
  void DisplayFormNotNullAdminTest() {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("user")).thenReturn(admin);
    when(request.getParameter(FieldKey.ENTITY_ID)).thenReturn("1");
    when(cruiseService.getCruiseByID(any(Long.class))).thenReturn(new Cruise());
    assertEquals(Constants.REDIRECT + Path.EDIT_CRUISE_PAGE, command.execute(request));
  }

  @Test
  void DisplayFormEmptyUserTest() {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("user")).thenReturn(null);

    assertEquals(Constants.REDIRECT + Path.MAIN_PAGE, command.execute(request));
  }

  @Test
  void DisplayFormNotFreeSeatsTest() {
    when(request.getSession()).thenReturn(session);
    when(request.getParameter(FieldKey.ENTITY_ID)).thenReturn("1");
    when(request.getSession().getAttribute("user")).thenReturn(client);
    when(cruiseService.getCruiseByID(any(Long.class))).thenReturn(cruise);
    when(orderService.getBookedSeatsByCruiseID(cruise.getId())).thenReturn(1);

    assertEquals(Constants.REDIRECT + Path.CATALOGUE_PAGE, command.execute(request));
  }
}
