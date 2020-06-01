package com.sample.cwi.handlers;

import com.sample.cwi.clients.CarWarehouseClient;
import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.domains.Vehicle;
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
    public void shouldReturnValidResponseMapWithoutAnyException(){

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle());
        when(propertiesManager.getStringProperty(anyString())).thenReturn("mockedCarWarehouseServiceUrl");
        when(carWarehouseClient.getHttpResponse(anyString())).thenReturn(vehicleList);

        Map<String, Object> resultMap = commonRouteHandler.handleVehicleList();
        Assert.assertNotNull(resultMap);
        Assert.assertEquals(resultMap.size(), 2);
        Assert.assertEquals(resultMap.get("body"), "/pages/view-vehicle-list.ftl");
        Assert.assertNotNull(resultMap.get("vehicleList"));

    }

    @Test
    public void shouldThrowValidResponseMapWithoutAnyException(){

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle());
        when(propertiesManager.getStringProperty(anyString())).thenReturn("mockedCarWarehouseServiceUrl");
        when(carWarehouseClient.getHttpResponse(anyString())).thenReturn(vehicleList);

        Map<String, Object> resultMap = commonRouteHandler.handleVehicleList();
        Assert.assertNotNull(resultMap);
        Assert.assertEquals(resultMap.size(), 2);
        Assert.assertEquals(resultMap.get("body"), "/pages/view-vehicle-list.ftl");
        Assert.assertNotNull(resultMap.get("vehicleList"));
        Assert.assertEquals(resultMap.get("vehicleList"), vehicleList);

    }
}