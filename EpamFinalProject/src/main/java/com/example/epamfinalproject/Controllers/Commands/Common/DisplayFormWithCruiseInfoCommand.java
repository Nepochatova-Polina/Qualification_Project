package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.OrderService;
import com.example.epamfinalproject.Utility.Constants;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * A command that makes a query to the database and passes complete information about the cruise to the session.
 * If the registered user is Administrator, then redirect to the editing page. Redirects the Client to the order page
 */
public class DisplayFormWithCruiseInfoCommand implements Command {
    private static final Logger log = LogManager.getLogger(DisplayFormWithCruiseInfoCommand.class);

    private final CruiseService cruiseService;
    private final OrderService orderService;
    public DisplayFormWithCruiseInfoCommand(CruiseService cruiseService, OrderService orderService) {
        this.cruiseService = cruiseService;
        this.orderService = orderService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        log.debug(Constants.COMMAND_STARTS);

        if(request.getSession().getAttribute("user")  == null){
            request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.ACCESS_DENIED);
            log.warn("Filter error! Accessed by an unauthenticated User");
            log.debug(Constants.COMMAND_FINISHED);
            return Constants.REDIRECT + Path.MAIN_PAGE;
        }
        User user = (User) request.getSession().getAttribute("user");

        Cruise cruise =
                cruiseService.getCruiseByID(Long.parseLong(request.getParameter(FieldKey.ENTITY_ID)));
        if(cruise == null){
            request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.CRUISE_INVALID);
            log.trace("Database has no Cruise with ID");
            log.debug(Constants.COMMAND_FINISHED);
            return Constants.REDIRECT + Path.MAIN_PAGE;
        }
        if(user.getRole().equals(UserRole.CLIENT)) {
            int freeSeats =
                    cruise.getShip().getPassengerCapacity()
                            - orderService.getBookedSeatsByCruiseID(cruise.getId());
            if (freeSeats == 0) {
                request.getSession().setAttribute(Constants.MESSAGE, MessageKeys.SHIP_INACCESSIBLE);
                log.trace("Cruise has no free seats");
                log.debug(Constants.COMMAND_FINISHED);
                return Constants.REDIRECT + Path.CATALOGUE_PAGE;
            }
            SessionUtility.setCruiseParamsForClient(request,cruise,freeSeats);
            log.debug(Constants.COMMAND_FINISHED);
            return Constants.REDIRECT + Path.ORDER_PAGE;
        }
        SessionUtility.setCruiseParamsForAdmin(request,cruise);

        log.debug(Constants.COMMAND_FINISHED);

        return Constants.REDIRECT + Path.EDIT_CRUISE_PAGE;
    }
}
