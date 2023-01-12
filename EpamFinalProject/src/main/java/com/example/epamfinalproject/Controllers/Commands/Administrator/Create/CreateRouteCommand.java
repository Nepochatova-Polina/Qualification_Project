package com.example.epamfinalproject.Controllers.Commands.Administrator.Create;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.LoginCommand;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateRouteCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");
        Route route = new Route();

        route.setDeparture(request.getParameter(FieldKey.DEPARTURE));
        route.setDestination(request.getParameter(FieldKey.DESTINATION));
        route.setNumberOfPorts(Integer.parseInt(request.getParameter(FieldKey.NUMBER_OF_PORTS)));
        route.setTransitTime(Integer.parseInt(request.getParameter(FieldKey.TRANSIT_TIME)));

        if (Validation.validateRouteFields(route)) {
            RouteService routeService = new RouteService();
            routeService.createRoute(route);

            log.debug("Record was added");
            log.debug("Command Finished");
        } else {
            request.getSession().setAttribute("message", MessageKeys.ROUTE_INVALID);
            log.trace("Invalid Route parameters");
            log.debug("Command Finished");
            return Path.CREATE_ROUTE_PAGE;
        }
        return "redirect:" + Path.ADMINISTRATOR_PAGE;
    }
}
