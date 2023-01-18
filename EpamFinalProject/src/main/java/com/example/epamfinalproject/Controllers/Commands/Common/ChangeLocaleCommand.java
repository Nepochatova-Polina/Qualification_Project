package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {
    private static final Logger log = LogManager.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        request.getSession().setAttribute("locale", request.getParameter("locale").equals("en") ? "en" : "ua");
        log.debug("Command finished");
        return "redirect:" + request.getParameter("page-path");
    }
}
