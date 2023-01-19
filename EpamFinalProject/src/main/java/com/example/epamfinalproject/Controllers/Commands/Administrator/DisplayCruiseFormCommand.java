package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DisplayCruiseFormCommand implements Command {
  private static final Logger log = LogManager.getLogger(DisplayCruiseFormCommand.class);

  private final CruiseService cruiseService;

  public DisplayCruiseFormCommand(CruiseService cruiseService) {
    this.cruiseService = cruiseService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);
    Cruise cruise =
        cruiseService.getCruiseByID(Long.parseLong(request.getParameter(FieldKey.ENTITY_ID)));
    if (cruise == null) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.CRUISE_INVALID);
      log.trace("Invalid Cruise parameters");
      log.debug(Constants.COMMAND_FINISHED);
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    SessionUtility.setCruiseParamsForAdmin(request, cruise);
    log.debug(Constants.COMMAND_FINISHED);

    return Constants.REDIRECT + Path.EDIT_CRUISE_PAGE;
  }
}
