package com.example.epamfinalproject.Controllers;

import com.example.epamfinalproject.Controllers.Commands.Administrator.*;
import com.example.epamfinalproject.Controllers.Commands.Client.CreateOrderCommand;
import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.*;
import com.example.epamfinalproject.Database.Implementations.*;
import com.example.epamfinalproject.Services.*;
import com.example.epamfinalproject.Utility.Constants;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "controller", value = "/controller")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10, // 10 MB
    maxRequestSize = 1024 * 1024 * 100 // 100 MB
    )
public class Controller extends HttpServlet {
  private static final Logger log = LogManager.getLogger(Controller.class);

  private static final Map<String, Command> commands = new HashMap<>();

  private final OrderService orderService;
  private final CruiseService cruiseService;
  private final UserService userService;
  private final RouteService routeService;
  private final ShipService shipService;
  private final StaffService staffService;

  public Controller() {
    orderService = new OrderService(new OrderImplementation());
    cruiseService = new CruiseService(new CruiseImplementation());
    userService = new UserService(new UserImplementation());
    routeService = new RouteService(new RouteImplementation());
    shipService = new ShipService(new ShipImplementation());
    staffService = new StaffService(new StaffImplementation());
  }

  /** Inits all accessible commands and loggedUsers container */
  @Override
  public void init() {
    this.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

    commands.put(
        "login",
        new LoginCommand(userService, cruiseService, orderService, shipService, routeService));
    commands.put("signUp", new SignUpCommand(userService, cruiseService));
    commands.put("logout", new LogoutCommand());
    commands.put("profile", new ProfileCommand());
    commands.put("catalogue", new CatalogueCommand(cruiseService));
    commands.put("displayFormWithCruiseInfo", new DisplayFormWithCruiseInfoCommand(cruiseService, orderService));

    commands.put("createOrder", new CreateOrderCommand(orderService, userService, cruiseService));
    commands.put("confirmOrder", new ConfirmOrderCommand(orderService));
    commands.put("confirmCruise", new ConfirmCruiseCommand(cruiseService));
    commands.put("confirmAll", new ConfirmAllCommand(orderService));
    commands.put("createCruise", new CreateCruiseCommand(cruiseService, routeService, shipService, staffService));
    commands.put("deleteCruise", new DeleteCruiseCommand(cruiseService));
    commands.put("editCruise", new EditCruiseCommand(cruiseService, shipService, routeService));
    commands.put("resetFilter", new ResetFilterCommand(cruiseService));

    commands.put("changeLocale", new ChangeLocaleCommand());
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    log.debug("Controller starts");

    String commandName = request.getParameter("command");
    log.trace("Request parameter: command " + commandName);

    Command command = commands.getOrDefault(commandName, r -> Path.MAIN_PAGE);
    log.trace("Obtained command:" + command);

    String page = command.execute(request);
    request.getSession().setAttribute("message-displayed", false);

    if (page.contains(Constants.REDIRECT)) {
      log.trace("Redirect: " + page);
      log.debug("Controller finished, now go to address" + page);
      response.sendRedirect(page.replace("redirect:", request.getContextPath()));
    } else {
      log.trace("Forward address: " + page);
      log.debug("Controller finished, now go to forward address: " + page);
      request.getRequestDispatcher(page).forward(request, response);
    }
  }
}
