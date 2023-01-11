package com.example.epamfinalproject.Controllers.Commands.Client;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;

import javax.servlet.http.HttpServletRequest;

public class UserAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:" + Path.CLIENT_PAGE;
    }
}
