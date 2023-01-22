package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.SessionUtility;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CatalogueCommand implements Command {
  private static final Logger log = LogManager.getLogger(CatalogueCommand.class);

  private final CruiseService cruiseService;

  public CatalogueCommand(CruiseService cruiseService) {
    this.cruiseService = cruiseService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);

    User user = (User) request.getSession().getAttribute("user");

    int recordsCount = cruiseService.getNumberOfActualCruises();
    int page = 1;
    String pagePath = request.getParameter("page-path");

    if (request.getParameter("page") != null) {
      page = Integer.parseInt(request.getParameter("page"));
    }
    if (page < 1 && user.getRole().equals(UserRole.ADMINISTRATOR)
        || pagePath == null && user.getRole().equals(UserRole.ADMINISTRATOR)) {
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    if (page < 1 || pagePath == null) {
      return Constants.REDIRECT + Path.MAIN_PAGE;
    }
    List<Cruise> cruises;
    if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
      cruises =
          cruiseService.getAllCruisesForPage(Constants.PAGE_SIZE, (page - 1) * Constants.PAGE_SIZE);
    } else {
      cruises =
          cruiseService.getActualCruisesForPage(
              Constants.PAGE_SIZE, (page - 1) * Constants.PAGE_SIZE);
    }
    SessionUtility.setCruisesParams(request, cruises);
    int pageCount = (int) Math.ceil(recordsCount * 1.0 / Constants.PAGE_SIZE);
    request.getSession().setAttribute("numOfPages", pageCount);
    request.getSession().setAttribute("currentPage", page);

    log.debug(Constants.COMMAND_FINISHED);
    return Constants.REDIRECT + pagePath;
  }
}
