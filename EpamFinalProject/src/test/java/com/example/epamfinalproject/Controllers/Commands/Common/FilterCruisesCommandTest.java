package com.example.epamfinalproject.Controllers.Commands.Common;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Database.Interfaces.CruiseDAO;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
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

class FilterCruisesCommandTest {
  private static HttpServletRequest request;
  private static HttpSession session;
  private static Command command;
  private static Cruise cruise;

  @BeforeAll
  static void beforeAll() {
    session = mock(HttpSession.class);
    request = mock(HttpServletRequest.class);
    CruiseDAO cruiseDAO = mock(CruiseDAO.class);
    cruise = new Cruise(new Ship(), new Route(), null, 0, null, null);
    command = new FilterCruisesCommand(new CruiseService(cruiseDAO));
  }

  @ParameterizedTest
  @MethodSource("invalidArgumentsData")
  void FilterCruisesCommandInvalidTest(String leaving, String arriving, String transit) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);

    when(request.getParameter(FieldKey.CRUISE_LEAVING)).thenReturn(leaving);
    when(request.getParameter(FieldKey.CRUISE_ARRIVING)).thenReturn(arriving);
    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn(transit);

    assertEquals(Constants.REDIRECT + Path.MAIN_PAGE, command.execute(request));
  }

  @ParameterizedTest
  @MethodSource("validArgumentsData")
  void FilterCruisesCommandValidTest(String leaving, String arriving, String transit) {
    when(request.getSession()).thenReturn(session);
    when(request.getSession().getAttribute("cruise")).thenReturn(cruise);

    when(request.getParameter(FieldKey.CRUISE_LEAVING)).thenReturn(leaving);
    when(request.getParameter(FieldKey.CRUISE_ARRIVING)).thenReturn(arriving);
    when(request.getParameter(FieldKey.TRANSIT_TIME)).thenReturn(transit);

    assertEquals(Constants.REDIRECT + Path.CATALOGUE_PAGE, command.execute(request));
  }


  static Stream<Arguments> validArgumentsData() {
    return Stream.of(
        Arguments.of("", "", "-1"), // empty variables
        Arguments.of("", "", "1"), // non-empty transit time variable
        Arguments.of("", "2022-10-10", "-1"), // non-empty arriving date variable
        Arguments.of("", "2022-10-10", "1"), // non-empty arriving date and transit time variable
        Arguments.of("2020-10-10", "", "-1"), // non-empty leaving date variable
        Arguments.of(
            "2020-10-10", "2020-10-10", "-1"), // non-empty  leaving and arriving dates variables
        Arguments.of("2020-10-10", "", "1"), // non-empty leaving date and transit time variables
        Arguments.of("2020-10-10", "2020-10-10", "1"));
  }

  static Stream<Arguments> invalidArgumentsData() {
    return Stream.of(
        Arguments.of("202-10-10", "", "-1"), // invalid leaving date
        Arguments.of("2020-10-10", "2020-20-20", "-1"), // invalid arriving date
        Arguments.of("2020-20-10", "202-10-10", "1")); // invalid leaving and arriving dates
  }
}
