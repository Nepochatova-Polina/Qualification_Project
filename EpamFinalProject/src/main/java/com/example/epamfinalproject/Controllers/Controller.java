package com.example.epamfinalproject.Controllers;

import com.example.epamfinalproject.Controllers.Commands.*;
import com.example.epamfinalproject.Controllers.Commands.Administrator.ConfirmOrderCommand;
import com.example.epamfinalproject.Controllers.Commands.Administrator.CreateCruiseCommand;
import com.example.epamfinalproject.Controllers.Commands.Administrator.DeleteCruiseCommand;
import com.example.epamfinalproject.Controllers.Commands.Administrator.DisplayCruiseFormCommand;
import com.example.epamfinalproject.Controllers.Commands.Administrator.EditCruiseCommand;
import com.example.epamfinalproject.Controllers.Commands.Client.CreateOrderCommand;
import com.example.epamfinalproject.Controllers.Commands.Client.DisplayOrderFormCommand;
import com.example.epamfinalproject.Controllers.Commands.Common.*;
import com.example.epamfinalproject.Controllers.Commands.Common.ProfileCommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet(name = "controller", value = "/controller")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Controller extends HttpServlet {
    private static final Map<String, Command> commands = new HashMap<>();
    private static final Logger log = LogManager.getLogger(Controller.class);

    /**
     * Inits all accessible commands and loggedUsers container
     */
    @Override
    public void init() {

        this.getServletContext().setAttribute("loggedUsers", new HashSet<String>());


        commands.put("login", new LoginCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("catalogue", new CatalogueCommand());
        commands.put("filterCruises", new FilterCruisesCommand());
        commands.put("displayOrderForm", new DisplayOrderFormCommand());

        commands.put("createOrder", new CreateOrderCommand());
        commands.put("confirmOrder", new ConfirmOrderCommand());
        commands.put("createCruise", new CreateCruiseCommand());
        commands.put("deleteCruise", new DeleteCruiseCommand());
        commands.put("editCruise", new EditCruiseCommand());
        commands.put("displayCruiseForm", new DisplayCruiseFormCommand());

        commands.put("changeLocale", new ChangeLocaleCommand());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("Controller starts");

        String commandName = request.getParameter("command");
        log.trace("Request parameter: command " + commandName);

        Command command = commands.getOrDefault(commandName, (r) -> Path.MAIN_PAGE);
        log.trace("Obtained command:" + command);

        String page = command.execute(request);
        request.getSession().setAttribute("message-displayed", false);

        if (page.contains("redirect:")) {
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
