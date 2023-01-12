package com.example.epamfinalproject.Controllers.Commands.Administrator.Delete;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteCruiseCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteCruiseCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        CruiseService cruiseService = new CruiseService();
        cruiseService.deleteCruiseByID(Integer.parseInt(request.getParameter(FieldKey.ENTITY_ID)));

        SessionUtility.setCruisesParams(request,cruiseService.getAllCruises());
        log.debug("Command Finished");
        return Path.DELETE_SHIP_PAGE;
    }
}
