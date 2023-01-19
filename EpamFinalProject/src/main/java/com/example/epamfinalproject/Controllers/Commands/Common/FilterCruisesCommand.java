package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FilterCruisesCommand implements Command {
  private static final Logger log = LogManager.getLogger(FilterCruisesCommand.class);

  private final CruiseService cruiseService;

  public FilterCruisesCommand(CruiseService cruiseService) {
    this.cruiseService = cruiseService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);

    String startDate = request.getParameter(FieldKey.CRUISE_LEAVING);
    String endDate = request.getParameter(FieldKey.CRUISE_ARRIVING);
    int duration = Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME));

    List<Cruise> cruises;
    if (Objects.equals(startDate, "") && Objects.equals(endDate, "") && duration == 0) {
      log.debug("No filter information");
      cruises = cruiseService.getActualCruisesForPage(Constants.PAGE_SIZE, 0);

    } else if (Objects.equals(startDate, "") && Objects.equals(endDate, "") && duration != 0) {
      cruises = cruiseService.getAllCruisesByDuration(duration);
      log.debug("Filter cruises by duration");

    } else if (Objects.equals(startDate, "") && !Objects.equals(endDate, "") && duration == 0) {
      cruises = cruiseService.getAllCruisesBeforeDate(LocalDate.parse(endDate));
      log.debug("Filter cruises before end date");

    } else if (!Objects.equals(startDate, "") && Objects.equals(endDate, "") && duration == 0) {
      cruises = cruiseService.getAllCruisesAfterDate(LocalDate.parse(startDate));
      log.debug("Filter cruises after start date");

    } else if (!Objects.equals(startDate, "") && !Objects.equals(endDate, "") && duration == 0) {
      cruises =
          cruiseService.getAllCruisesBetweenTwoDates(
              LocalDate.parse(startDate), LocalDate.parse(endDate));
      log.debug("Filter cruises between start date and end date");

    } else if (!Objects.equals(startDate, "") && Objects.equals(endDate, "") && duration != 0) {
      cruises = cruiseService.getAllCruisesByStartAndDuration(LocalDate.parse(startDate), duration);
      log.debug("Filter cruises between start date and duration");

    } else if (Objects.equals(startDate, "") && !Objects.equals(endDate, "") && duration != 0) {
      cruises = cruiseService.getAllCruisesByEndAndDuration(LocalDate.parse(endDate), duration);
      log.debug("Filter cruises between end date and duration");

    } else {
      cruises =
          cruiseService.getAllCruisesByDatesAndDuration(
              LocalDate.parse(startDate), LocalDate.parse(endDate), duration);
      log.debug("Filter cruises between end dates and duration");
    }

    SessionUtility.setCruisesParams(request, cruises);
    log.debug(Constants.COMMAND_FINISHED);
    return Constants.REDIRECT + Path.CATALOGUE_PAGE;
  }
}
