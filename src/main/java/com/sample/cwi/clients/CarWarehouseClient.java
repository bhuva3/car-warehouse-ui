package com.sample.cwi.clients;

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


public class CarWarehouseClient {


    private static final Logger LOGGER = LoggerFactory.getLogger(CarWarehouseClient.class);

    public CarWarehouseClient() {
    }

    /**
     *  Http rest client to call external services.
     *  Interservice authentication should be added part of restClient
     * @param endpointUri
     * @return
     */
    public String getHttpResponse(String endpointUri) {
        LOGGER.info("Invoking getHttpResponse for endpoint [{}]", endpointUri);

        HttpGet request = new HttpGet(endpointUri);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            if(response !=null ){
                if(HttpStatus.OK_200 == response.getStatusLine().getStatusCode()){
                    return EntityUtils.toString(response.getEntity());
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


}
