package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Entities.Staff;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Services.StaffService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Staff> staff = createStaffList(request);
        if (staff == null) {
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
            addStaffRecords(staff, cruise.getShip().getId());
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

    /**
     * Shapes request to Route instance
     *
     * @param request
     * @return Route instance
     */
    public Route createRoute(HttpServletRequest request) {
        Route route = new Route();
        RouteService routeService = new RouteService();
        route.setDeparture(request.getParameter(FieldKey.DEPARTURE));
        route.setDestination(request.getParameter(FieldKey.DESTINATION));
        route.setTransitTime(Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)));
        if (!Validation.validateRouteFields(route) || routeService.getRouteByAllParameters(route).getDeparture() != null) {
            request.getSession().setAttribute("message", MessageKeys.ROUTE_INVALID);
            log.trace("Invalid Route parameters");
            log.debug("Command Finished");
            return null;
        }
        return route;
    }

    /**
     * Adds new Route record to database
     *
     * @param route new Route instance without id
     * @return Route instance with id value
     */
    public Route addRouteRecord(Route route) {
        RouteService routeService = new RouteService();
        routeService.createRoute(route);
        log.debug("Route record was added");
        return routeService.getRouteByAllParameters(route);
    }

    /**
     * Shapes request to Ship instance
     *
     * @param request
     * @return new Ship instance
     */
    public Ship createShip(HttpServletRequest request) {
        Ship ship = new Ship();
        ShipService shipService = new ShipService();
        ship.setName(request.getParameter(FieldKey.CRUISE_SHIP_NAME));
        ship.setPassengerCapacity(Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)));
        if (!Validation.validateShipFields(ship) || shipService.getShipByName(ship.getName()).getName() != null) {
            request.getSession().setAttribute("message", MessageKeys.SHIP_INVALID);
            log.trace("Invalid Ship parameters");
            log.debug("Command Finished");
            return null;
        }
        return ship;
    }

    /**
     * Adds new Ship instance to database
     *
     * @param ship new Ship instance
     * @return Ship instance with id value
     */
    public Ship addShipRecord(Ship ship) {
        ShipService shipService = new ShipService();
        shipService.registerShip(ship);
        log.debug("Ship record was added");
        return shipService.getShipByName(ship.getName());
    }

    /**
     * Shape request into list of Staff entities
     *
     * @param request
     * @return list of Staff entities
     */

    public List<Staff> createStaffList(HttpServletRequest request) {
        List<Staff> staffList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Staff staff = new Staff();
            staff.setFirstName(request.getParameter("first_name" + i));
            staff.setLastName(request.getParameter("last_name" + i));

            if (!Validation.validateStaffFields(staff)) {
                request.getSession().setAttribute("message", MessageKeys.STAFF_INVALID);
                log.trace("Invalid Cruise parameters");
                log.debug("Command Finished");
                return null;
            }
            staffList.add(staff);
        }
        return staffList;
    }

    /**
     *
     * @param staff List of new Staff instances
     * @param ship_id ID of ship
     */
    public void addStaffRecords(List<Staff> staff, long ship_id) {
        StaffService staffService = new StaffService();
        for (Staff value : staff) {
            value.setShip_id(ship_id);
            staffService.registerStaff(value);
            log.debug("Staff record was added");
        }
    }

}

