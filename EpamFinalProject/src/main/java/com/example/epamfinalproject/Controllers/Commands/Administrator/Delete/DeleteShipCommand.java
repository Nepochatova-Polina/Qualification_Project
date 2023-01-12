package com.example.epamfinalproject.Controllers.Commands.Administrator.Delete;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.LoginCommand;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteShipCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteShipCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        ShipService shipService = new ShipService();

        shipService.deleteShipByID(Integer.parseInt(request.getParameter(FieldKey.ENTITY_ID)));
        SessionUtility.setShipsParams(request,shipService.getAllShips());

        log.debug("Command Finished");
        return Path.DELETE_SHIP_PAGE;
    }
}
