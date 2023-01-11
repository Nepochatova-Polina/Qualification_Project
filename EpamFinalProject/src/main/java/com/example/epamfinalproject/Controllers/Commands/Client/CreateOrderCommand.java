package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Controllers.SessionUtility;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Order;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.OrderService;
import com.example.epamfinalproject.Services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *
 */
public class CreateOrderCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");
        OrderService orderService = new OrderService();
        UserService userService = new UserService();

        Order order = shapeOrder(request);
        User user = (User) request.getSession().getAttribute("user");

        List<Order> userOrders = orderService.getOrdersByUserID(order.getUser().getId());

        if (checkOrder(userOrders, order)) {
            log.debug("User already has the same order");
            request.getSession().setAttribute("message", MessageKeys.ORDER_ALREADY_CREATED);
            return Path.ORDER_PAGE;
        }

        InputStream fileContent;
        long size;
        try {
            Part filePart = request.getPart("passport");
            fileContent = filePart.getInputStream();
            size = filePart.getSize();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        orderService.createOrder(order);
        userService.updateUserPassport(1, fileContent, size);

        userOrders = orderService.getOrdersByUserID(order.getUser().getId());

        SessionUtility.setOrdersForClient(request, user, userOrders);
        log.debug("Command finished");
        return "redirect:" + Path.CATALOGUE_PAGE;
    }

    private Order shapeOrder(HttpServletRequest request) {
        Order order = new Order();

        order.setUser((User) request.getSession().getAttribute("user"));

        CruiseService service = new CruiseService();
        Cruise cruise = service.getCruiseByID((Long.parseLong(request.getParameter(FieldKey.ENTITY_ID))));
        order.setCruise(cruise);
        order.setNumberOfSeats(1);
        order.setPrice(cruise.getPrice());
        return order;
    }

    private boolean checkOrder(List<Order> orderList, Order order) {
        for (Order value : orderList) {
            if (value.equals(order)) {
                return true;
            }
        }
        return false;
    }
}
