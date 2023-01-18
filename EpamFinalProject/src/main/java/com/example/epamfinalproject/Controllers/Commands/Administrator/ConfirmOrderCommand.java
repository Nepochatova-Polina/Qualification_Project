package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.OrderService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConfirmOrderCommand implements Command {
    private static final Logger log = LogManager.getLogger(ConfirmOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");
        List<String> params = List.of(request.getParameterValues(FieldKey.ENTITY_ID));

        if (params.isEmpty()) {
            request.getSession().setAttribute("message", MessageKeys.ORDER_CONFIRMATION_ERROR);
            log.trace("Not a Order was checked");
            return"redirect:"+ Path.ADMINISTRATOR_PAGE;
        }
        OrderService orderService = new OrderService();
        for (String param : params) {
            orderService.confirmOrderByID(Long.parseLong(param));
            log.debug("Record with id " + Integer.parseInt(param) + "was confirmed");
        }

        SessionUtility.setOrders(request,orderService.getAllUnconfirmedOrders());
        log.debug("Command Finished");
        return "redirect:"+ Path.ADMINISTRATOR_PAGE;
    }
}
