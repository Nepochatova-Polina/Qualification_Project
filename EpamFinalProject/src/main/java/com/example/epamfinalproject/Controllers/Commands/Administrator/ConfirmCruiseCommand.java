package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Database.Queries.CruiseQueries;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConfirmCruiseCommand implements Command {
  private static final Logger log = LogManager.getLogger(ConfirmCruiseCommand.class);

  private final CruiseService cruiseService;

  public ConfirmCruiseCommand(CruiseService cruiseService) {
    this.cruiseService = cruiseService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);
    String param = request.getParameter(FieldKey.ENTITY_ID);

    if (param.isEmpty()) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.CRUISE_CONFIRMATION_ERROR);
      log.trace("Not an Cruise has been ticked off");
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    if (Long.parseLong(param) < 1) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.CRUISE_CONFIRMATION_ERROR);
      log.trace("Cruise ID is invalid");
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    } else {
      cruiseService.confirmCruiseByID(Long.parseLong(param));
      log.debug("Record with id " + Integer.parseInt(param) + "was confirmed");
    }

    SessionUtility.setCruisesParams(
        request,
        cruiseService.getAllCruisesForPage(
            CruiseQueries.GET_ALL_ACTUAL_CRUISES_FOR_FIRST_PAGE_ADMIN_QUERY));
    log.debug(Constants.COMMAND_FINISHED);
    return Constants.REDIRECT + Path.CATALOGUE_PAGE;
  }
}
