package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Order;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.OrderService;
import com.example.epamfinalproject.Services.UserService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CreateOrderCommand implements Command {
  private static final Logger log = LogManager.getLogger(CreateOrderCommand.class);
  private final OrderService orderService;
  private final UserService userService;
  private final CruiseService cruiseService;

  public CreateOrderCommand(
      OrderService orderService, UserService userService, CruiseService cruiseService) {
    this.orderService = orderService;
    this.userService = userService;
    this.cruiseService = cruiseService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);

    Order order = shapeOrder(request);
    User user = (User) request.getSession().getAttribute("user");

    List<Order> userOrders = orderService.getOrdersByUserID(order.getUser().getId());

    if (checkOrder(userOrders, order)) {
      log.debug("User already has the same order");
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.ORDER_ALREADY_CREATED);
      return Path.ORDER_PAGE;
    }

    InputStream fileContent;
    long size;
    try {
      Part filePart = request.getPart("passport");
      fileContent = filePart.getInputStream();
      size = filePart.getSize();
    } catch (ServletException | IOException e) {
      log.trace("Document data cannot be processed");
      throw new RuntimeException(e);
    }

    orderService.createOrder(order);
    userService.updateUserPassport(user.getId(), fileContent, size);

    userOrders = orderService.getOrdersByUserID(order.getUser().getId());

    SessionUtility.setOrders(request, userOrders);
    log.debug(Constants.COMMAND_FINISHED);
    return Constants.REDIRECT + Path.CATALOGUE_PAGE;
  }

  private Order shapeOrder(HttpServletRequest request) {
    Order order = new Order();
    order.setUser((User) request.getSession().getAttribute("user"));

    Cruise cruise =
        cruiseService.getCruiseByID((Long.parseLong(request.getParameter(FieldKey.ENTITY_ID))));
    order.setCruise(cruise);
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
