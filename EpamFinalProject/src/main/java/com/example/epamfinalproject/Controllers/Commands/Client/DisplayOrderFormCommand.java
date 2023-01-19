package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.OrderService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DisplayOrderFormCommand implements Command {
  private static final Logger log = LogManager.getLogger(DisplayOrderFormCommand.class);

  private final CruiseService cruiseService;
  private final OrderService orderService;

  public DisplayOrderFormCommand(CruiseService cruiseService, OrderService orderService) {
    this.cruiseService = cruiseService;
    this.orderService = orderService;
  }

  @Override
  public String execute(HttpServletRequest request) {
    log.debug(Constants.COMMAND_STARTS);

    Cruise cruise =
        cruiseService.getCruiseByID(Long.parseLong(request.getParameter(FieldKey.ENTITY_ID)));
    int freeSeats =
        cruise.getShip().getPassengerCapacity()
            - orderService.getBookedSeatsByCruiseID(cruise.getId());
    if (freeSeats == 0) {
      request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.SHIP_INACCESSIBLE);
      log.trace("Cruise is full");
      log.debug(Constants.COMMAND_FINISHED);
      return Constants.REDIRECT + Path.CATALOGUE_PAGE;
    }
    request.getSession().setAttribute("cruise", cruise);
    request.getSession().setAttribute("freeSeats", freeSeats);

    log.debug(Constants.COMMAND_FINISHED);

    return Constants.REDIRECT + Path.ORDER_PAGE;
  }
}
