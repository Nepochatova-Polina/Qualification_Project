package com.example.epamfinalproject.Controllers.Commands.Administrator.Delete;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.StaffService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteStaffCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteStaffCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        List<String> params = List.of(request.getParameterValues(FieldKey.ENTITY_ID));

        if (params.isEmpty()) {
            log.trace("Not a Ship was checked");
            return"redirect:"+  Path.DELETE_STAFF_PAGE;
        }
        StaffService staffService = new StaffService();
        for (String param : params) {
            staffService.deleteStaffByID(Integer.parseInt(param));
            log.debug("Record with id " + Integer.parseInt(param) + "was deleted");
        }
        SessionUtility.setStaffParams(request,staffService.getAllStaff());

        log.debug("Command Finished");
        return "redirect:"+ Path.DELETE_STAFF_PAGE;
    }
}
