package com.example.epamfinalproject.Controllers.Commands.Administrator.Create;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Controllers.Commands.Common.LoginCommand;
import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class FilterShipsCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");

        LocalDate start_date = LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_LEAVING)));
        LocalDate end_date = LocalDate.parse(String.valueOf(request.getParameter(FieldKey.CRUISE_ARRIVING)));

        ShipService shipService = new ShipService();
        List<Ship> allShips = shipService.getAllShips();
        List<Ship> unsuitableShips = shipService.getShipsByDates(start_date, end_date);
        if(unsuitableShips != null) {
            allShips.removeAll(unsuitableShips);
        }
        if(allShips == null){
            request.getSession().setAttribute("shipMessage", MessageKeys.SHIP_NOT_FOUND);
        }
        SessionUtility.setShipsParams(request, allShips);

        log.debug("Command Finished");
        return "Admin/createCruise.jsp";
    }
}
