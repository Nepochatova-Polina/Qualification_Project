package com.example.epamfinalproject.Controllers.Commands.Common;

import com.example.epamfinalproject.Controllers.Commands.Command;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Utility.FieldKey;
import com.example.epamfinalproject.Utility.SessionUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CatalogueCommand implements Command {
    private static final Logger log = LogManager.getLogger(CatalogueCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");
        CruiseService cruiseService = new CruiseService();

        int recordsCount = cruiseService.getNumberOfActualCruises();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Cruise> cruises = cruiseService.getActualCruisesForPage(FieldKey.PAGE_SIZE, (page - 1) * FieldKey.PAGE_SIZE);

        SessionUtility.setCruisesParams(request, cruises);
        int pageCount = (int) Math.ceil(recordsCount * 1.0 / FieldKey.PAGE_SIZE);
        request.getSession().setAttribute("numOfPages", pageCount);
        request.getSession().setAttribute("currentPage", page);

        log.debug("Command finished");
        return "redirect:" + request.getParameter("page-path");
    }
}
