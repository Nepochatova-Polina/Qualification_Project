package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Entities.Cruise;
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

    int recordsCount = cruiseService.getNumberOfActualCruises();
    int page = 1;
    if (request.getParameter("page") != null) {
      page = Integer.parseInt(request.getParameter("page"));
    }
    List<Cruise> cruises;
    if (request.getParameter("page-path").contains("Admin")) {
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
    return Constants.REDIRECT + request.getParameter("page-path");
  }
}
