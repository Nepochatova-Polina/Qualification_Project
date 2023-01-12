package com.example.epamfinalproject.Controllers.Commands.Administrator.Create;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.LoginCommand;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class CreateCruiseCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        CruiseService cruiseService = new CruiseService();
        RouteService routeService = new RouteService();
        ShipService shipService = new ShipService();
        Cruise cruise = new Cruise();

        cruise.setName(request.getParameter(FieldKey.CRUISE_NAME));
        cruise.setShip(shipService.getShipByID(Integer.parseInt(request.getParameter(FieldKey.CRUISE_SHIP_ID))));
        cruise.setRoute(routeService.getRouteByID(Integer.parseInt(request.getParameter(FieldKey.CRUISE_ROUTE_ID))));
        cruise.setPrice(Integer.parseInt(request.getParameter(FieldKey.CRUISE_PRICE)));
        cruise.setStartOfTheCruise(LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_LEAVING))));
        cruise.setEndOfTheCruise(LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_ARRIVING))));

        if(!checkCruise(cruise)){
            request.getSession().setAttribute("message",MessageKeys.SHIP_INACCESSIBLE);
            return Path.CREATE_CRUISE_PAGE;
        }
        if (Validation.validateCruiseFields(cruise)) {
            cruiseService.createCruise(cruise);
            log.debug("Record was added");
            log.debug("Command Finished");
        }else {
            request.getSession().setAttribute("message", MessageKeys.CRUISE_INVALID);
            log.trace("Invalid Route parameters");
            log.debug("Command Finished");
            return Path.CREATE_CRUISE_PAGE;
        }
        return Path.ADMINISTRATOR_PAGE;
    }
    private boolean checkCruise(Cruise cruise){
        ShipService shipService = new ShipService();
        List<Ship> unsuitableShips = shipService.getShipsByDates(cruise.getStartOfTheCruise(), cruise.getEndOfTheCruise());
        return !unsuitableShips.contains(cruise.getShip());
    }
}
