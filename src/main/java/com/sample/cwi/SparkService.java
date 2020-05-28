package com.sample.cwi;


import com.sample.cwi.config.PropertiesManager;
import com.sample.cwi.utils.PropertyFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;

public class SparkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkService.class);
    public static final String APP_PORT = "appPort";

    public static Service service;
    public static Components components;

    public static void main(String... args) {

        service = Service.ignite();

        initialiseComponentForDependencyInjection();

        PropertiesManager propertiesManager = new PropertiesManager(PropertyFileReader.readPropertyFile(args));
        components.set(PropertiesManager.class, propertiesManager);

        service.port(components.get(PropertiesManager.class).getIntProperty(APP_PORT));
        // admin port to be added using MetricRegistry

    }

    private static void initialiseComponentForDependencyInjection() {
        components = new Components();
    }
}
