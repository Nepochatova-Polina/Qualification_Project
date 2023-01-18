package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.Order;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.*;
import com.example.epamfinalproject.Utility.Encryptor;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        String login = request.getParameter(FieldKey.LOGIN);

        if (login == null || login.equals("")) {
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            log.trace("Invalid User parameters");
            return "redirect:" + Path.LOGIN_PAGE;
        }

        UserService userService = new UserService();
        User user = userService.getUserByLogin(login);


        if (validateUserData(user, request)) {
            request.getSession().setAttribute("role", user.getRole().toString());

            CruiseService cruiseService = new CruiseService();
            OrderService orderService = new OrderService();
            StaffService staffService = new StaffService();
            ShipService shipService = new ShipService();
            RouteService routeService = new RouteService();

            if (user.getRole().equals(UserRole.ADMINISTRATOR)) {

                SessionUtility.setParamsForAdmin(request, user,
                        userService.getClientUsers(),
                        cruiseService.getActualCruises(),
                        orderService.getAllUnconfirmedOrders(),
                        staffService.getAllStaff(),
                        shipService.getAllShips(),
                        routeService.getAllRoutes());

                log.debug("Logging in as ADMINISTRATOR");
                log.debug("Command finished");
                return "redirect:" + Path.ADMINISTRATOR_PAGE;
            } else {
                log.debug("Logging in as USER");
                log.debug("Command Finished");

                String preCommand = (String) request.getSession().getAttribute("preCommand");
                if (preCommand != null && preCommand.equals("displayOrderForm")) {
                    updateSession(request, user);
                    return "redirect:" + Path.ORDER_PAGE;
                }


                SessionUtility.setParamsForClient(request, user,
                        cruiseService.getActualCruisesForPage(FieldKey.PAGE_SIZE, 0),
                        orderService.getOrdersByUserID(user.getId()));
                return "redirect:" + Path.CLIENT_PAGE;
            }
        } else {
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            log.trace("Invalid User parameters");
            return "redirect:" + Path.LOGIN_PAGE;
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request) {
        return (user != null) &&
                (Encryptor.check(user.getPassword(), request.getParameter(FieldKey.PASSWORD)));
    }

    /**
     * Updating data in session, set User data, selected Cruise data, availability for this cruise and all User's orders.
     *
     * @param request
     * @param user    logged user
     */
    private void updateSession(HttpServletRequest request, User user) {
        OrderService orderService = new OrderService();
        CruiseService cruiseService = new CruiseService();
        Cruise cruise = cruiseService.getCruiseByID(user.getId());
        List<Order> orders = orderService.getOrdersByUserID(user.getId());
        int freeSeats = cruise.getShip().getPassengerCapacity() - orderService.getBookedSeatsByCruiseID(cruise.getId());
        SessionUtility.setCruiseParamsForClient(request, user, cruise, freeSeats);
    }
}
