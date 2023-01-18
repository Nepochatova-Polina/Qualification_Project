package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.OrderService;
import com.example.epamfinalproject.Utility.FieldKey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DisplayOrderFormCommand implements Command {
    private static final Logger log = LogManager.getLogger(DisplayOrderFormCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");
        CruiseService cruiseService = new CruiseService();
        OrderService orderService = new OrderService();

        Cruise cruise = cruiseService.getCruiseByID(Long.parseLong(request.getParameter(FieldKey.ENTITY_ID)));
        int freeSeats = cruise.getShip().getPassengerCapacity() - orderService.getBookedSeatsByCruiseID(cruise.getId());
        if (freeSeats == 0) {
            request.getSession().setAttribute("message", MessageKeys.SHIP_INACCESSIBLE);
            log.trace("Cruise is full");
            log.debug("Command Finished");
            return "redirect:" + Path.CATALOGUE_PAGE;
        }
        request.getSession().setAttribute("cruise", cruise);
        request.getSession().setAttribute("freeSeats", freeSeats);

        log.debug("Command finished");

        return "redirect:" + Path.ORDER_PAGE;
    }
}
