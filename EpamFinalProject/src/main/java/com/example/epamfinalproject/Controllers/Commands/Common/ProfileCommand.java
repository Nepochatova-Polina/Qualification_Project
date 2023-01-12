package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.LoginCommand;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements Command {
    private static final Logger log = LogManager.getLogger(ProfileCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals(UserRole.ADMINISTRATOR)){
            log.debug("Redirect to " + UserRole.ADMINISTRATOR +" page");
            log.debug("Command Finished");
            return "redirect:" + Path.ADMINISTRATOR_PAGE;
        }
        log.debug("Redirect to " + UserRole.CLIENT +" page");
        log.debug("Command Finished");
        return Path.CLIENT_PAGE;

    }
}
