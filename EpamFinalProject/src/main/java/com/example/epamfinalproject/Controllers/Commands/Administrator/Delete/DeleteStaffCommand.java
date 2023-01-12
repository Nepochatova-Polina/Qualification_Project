package com.example.epamfinalproject.Controllers.Commands.Administrator.Delete;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Services.StaffService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteStaffCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteStaffCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        StaffService staffService = new StaffService();

        staffService.deleteStaffByID(Integer.parseInt(request.getParameter(FieldKey.ENTITY_ID)));
        SessionUtility.setStaffParams(request,staffService.getAllStaff());

        log.debug("Command Finished");
        return Path.DELETE_SHIP_PAGE;
    }
}
