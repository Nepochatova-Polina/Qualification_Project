package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Administrator.CreateCruiseCommand;
import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Objects;

public class EditCruiseCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateCruiseCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");
        CruiseService cruiseService = new CruiseService();
        ShipService shipService = new ShipService();
        RouteService routeService = new RouteService();

        Cruise cruise = (Cruise) request.getSession().getAttribute("cruise");

        if(!Objects.equals(request.getParameter("cruise_name"), "")) cruise.setName(request.getParameter("cruise_name"));
        if(!Objects.equals(request.getParameter("ship_name"), "")) cruise.getShip().setName(request.getParameter("ship_name"));
        if(Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)) != 0)
            cruise.getShip().setPassengerCapacity(Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)));
        if (!Objects.equals(request.getParameter(FieldKey.CRUISE_LEAVING), ""))
            cruise.setStartOfTheCruise(LocalDate.parse(request.getParameter(FieldKey.CRUISE_LEAVING)));
        if (!Objects.equals(request.getParameter(FieldKey.CRUISE_ARRIVING), ""))
            cruise.setStartOfTheCruise(LocalDate.parse(request.getParameter(FieldKey.CRUISE_ARRIVING)));
        if(!Objects.equals(request.getParameter(FieldKey.DEPARTURE), ""))
            cruise.getRoute().setDeparture(request.getParameter(FieldKey.DEPARTURE));
        if(!Objects.equals(request.getParameter(FieldKey.DESTINATION), ""))
            cruise.getRoute().setDeparture(request.getParameter(FieldKey.DESTINATION));
        if(Integer.parseInt(request.getParameter(FieldKey.CRUISE_PRICE)) != 0)
            cruise.setPrice(Integer.parseInt(request.getParameter(FieldKey.CRUISE_PRICE)));
        if(Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)) != 0)
            cruise.getRoute().setTransitTime(Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)));

        if(Validation.validateCruiseFields(cruise)){
            cruiseService.updateCruiseByID(cruise,cruise.getId());
            log.debug("Cruise Record was updated");
            shipService.updateShipByID(cruise.getShip(),cruise.getShip().getId());
            log.debug("Ship Record was updated");
            routeService.updateRouteByID(cruise.getRoute(),cruise.getRoute().getId());
            log.debug("Route Record was updated");

            SessionUtility.setCruisesParams(request,cruiseService.getAllCruisesForPage(FieldKey.PAGE_SIZE,0));
            log.debug("Command Finished");
        } else {
            request.getSession().setAttribute("message", MessageKeys.CRUISE_INVALID);
            log.trace("Invalid Cruise parameters");
            log.debug("Command Finished");
            return "redirect:" + Path.ADMINISTRATOR_PAGE;
        }
        log.debug("Command Finished");
        return "redirect:" + Path.ADMINISTRATOR_PAGE;
    }
}
