package com.sample.cwi.routes;

import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.domains.SessionData;
import com.sample.cwi.filters.CsrfFilter;
import com.sample.cwi.handlers.CommonRouteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static com.sample.cwi.SparkService.service;
import static com.sample.cwi.utils.SessionUtil.getSessionData;

public class CommonRoute {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRoute.class);

    private CommonRouteHandler commonRouteHandler;
    private FreeMarkerEngine freeMarkerEngine;


    public CommonRoute(CommonRouteHandler commonRouteHandler, FreeMarkerEngine freeMarkerEngine) {
        this.commonRouteHandler = commonRouteHandler;
        this.freeMarkerEngine = freeMarkerEngine;
    }

    private TemplateViewRoute viewCarListRoute = (request, response) -> {
        LOGGER.info("Invoking viewCarListRoute");
        SessionData sessionData = getSessionData(request, false);
        if(sessionData == null) {
            sessionData = getSessionData(request, true);
        }

        Map<String, Object> responseMap = commonRouteHandler.handleVehicleList();
        responseMap.put("sessionData", sessionData);
        //TODO : pagination to be added
        return new ModelAndView(responseMap, "common-page.ftl");
    };

    private TemplateViewRoute viewDetailsRoute = (request, response) -> {
        LOGGER.info("Invoking viewDetailsRoute");
        SessionData sessionData = getSessionData(request, false);
        String vehicleId = request.queryParams("vehicleId");

        Map<String, Object> responseMap = commonRouteHandler.handleViewDetails(vehicleId);
        responseMap.put("sessionData", sessionData);
        return new ModelAndView(responseMap, "common-page.ftl");
    };

    public void setUpRoutes(){

        service.get("/open/home", viewCarListRoute, freeMarkerEngine);

        service.post("/open/viewdetails", viewDetailsRoute, freeMarkerEngine);

        // Not enough time to implement post redirect get so simply redirecting on home page
        service.get("/open/viewdetails", viewCarListRoute, freeMarkerEngine);
    }

}
