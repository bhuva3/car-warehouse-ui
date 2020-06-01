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

import java.util.Map;

import static com.sample.cwi.SparkService.service;
import static com.sample.cwi.utils.SessionUtil.getSessionData;

public class CommonRoute {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRoute.class);

    private PropertiesManager propertiesManager;
    private CommonRouteHandler commonRouteHandler;
    private FreeMarkerEngine freeMarkerEngine;
    private CsrfFilter csrfFilter;

    public CommonRoute(PropertiesManager propertiesManager, CommonRouteHandler commonRouteHandler, FreeMarkerEngine freeMarkerEngine, CsrfFilter csrfFilter) {
        this.propertiesManager = propertiesManager;
        this.commonRouteHandler = commonRouteHandler;
        this.freeMarkerEngine = freeMarkerEngine;
        this.csrfFilter = csrfFilter;
    }

    private TemplateViewRoute viewCarListRoute = (request, response) -> {
        LOGGER.info("Invoking viewCarListRoute");
        SessionData sessionData = getSessionData(request, false);
        Map<String, Object> responseMap = commonRouteHandler.handleVehicleList();
        return new ModelAndView(responseMap, "common-page.ftl");
    };


    public void setUpRoutes(){

        service.get("/open/home", viewCarListRoute, freeMarkerEngine);
    }

}
