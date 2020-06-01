package com.sample.cwi.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sample.cwi.domains.Vehicle;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CarWarehouseClient {


    private static final Logger LOGGER = LoggerFactory.getLogger(CarWarehouseClient.class);

    public CarWarehouseClient() {
    }

    public List<Vehicle> getHttpResponse(String endpointUri) {
        LOGGER.info("Invoking getHttpResponse for endpoint [{}]", endpointUri);

        HttpGet request = new HttpGet(endpointUri);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            if(response !=null ){
                if(HttpStatus.OK_200 == response.getStatusLine().getStatusCode()){
                    return getExtractVehicleList(response) ;
                } else {
                    LOGGER.error("Endpoint [{}] responded with status [{}]",endpointUri, response.getStatusLine().getStatusCode() );
                    return null;
                }
            }

        } catch (ClientProtocolException e) {
            LOGGER.error("Exception occurred while calling endpoint [{}] [{}]", endpointUri, e);

        } catch (IOException e) {
            LOGGER.error("Exception occurred while calling endpoint [{}] [{}]", endpointUri, e);
        }
        // specific customException can be used here on basis of requirement

        return null;
    }

    private List<Vehicle> getExtractVehicleList(CloseableHttpResponse httpResponse) {
        List<Vehicle> vehicleList = new ArrayList<>();
        if(httpResponse != null && httpResponse.getEntity()!=null){
            try {
                vehicleList = new ObjectMapper().readValue(EntityUtils.toString(httpResponse.getEntity()), new TypeReference<List<Vehicle>>() {});
            } catch (IOException e) {
                LOGGER.error("Exception occurred while parsing vehicleList return by carWarehouseService", e);
            }
        }
        return vehicleList;
    }
}
