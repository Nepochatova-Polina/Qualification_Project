package com.example.epamfinalproject.Controllers.Commands.Administrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DeleteCruiseCommandTest {
  private static HttpServletRequest request;
  private static HttpSession session;
  private static Command command;

  @BeforeAll
  static void beforeAll() {
    request = mock(HttpServletRequest.class);
    session = mock(HttpSession.class);
    CruiseDAO cruiseDAO = mock(CruiseDAO.class);
    command = new DeleteCruiseCommand(new CruiseService(cruiseDAO));
  }

  @Test
  void deleteCruiseEmptyTest() {
    String[] values = {};
    when(request.getSession()).thenReturn(session);
    when(request.getParameterValues(FieldKey.ENTITY_ID)).thenReturn(values);

    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
  }

  @Test
  void deleteCruiseFilledTest() {
    String[] values = {"2", "3", "5", "7"};
    when(request.getSession()).thenReturn(session);
    when(request.getParameterValues(FieldKey.ENTITY_ID)).thenReturn(values);

    assertEquals(Constants.REDIRECT + Path.DELETE_CRUISE_PAGE, command.execute(request));
  }

  @Test
  void deleteCruiseInvalidTest() {
    String[] values = {"-2", "-3", "-5", "-7"};
    when(request.getSession()).thenReturn(session);
    when(request.getParameterValues(FieldKey.ENTITY_ID)).thenReturn(values);

    assertEquals(Constants.REDIRECT + Path.ADMINISTRATOR_PAGE, command.execute(request));
  }
}
