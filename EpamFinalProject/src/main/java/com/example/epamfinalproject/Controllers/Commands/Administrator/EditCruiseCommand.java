package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import com.example.epamfinalproject.Utility.Validation;
import java.time.LocalDate;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.example.epamfinalproject.Database.Queries.CruiseQueries.GET_ALL_CRUISES_FOR_FIRST_PAGE_QUERY;

/**
 * The Command collects data from the request and checks which fields have been changed, generates a
 * new Cruise class instance and overwrites the data in the database. Fields for which there is no
 * new data remain unchanged.
 */
public class EditCruiseCommand implements Command {
  private static final Logger log = LogManager.getLogger(EditCruiseCommand.class);

  private final CruiseService cruiseService;
  private final ShipService shipService;
  private final RouteService routeService;

  public EditCruiseCommand(
      CruiseService cruiseService, ShipService shipService, RouteService routeService) {
    this.cruiseService = cruiseService;
    this.shipService = shipService;
    this.routeService = routeService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);
    boolean shipFlag = false;
    boolean routeFlag = false;
    boolean cruiseFlag = false;

    Cruise cruise = (Cruise) request.getSession().getAttribute("cruise");

    if (!Objects.equals(request.getParameter(FieldKey.CRUISE_NAME), "")) {
      cruise.setName(request.getParameter(FieldKey.CRUISE_NAME));
      cruiseFlag = true;
    }
    if (!Objects.equals(request.getParameter("ship_name"), "")) {
      cruise.getShip().setName(request.getParameter("ship_name"));
      shipFlag = true;
    }

    if (Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)) != 0) {
      cruise
              .getShip()
              .setPassengerCapacity(
                      Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)));
      shipFlag = true;
    }

    if (!Validation.validateShipFields(cruise.getShip())) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.SHIP_INVALID);
      log.trace("Invalid Ship parameters");
      log.debug(Constants.COMMAND_FINISHED);
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }

    if (!Objects.equals(request.getParameter(FieldKey.DEPARTURE), "")) {
      cruise.getRoute().setDeparture(request.getParameter(FieldKey.DEPARTURE));
      routeFlag = true;
    }
    if (!Objects.equals(request.getParameter(FieldKey.DESTINATION), "")) {
      cruise.getRoute().setDestination(request.getParameter(FieldKey.DESTINATION));
      routeFlag = true;
    }
    if (Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)) != 0) {
      cruise
          .getRoute()
          .setTransitTime(Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)));
      routeFlag = true;
    }
    if (!Validation.validateRouteFields(cruise.getRoute())) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.ROUTE_INVALID);
      log.trace("Invalid Route parameters");
      log.debug(Constants.COMMAND_FINISHED);
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    if (!Objects.equals(request.getParameter(FieldKey.CRUISE_LEAVING), "")
        && Validation.isDateValid((request.getParameter(FieldKey.CRUISE_LEAVING)))) {
      cruise.setStartOfTheCruise(LocalDate.parse(request.getParameter(FieldKey.CRUISE_LEAVING)));
      cruiseFlag = true;
    }
    if (!Objects.equals(request.getParameter(FieldKey.CRUISE_ARRIVING), "")
        && Validation.isDateValid((request.getParameter(FieldKey.CRUISE_ARRIVING)))) {
      cruise.setStartOfTheCruise(LocalDate.parse(request.getParameter(FieldKey.CRUISE_ARRIVING)));
      cruiseFlag = true;
    }
    if (Integer.parseInt(request.getParameter(FieldKey.CRUISE_PRICE)) != 0) {
      cruise.setPrice(Integer.parseInt(request.getParameter(FieldKey.CRUISE_PRICE)));
      cruiseFlag = true;
    }

    if (Validation.validateCruiseFields(cruise)) {
      if(cruiseFlag) {
        cruiseService.updateCruiseByID(cruise, cruise.getId());
        log.debug("Cruise Record was updated");
      }
      if (shipFlag) {
        shipService.updateShipByID(cruise.getShip(), cruise.getShip().getId());
        log.debug("Ship Record was updated");
      }
      if (routeFlag) {
        routeService.updateRouteByID(cruise.getRoute(), cruise.getRoute().getId());
        log.debug("Route Record was updated");
      }
      SessionUtility.setCruisesParams(
          request, cruiseService.getAllCruisesForPage(GET_ALL_CRUISES_FOR_FIRST_PAGE_QUERY));

    } else {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.CRUISE_INVALID);
      log.trace("Invalid Cruise parameters");
      log.debug(Constants.COMMAND_FINISHED);
      return Constants.REDIRECT + Path.ADMINISTRATOR_PAGE;
    }
    log.debug(Constants.COMMAND_FINISHED);
    return Constants.REDIRECT + Path.DISPLAY_EDIT_CATALOGUE_PAGE;
  }
}
