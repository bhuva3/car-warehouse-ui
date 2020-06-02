package com.sample.cwi.routes;

import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.domains.*;
import com.sample.cwi.filters.CsrfFilter;
import com.sample.cwi.handlers.CommonRouteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sample.cwi.SparkService.service;
import static com.sample.cwi.utils.SessionUtil.SESSION;
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
        request.session(false).attribute(SESSION, sessionData);
        //TODO : pagination to be added
        return new ModelAndView(responseMap, "common-page.ftl");
    };


    private TemplateViewRoute viewCartRoute = (request, response) -> {
        LOGGER.info("Invoking viewCartRoute");
        SessionData sessionData = getSessionData(request, false);

        Map<String, Object> responseMap = commonRouteHandler.handleViewCart();
        responseMap.put("sessionData", sessionData);
        if(sessionData.getCartDetailList().size() > 0){
            double cartTotal = sessionData.getCartDetailList().stream().mapToDouble(CartDetail::getVehiclePrice)
                    .sum();
            responseMap.put("cartTotal", cartTotal);
        }
        request.session(false).attribute(SESSION, sessionData);
        return new ModelAndView(responseMap, "common-page.ftl");
    };

    private TemplateViewRoute viewDetailsRoute = (request, response) -> {
        LOGGER.info("Invoking viewDetailsRoute");
        SessionData sessionData = getSessionData(request, false);
        String vehicleId = request.queryParams("vehicleId");

        Map<String, Object> responseMap = commonRouteHandler.handleViewDetails(vehicleId);
        responseMap.put("sessionData", sessionData);
        request.session(false).attribute(SESSION, sessionData);
        return new ModelAndView(responseMap, "common-page.ftl");
    };

    private TemplateViewRoute addToCartRoute = (request, response) -> {
        LOGGER.info("Invoking addToCartRoute");
        SessionData sessionData = getSessionData(request, false);
        long vehicleId = Long.parseLong(request.queryParams("vehicleId"));
        String vehicleMake = request.queryParams("vehicleMake");
        String vehicleModel = request.queryParams("vehicleModel");
        double vehiclePrice = Double.parseDouble(request.queryParams("vehiclePrice"));

        CartDetail cartDetail = new CartDetail(vehicleId, vehicleMake, vehicleModel, vehiclePrice);
        if(sessionData.getCartDetailList()==null){
            sessionData.setCartDetailList(new ArrayList<>());
        }
        sessionData.getCartDetailList().add(cartDetail);

        // better if we can validate same vehicle details in datastore before adding to cart.
        Map<String, Object> responseMap = commonRouteHandler.handleVehicleList();

        responseMap.put("message", "Car added to your cart successfully !");
        responseMap.put("sessionData", sessionData);
        request.session(false).attribute(SESSION, sessionData);
        return new ModelAndView(responseMap, "common-page.ftl");
    };

    public void setUpRoutes(){

        service.get("/open/home", viewCarListRoute, freeMarkerEngine);

        service.post("/open/viewdetails", viewDetailsRoute, freeMarkerEngine);

        // Not enough time to implement post redirect get so simply redirecting on home page
        service.get("/open/viewdetails", viewCarListRoute, freeMarkerEngine);

        service.post("/open/addtocart", addToCartRoute, freeMarkerEngine);

        // Not enough time to implement post redirect get so simply redirecting on home page
        service.get("/open/addtocart", viewCarListRoute, freeMarkerEngine);

        service.get("/open/viewcart", viewCartRoute, freeMarkerEngine);
    }

}
