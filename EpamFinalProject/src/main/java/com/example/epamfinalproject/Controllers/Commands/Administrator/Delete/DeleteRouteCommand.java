package com.example.epamfinalproject.Controllers.Commands.Administrator.Delete;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.LoginCommand;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteRouteCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteRouteCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        RouteService routeService = new RouteService();

        routeService.deleteRouteByID(Integer.parseInt(request.getParameter(FieldKey.ENTITY_ID)));
        SessionUtility.setRouteParams(request,routeService.getAllRoutes());

        log.debug("Command Finished");
        return Path.DELETE_ROUTE_PAGE;
    }
}
