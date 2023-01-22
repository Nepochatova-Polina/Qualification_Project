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
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.Validation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * A command to create an instance of the Cruise class and add a record to the database. Available for Administrator
 */
public class CreateCruiseCommand implements Command {
  private static final Logger log = LogManager.getLogger(CreateCruiseCommand.class);
  private final CruiseService cruiseService;
  private final RouteService routeService;
  private final ShipService shipService;
  private final StaffService staffService;

  public CreateCruiseCommand(
      CruiseService cruiseService,
      RouteService routeService,
      ShipService shipService,
      StaffService staffService) {
    this.cruiseService = cruiseService;
    this.routeService = routeService;
    this.shipService = shipService;
    this.staffService = staffService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);

    Ship ship = createShip(request);
    if (ship == null) {
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    Route route = createRoute(request);
    if (route == null) {
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    List<Staff> staff = createStaffList(request);
    if (staff.isEmpty()) {
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    Cruise cruise = new Cruise();
    cruise.setName(request.getParameter(FieldKey.CRUISE_NAME));
    cruise.setPrice(Integer.parseInt(request.getParameter(FieldKey.CRUISE_PRICE)));
    cruise.setStartOfTheCruise(
        LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_LEAVING))));
    cruise.setEndOfTheCruise(
        LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_ARRIVING))));

    if (Validation.validateCruiseFields(cruise)) {
      cruise.setShip(addShipRecord(ship));
      cruise.setRoute(addRouteRecord(route));
      cruiseService.createCruise(cruise);
      addStaffRecords(staff, cruise.getShip().getId());
      log.debug("Record was added");
      log.debug(Constants.COMMAND_FINISHED);
    } else {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.CRUISE_INVALID);
      log.trace("Invalid Cruise parameters");
      log.debug(Constants.COMMAND_FINISHED);
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    return Constants.REDIRECT + Path.CREATE_CRUISE_PAGE;
  }

  /**
   * Shapes request to Route instance
   *
   * @return Route instance
   */
  public Route createRoute(HttpServletRequest request) {
    Route route = new Route();
    route.setDeparture(request.getParameter(FieldKey.DEPARTURE));
    route.setDestination(request.getParameter(FieldKey.DESTINATION));
    route.setTransitTime(Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)));
    if (!Validation.validateRouteFields(route)
        || routeService.getRouteByAllParameters(route) != null) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.ROUTE_INVALID);
      log.trace("Invalid Route parameters");
      log.debug(Constants.COMMAND_FINISHED);
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
  private Route addRouteRecord(Route route) {
    routeService.createRoute(route);
    log.debug("Route record was added");
    return routeService.getRouteByAllParameters(route);
  }

  /**
   * Shapes request to Ship instance
   *
   * @return new Ship instance
   */
  private Ship createShip(HttpServletRequest request) {
    Ship ship = new Ship();
    ship.setName(request.getParameter(FieldKey.CRUISE_SHIP_NAME));
    ship.setPassengerCapacity(Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)));
    if (!Validation.validateShipFields(ship)
        || shipService.getShipByName(ship.getName()) != null) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.SHIP_INVALID);
      log.trace("Invalid Ship parameters");
      log.debug(Constants.COMMAND_FINISHED);
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
  private Ship addShipRecord(Ship ship) {
    shipService.registerShip(ship);
    log.debug("Ship record was added");
    return shipService.getShipByName(ship.getName());
  }

  /**
   * Shape request into list of Staff entities
   *
   * @return list of Staff entities
   */
  private List<Staff> createStaffList(HttpServletRequest request) {
    List<Staff> staffList = new ArrayList<>();
    for (int i = 1; i <= Constants.STAFF_NUMBER; i++) {
      Staff staff = new Staff();
      staff.setFirstName(request.getParameter(FieldKey.FIRST_NAME + i));
      staff.setLastName(request.getParameter(FieldKey.LAST_NAME + i));

      if (!Validation.validateStaffFields(staff)) {
        request.getSession().setAttribute("message", MessageKeys.STAFF_INVALID);
        log.trace("Invalid Cruise parameters");
        log.debug(Constants.COMMAND_FINISHED);
        return Collections.emptyList();
      }
      staffList.add(staff);
    }
    return staffList;
  }

  /**
   * @param staff List of new Staff instances
   * @param shipId ID of ship
   */
  private void addStaffRecords(List<Staff> staff, long shipId) {
    for (Staff value : staff) {
      value.setShipId(shipId);
      staffService.registerStaff(value);
      log.debug("Staff record was added");
    }
  }
}
