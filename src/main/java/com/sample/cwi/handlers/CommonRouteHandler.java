package com.sample.cwi.handlers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.cwi.clients.CarWarehouseClient;
import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.domains.Car;
import com.sample.cwi.domains.Vehicle;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonRouteHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRouteHandler.class);

    private CarWarehouseClient carWarehouseClient;
    private PropertiesManager propertiesManager;

    public CommonRouteHandler(CarWarehouseClient carWarehouseClient, PropertiesManager propertiesManager) {
        this.carWarehouseClient = carWarehouseClient;
        this.propertiesManager = propertiesManager;
    }

    public Map<String, Object> handleVehicleList() {

        Map<String, Object> responseMap = new HashMap<>();
        List<Vehicle> vehicleList;
        String carWarehouseServiceUrl = propertiesManager.getStringProperty("carWarehouseServiceUrl");

        LOGGER.info("Calling carWarehouseService to getVehicleDetailList");
        vehicleList = carWarehouseClient.getHttpResponse(carWarehouseServiceUrl + "/getVehicleDetailList");
        responseMap.put("vehicleList", vehicleList);
        responseMap.put("body", "/pages/view-vehicle-list.ftl");
        return responseMap;
    }


}
