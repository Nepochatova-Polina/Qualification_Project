package com.example.epamfinalproject.Controllers.Commands.Administrator.Create;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Staff;
import com.example.epamfinalproject.Services.StaffService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateStaffCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateStaffCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");
        Staff staff = new Staff();
        staff.setFirstName(request.getParameter(FieldKey.FIRST_NAME));
        staff.setLastName(request.getParameter(FieldKey.LAST_NAME));
        staff.setShip_id(Integer.parseInt(request.getParameter(FieldKey.CRUISE_SHIP_ID)));

        if (Validation.validateStaffFields(staff)) {
            StaffService staffService = new StaffService();
            staffService.registerStaff(staff);
            log.debug("Record was added");
            log.debug("Command Finished");
        } else {
            request.getSession().setAttribute("message", MessageKeys.SIGN_UP_INVALID);
            log.trace("Invalid Staff parameters");
            log.debug("Command Finished");
            return Path.CREATE_STAFF_PAGE;
        }
        return "redirect:" + Path.ADMINISTRATOR_PAGE;
    }
}
