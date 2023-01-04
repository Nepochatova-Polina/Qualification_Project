package com.example.epamfinalproject.Controllers.Commands;

import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Paths;
import com.example.epamfinalproject.Controllers.SessionUtility;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Services.*;
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
            return "/login/userLogin.jsp";
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
                                                            cruiseService.getAllCruises(),
                                                            orderService.getAllOrders(),
                                                            staffService.getAllStaff(),
                                                            shipService.getAllShips(),
                                                            routeService.getAllRoutes());
                log.debug("Logging in as ADMINISTRATOR");
                log.debug("Command finished");
                return Paths.ADMIN_PAGE;
            } else {
                SessionUtility.setUserParams(request, user, cruiseService.getAllCruises());
                log.debug("Logging in as USER");
                log.debug("Command Finished");
                return Paths.USER_PAGE;
            }
        } else {
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            log.trace("Invalid User parameters");
            return "redirect:" + Paths.LOGIN_PAGE;
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request) {
        return (user != null) &&
                (request.getParameter(FieldKey.PASSWORD).equals(user.getPassword()));
    }
}
