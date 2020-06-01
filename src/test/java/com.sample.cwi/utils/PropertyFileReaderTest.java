package com.sample.cwi.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@RunWith(MockitoJUnitRunner.class)
public class PropertyFileReaderTest {


    @Test
    public void shouldReadPropertyValueFromPropertyFileWithoutAnyError() throws IOException {

        Properties properties = PropertyFileReader.readPropertyFile(new String[]{"server", "config/car-warehouse-ui.yml"});
        Assert.assertNotNull(properties);
        Assert.assertEquals(properties.getProperty("appPort"), "4567");

    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowFileNotFoundExceptionWhenFileNotPresent() throws IOException {
        PropertyFileReader.readPropertyFile(new String[]{"server", "config/file-not-present.yml"});
    }

}