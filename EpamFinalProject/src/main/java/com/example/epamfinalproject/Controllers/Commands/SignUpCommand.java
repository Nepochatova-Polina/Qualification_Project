package com.example.epamfinalproject.Controllers.Commands;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command{
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");
        return null;
    }
}
