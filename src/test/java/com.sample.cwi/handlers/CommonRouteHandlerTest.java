package com.sample.cwi.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.cwi.clients.CarWarehouseClient;
import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.domains.Car;
import com.sample.cwi.domains.Vehicle;
import com.sample.cwi.domains.Warehouse;
import com.sample.cwi.domains.WarehouseLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommonRouteHandlerTest {

    CommonRouteHandler commonRouteHandler;

    @Mock
    PropertiesManager propertiesManager;

    @Mock
    CarWarehouseClient carWarehouseClient;

    @Before
    public void setUp(){
        commonRouteHandler = new CommonRouteHandler(carWarehouseClient, propertiesManager);
    }

    @Test
    public void shouldReturnValidResponseWithVehicleList() throws JsonProcessingException {

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "Volvo", "s101", 2019, 100,false , "2020-06-01", null));
        when(propertiesManager.getStringProperty(anyString())).thenReturn("mockedCarWarehouseServiceUrl");
        when(carWarehouseClient.getHttpResponse(anyString())).thenReturn(new ObjectMapper().writeValueAsString(vehicleList));

        Map<String, Object> resultMap = commonRouteHandler.handleVehicleList();
        Assert.assertNotNull(resultMap);
        Assert.assertEquals(2, resultMap.size());
        Assert.assertEquals("/pages/view-vehicle-list.ftl", resultMap.get("body"));
        Assert.assertNotNull(resultMap.get("vehicleList"));
        Assert.assertEquals(vehicleList.size(), ((List<Vehicle>) resultMap.get("vehicleList")).size());
        Assert.assertEquals(1, ((List<Vehicle>) resultMap.get("vehicleList")).get(0).getId());

    }

    @Test
    public void shouldReturnValidResponseWithWarehouseDetails() throws JsonProcessingException {

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "Volvo", "s101", 2019, 100,false , "2020-06-01", null));

        Warehouse warehouse = new Warehouse(1, "Test Warehouse", new Car("test carlocation", vehicleList), new WarehouseLocation());

        when(propertiesManager.getStringProperty(anyString())).thenReturn("mockedCarWarehouseServiceUrl");
        when(carWarehouseClient.getHttpResponse(anyString())).thenReturn(new ObjectMapper().writeValueAsString(warehouse));

        Map<String, Object> resultMap = commonRouteHandler.handleViewDetails("1");
        Assert.assertNotNull(resultMap);
        Assert.assertEquals(2, resultMap.size());
        Assert.assertEquals("/pages/view-details.ftl", resultMap.get("body"));
        Assert.assertNotNull(resultMap.get("warehouse"));
        Assert.assertEquals(1, ((Warehouse)resultMap.get("warehouse")).getId());
        Assert.assertEquals("Test Warehouse", ((Warehouse)resultMap.get("warehouse")).getName());

    }
}