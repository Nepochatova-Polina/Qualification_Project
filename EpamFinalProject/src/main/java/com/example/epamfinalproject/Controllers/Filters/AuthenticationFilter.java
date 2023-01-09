package com.example.epamfinalproject.Controllers.Filters;

import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.User;
import jakarta.servlet.annotation.WebFilter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;

@WebFilter(filterName = "authenticationFilter")
public class AuthenticationFilter implements Filter {
    private static final Logger log = Logger.getLogger(AuthenticationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter starts");
        final HttpServletRequest req = (HttpServletRequest) request;
        final User user = (User) req.getSession().getAttribute("user");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");

        //If user already logged on another browser or device, refuses to logging in him twice
        if (password == null && login != null) {
            if (loggedUsers.contains(login)) {

                req.getSession().setAttribute("message", MessageKeys.ALREADY_LOGGED);
                log.trace("Set the request attribute: message --> " + MessageKeys.ALREADY_LOGGED);

                request.getRequestDispatcher(Path.MAIN_PAGE)
                        .forward(request, response);
            }
        }

        //Prevent error message from displaying after page refreshing
        if (req.getSession().getAttribute("message-displayed") != null &&
                (boolean) req.getSession().getAttribute("message-displayed")) {
            req.getSession().removeAttribute("message");
        } else {
            req.getSession().setAttribute("message-displayed", true);
        }

        //Set default locale if user has not been on site yet
//        if (req.getSession().getAttribute("locale") == null) {
//            req.getSession().setAttribute("locale", "EN");
//        }

        //Set default sort parameters if user has not been on site yet
//        if (req.getSession().getAttribute("alphabetSort") == null) {
//            req.getSession().setAttribute("alphabetSort", 1);
//            req.getSession().setAttribute("studentSort", 3);
//            req.getSession().setAttribute("stateFundedSort", 5);
//        }

        log.debug("Filter finished");

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
