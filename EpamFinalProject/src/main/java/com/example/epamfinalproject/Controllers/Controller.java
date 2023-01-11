package com.example.epamfinalproject.Controllers;

import com.example.epamfinalproject.Controllers.Commands.*;
import com.example.epamfinalproject.Controllers.Commands.Client.CreateOrderCommand;
import com.example.epamfinalproject.Controllers.Commands.Client.DisplayOrderFormCommand;
import com.example.epamfinalproject.Controllers.Commands.FilterCruisesCommand;
import com.example.epamfinalproject.Controllers.Commands.ProfileCommand;
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
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
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
        commands.put("createOrder", new CreateOrderCommand());
        commands.put("displayOrderForm", new DisplayOrderFormCommand());
        commands.put("catalogue", new CatalogueCommand());
        commands.put("filterCruises", new FilterCruisesCommand());
        commands.put("profile", new ProfileCommand());
//        commands.put("deleteCruise", new DeleteFacultyCommand());
//        commands.put("editCruise", new EditFacultyCommand());
//        commands.put("createOrder", new CreateSubmissionCommand());
//        commands.put("checkOrder", new CheckSubmissionCommand());
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

        Command command = commands.getOrDefault(commandName, (r) -> com.example.epamfinalproject.Controllers.Path.MAIN_PAGE);
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
