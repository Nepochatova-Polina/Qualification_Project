package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The Command to delete a Cruise record from the database.
 * The command changes the value of the "deleted" field to true,
 * after that the record is no longer displayed in the interface
 */
public class DeleteCruiseCommand implements Command {
  private static final Logger log = LogManager.getLogger(DeleteCruiseCommand.class);

  private final CruiseService cruiseService;

  public DeleteCruiseCommand(CruiseService cruiseService) {
    this.cruiseService = cruiseService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);

    List<String> params = List.of(request.getParameterValues(FieldKey.ENTITY_ID));

    if (params.isEmpty()) {
      log.trace("Not a Ship has been ticked off");
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    for (String param : params) {
      if(Integer.parseInt(param) <= 0){
        log.warn("Invalid Entity ID");
        request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.PARAM_INVALID);
        return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
      }
      cruiseService.deleteCruiseByID(Integer.parseInt(param));
      log.debug("Record with id " + Integer.parseInt(param) + "was deleted");
    }

    SessionUtility.setCruisesParams(
        request, cruiseService.getAllCruisesForPage(Constants.PAGE_SIZE, 0));
    log.debug(Constants.COMMAND_FINISHED);
    return Constants.REDIRECT + Path.DELETE_CRUISE_PAGE;
  }
}
