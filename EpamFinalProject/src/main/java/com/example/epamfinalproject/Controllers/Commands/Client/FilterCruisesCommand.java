package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Controllers.SessionUtility;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.CruiseService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class FilterCruisesCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        CruiseService cruiseService = new CruiseService();
        User user = (User) request.getSession().getAttribute("user");

        String startDate = request.getParameter(FieldKey.CRUISE_LEAVING);
        String endDate = request.getParameter(FieldKey.CRUISE_ARRIVING);
        int duration = Integer.parseInt(request.getParameter(FieldKey.CRUISE_DURATION));

        List<Cruise> cruises = null;
        if (startDate == null && endDate == null && duration == 0) {
            log.debug("No information to filter");
            //todo create message for no filter
            request.getSession().setAttribute("message", MessageKeys.ACCESS_DENIED);
        } else if (startDate == null && endDate == null && duration != 0) {
            cruises = cruiseService.getAllCruisesByDuration(duration);
            log.debug("Filter cruises by duration");

        } else if (startDate == null && endDate != null && duration == 0) {
            cruises = cruiseService.getAllCruisesBeforeDate(LocalDate.parse(endDate));
            log.debug("Filter cruises before end date");

        } else if (startDate != null && endDate == null && duration == 0) {
            cruises = cruiseService.getAllCruisesAfterDate(LocalDate.parse(endDate));
            log.debug("Filter cruises after start date");

        } else if (startDate != null && endDate != null && duration == 0) {
            cruises = cruiseService.getAllCruisesBetweenTwoDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
            log.debug("Filter cruises between start date and end date");

        } else if (startDate != null && endDate == null && duration != 0) {
            cruises = cruiseService.getAllCruisesByStartAndDuration(LocalDate.parse(startDate), duration);
            log.debug("Filter cruises between start date and duration");

        } else if (startDate == null && endDate != null && duration != 0) {
            cruises = cruiseService.getAllCruisesByEndAndDuration(LocalDate.parse(endDate), duration);
            log.debug("Filter cruises between end date and duration");
        } else {
            cruises = cruiseService.getAllCruisesByDatesAndDuration(LocalDate.parse(startDate), LocalDate.parse(endDate), duration);
            log.debug("Filter cruises between end dates and duration");
        }
        SessionUtility.setUserParams(request, user, cruises);

        log.debug("Command finished");
        return Path.CATALOG_PAGE;
    }
}
