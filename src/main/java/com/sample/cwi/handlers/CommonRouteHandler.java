package com.sample.cwi.handlers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.cwi.clients.CarWarehouseClient;
import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.domains.Car;
import com.sample.cwi.domains.Vehicle;
import com.sample.cwi.domains.Warehouse;
import org.apache.http.HttpEntity;
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
        String httpEntityString = carWarehouseClient.getHttpResponse(carWarehouseServiceUrl + "/getVehicleDetailList/");
        vehicleList = getExtractVehicleList(httpEntityString);
        responseMap.put("vehicleList", vehicleList);
        responseMap.put("body", "/pages/view-vehicle-list.ftl");
        return responseMap;
    }

    public Map<String, Object> handleViewDetails(String vehicleId) {

        Map<String, Object> responseMap = new HashMap<>();
        Warehouse warehouse;
        String carWarehouseServiceUrl = propertiesManager.getStringProperty("carWarehouseServiceUrl");

        LOGGER.info("Calling carWarehouseService to getVehicleDetails");
        String httpEntityString = carWarehouseClient.getHttpResponse(carWarehouseServiceUrl + "/getVehicleDetails/" + vehicleId);
        warehouse = getExtractVehicleDetails(httpEntityString);
        responseMap.put("warehouse", warehouse);
        responseMap.put("body", "/pages/view-details.ftl");
        return responseMap;
    }

    private List<Vehicle> getExtractVehicleList(String httpEntityString) {
        List<Vehicle> vehicleList = new ArrayList<>();
        if(httpEntityString != null){
            try {
                vehicleList = new ObjectMapper().readValue(httpEntityString, new TypeReference<List<Vehicle>>() {});
            } catch (IOException e) {
                LOGGER.error("Exception occurred while parsing vehicleList return by carWarehouseService", e);
            }
        }
        return vehicleList;
    }

    private Warehouse getExtractVehicleDetails(String httpEntityString) {
        Warehouse warehouse = null;
        if(httpEntityString != null){
            try {
                warehouse = new ObjectMapper().readValue(httpEntityString, new TypeReference<Warehouse>() {});
            } catch (IOException e) {
                LOGGER.error("Exception occurred while parsing vehicle details return by carWarehouseService", e);
            }
        }
        return warehouse;
    }




}
