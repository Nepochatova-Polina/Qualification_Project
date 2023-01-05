package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");


        log.debug("Command finished");
        return null;
    }
}
