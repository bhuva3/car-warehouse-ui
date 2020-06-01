package com.sample.cwi;


import com.sample.cwi.clients.CarWarehouseClient;
import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.filters.CsrfFilter;
import com.sample.cwi.handlers.CommonRouteHandler;
import com.sample.cwi.routes.CommonRoute;
import com.sample.cwi.utils.PropertyFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;

public class SparkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkService.class);
    public static final String APP_PORT = "appPort";

    public static Service service;
    public static Components components;

    public static void main(String... args) throws IOException {

        service = Service.ignite();

        // Initialise picocontainer for dependencies injection
        initialiseComponentForDependencyInjection();

        PropertiesManager propertiesManager = new PropertiesManager(PropertyFileReader.readPropertyFile(args));
        components.set(PropertiesManager.class, propertiesManager);

        // this is option functionality, added to store session in datastore to overcome server failover scenario
        service.clusterSession(propertiesManager.getStringProperty("clusterNode"),
                null,
                propertiesManager.getStringProperty("httpSessionDatastore"),
                propertiesManager.getStringProperty("httpSessionCollection"),
                propertiesManager.getStringProperty("httpSessionDatastoreUrl"),
                propertiesManager.getIntProperty("scavengeInterval"));

        service.port(components.get(PropertiesManager.class).getIntProperty(APP_PORT));
        // admin port to be added using MetricRegistry

        components.set(CsrfFilter.class);
        components.set(CarWarehouseClient.class);
        components.set(FreeMarkerEngine.class, new FreeMarkerEngine());
        components.set(CommonRouteHandler.class);
        components.set(CommonRoute.class);
        components.get(CommonRoute.class).setUpRoutes();

        // checking Csrf token on request
        service.before("/open/*", (request, response) -> components.get(CsrfFilter.class).checkCsrfToken(request, components.get(FreeMarkerEngine.class)));
        service.before("/open/*", (request, response) -> components.get(CsrfFilter.class).generateCsrfToken(request));

        // resource filter can be added to rewrite static content url and set expiry of static content


    }

    private static void initialiseComponentForDependencyInjection() {
        components = new Components();
    }
}
