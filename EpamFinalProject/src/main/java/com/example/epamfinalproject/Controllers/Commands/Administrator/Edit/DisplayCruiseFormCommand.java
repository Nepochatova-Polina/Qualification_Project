package com.example.epamfinalproject.Controllers.Commands.Administrator.Edit;

import com.example.epamfinalproject.Controllers.Commands.Administrator.Create.CreateCruiseCommand;
import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DisplayCruiseFormCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateCruiseCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");
        CruiseService cruiseService = new CruiseService();
        Cruise cruise = cruiseService.getCruiseByID(Long.parseLong(request.getParameter(FieldKey.ENTITY_ID)));
        if(cruise == null){
            request.getSession().setAttribute("message", MessageKeys.CRUISE_INVALID);
            log.trace("Invalid Cruise parameters");
            log.debug("Command Finished");
            return "redirect:" + Path.ADMINISTRATOR_PAGE;
        }
        SessionUtility.setCruiseParamsForAdmin(request,cruise);
        log.debug("Command Finished");

        return "redirect:" + Path.EDIT_CRUISE_PAGE;
    }
}
