package com.example.epamfinalproject.Controllers.Commands;

import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Controllers.SessionUtility;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Services.*;
import com.example.epamfinalproject.Utility.Encryptor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        String login = request.getParameter(FieldKey.LOGIN);

        if (login == null || login.equals("")) {
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            log.trace("Invalid User parameters");
            return Path.LOGIN_PAGE;
        }

        UserService userService = new UserService();
        User user = userService.getUserByLogin(login);

        if (validateUserData(user, request)) {
            CruiseService cruiseService = new CruiseService();
            ShipService shipService = new ShipService();
            RouteService routeService = new RouteService();
            StaffService staffService = new StaffService();
            OrderService orderService = new OrderService();
            if (user.getRole().equals(UserRole.ADMINISTRATOR)) {


                SessionUtility.setUserParams(request, user, userService.getAllUsers(),
                                                            cruiseService.getAllActualCruises(),
                                                            orderService.getAllOrders(),
                                                            staffService.getAllStaff(),
                                                            shipService.getAllShips(),
                                                            routeService.getAllRoutes());
                log.debug("Logging in as ADMINISTRATOR");
                log.debug("Command finished");
                return Path.ADMINISTRATOR_PAGE;
            } else {
                SessionUtility.setUserParams(request, user, cruiseService.getAllActualCruises());
                log.debug("Logging in as USER");
                log.debug("Command Finished");
                return Path.CLIENT_PAGE;
            }
        } else {
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            log.trace("Invalid User parameters");
            return "redirect:" + Path.LOGIN_PAGE;
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request) {
        return (user != null) &&
                (Encryptor.check(user.getPassword(),request.getParameter(FieldKey.PASSWORD)));
    }
}
