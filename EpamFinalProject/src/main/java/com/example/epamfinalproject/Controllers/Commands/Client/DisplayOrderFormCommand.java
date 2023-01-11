package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DisplayOrderFormCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");
        CruiseService cruiseService = new CruiseService();
        OrderService orderService = new OrderService();

        Cruise cruise = cruiseService.getCruiseByID(Long.parseLong(request.getParameter(FieldKey.ENTITY_ID)));
        int freeSeats = cruise.getShip().getPassengerCapacity() - orderService.getBookedSeatsByCruiseID(cruise.getId());
        if(freeSeats != 0){
            request.getSession().setAttribute("cruise",cruise);
            request.getSession().setAttribute("freeSeats",freeSeats);
        }
        log.debug("Command finished");

        return Path.ORDER_PAGE;
    }
}
