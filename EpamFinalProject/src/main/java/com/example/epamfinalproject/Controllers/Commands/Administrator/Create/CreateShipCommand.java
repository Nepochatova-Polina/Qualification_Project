package com.example.epamfinalproject.Controllers.Commands.Administrator.Create;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.LoginCommand;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateShipCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        Ship ship = new Ship();
        ship.setName(request.getParameter(FieldKey.SHIP_NAME));
        ship.setPassengerCapacity(Integer.parseInt(request.getParameter(FieldKey.PASSENGER_CAPACITY)));

        if (Validation.validateShipFields(ship)) {
            ShipService shipService = new ShipService();
            shipService.registerShip(ship);

            log.debug("Record was added");
            log.debug("Command Finished");
        } else {
            request.getSession().setAttribute("message", MessageKeys.SHIP_INVALID);
            log.trace("Invalid Ship parameters");
            log.debug("Command Finished");
            return Path.CREATE_SHIP_PAGE;
        }
        return "redirect:" + Path.ADMINISTRATOR_PAGE;
    }
}
