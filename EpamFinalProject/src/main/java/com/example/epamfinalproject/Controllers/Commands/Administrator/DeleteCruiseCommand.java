package com.example.epamfinalproject.Controllers.Commands.Administrator;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteCruiseCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteCruiseCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        List<String> params = List.of(request.getParameterValues(FieldKey.ENTITY_ID));

        if (params.isEmpty()) {
            log.trace("Not a Ship was checked");
            return "redirect:"+ Path.ADMINISTRATOR_PAGE;
        }
        CruiseService cruiseService = new CruiseService();
        for (String param : params) {
            cruiseService.deleteCruiseByID(Integer.parseInt(param));
            log.debug("Record with id " + Integer.parseInt(param) + "was deleted");
        }

        SessionUtility.setCruisesParams(request,cruiseService.getAllCruisesForPage(FieldKey.PAGE_SIZE,0));
        log.debug("Command Finished");
        return "redirect:"+ Path.ADMINISTRATOR_PAGE;
    }
}
