package com.example.epamfinalproject.Controllers.Commands.Administrator.Create;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

public class CreateCruiseCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateCruiseCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        CruiseService cruiseService = new CruiseService();

        Ship ship = createShip(request);
        if (ship == null) {
            return "redirect:" + Path.ADMINISTRATOR_PAGE;
        }
        Route route = createRoute(request);
        if (route == null) {
            return "redirect:" + Path.ADMINISTRATOR_PAGE;
        }
        Cruise cruise = new Cruise();
        cruise.setName(request.getParameter(FieldKey.CRUISE_NAME));
        cruise.setPrice(Integer.parseInt(request.getParameter(FieldKey.CRUISE_PRICE)));
        cruise.setStartOfTheCruise(LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_LEAVING))));
        cruise.setEndOfTheCruise(LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_ARRIVING))));

        if (Validation.validateCruiseFields(cruise)) {
            cruise.setShip(addShipRecord(ship));
            cruise.setRoute(addRouteRecord(route));
            cruiseService.createCruise(cruise);
            log.debug("Record was added");
            log.debug("Command Finished");
        } else {
            request.getSession().setAttribute("message", MessageKeys.CRUISE_INVALID);
            log.trace("Invalid Cruise parameters");
            log.debug("Command Finished");
            return "redirect:" + Path.ADMINISTRATOR_PAGE;
        }
        return "redirect:" + Path.ADMINISTRATOR_PAGE;
    }

    public Route createRoute(HttpServletRequest request) {
        Route route = new Route();
        route.setDeparture(request.getParameter(FieldKey.DEPARTURE));
        route.setDestination(request.getParameter(FieldKey.DESTINATION));
        route.setTransitTime(Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)));
        if (!Validation.validateRouteFields(route)) {
            request.getSession().setAttribute("message", MessageKeys.ROUTE_INVALID);
            log.trace("Invalid Route parameters");
            log.debug("Command Finished");
            return null;
        }
        return route;
    }
    public Route addRouteRecord(Route route){
        RouteService routeService = new RouteService();
        routeService.createRoute(route);
        log.debug("Route record was added");
        List<Route> routes = routeService.getRouteByDepartureAndDestination(route.getDeparture(), route.getDestination());
        if (routes.size() != 1) {
            return routes.get(routes.size() - 1);
        } else {
            return routes.get(0);
        }
    }
    public Ship createShip(HttpServletRequest request) {
        Ship ship = new Ship();
        ship.setName(request.getParameter(FieldKey.CRUISE_SHIP_NAME));
        ship.setPassengerCapacity(Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)));
        if (!Validation.validateShipFields(ship)) {
            request.getSession().setAttribute("message", MessageKeys.SHIP_INVALID);
            log.trace("Invalid Ship parameters");
            log.debug("Command Finished");
            return null;
        }
       return ship;
    }
    public Ship addShipRecord(Ship ship){
        ShipService shipService = new ShipService();
        shipService.registerShip(ship);
        log.debug("Ship record was added");
        return shipService.getShipByName(ship.getName());
    }
}
