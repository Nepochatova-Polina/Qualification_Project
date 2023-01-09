package com.example.epamfinalproject.Controllers.Commands;

import com.example.epamfinalproject.Controllers.Commands.Client.CreateOrderCommand;
import com.example.epamfinalproject.Controllers.Path;
import com.example.epamfinalproject.Controllers.SessionUtility;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CatalogCommand implements Command{
    private static final Logger log = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");
        CruiseService cruiseService = new CruiseService();
        List<Cruise> cruises = cruiseService.getAllActualCruises();
        if(cruises == null){
            return Path.MAIN_PAGE;
        }
        SessionUtility.setCruiseParams(request,cruises);
        log.debug("Command finished");
        return Path.CATALOG_PAGE;
    }
}
