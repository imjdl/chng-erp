package cn.com.chng.erp.utils;

import cn.com.chng.erp.constants.Constants;
import org.apache.commons.collections.MapUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by liuyandong on 2017/5/11.
 */
public class PropertyUtils {
    private static Properties properties = null;

    public static String getProperty(String propertyKey) throws IOException {
        return getProperties().getProperty(propertyKey);
    }

    public static String getProperty(String propertyKey, String defaultPropertyValue) throws IOException {
        return getProperties().getProperty(propertyKey, defaultPropertyValue);
    }

    public static Properties getProperties() throws IOException {
        if (MapUtils.isEmpty(properties)) {
            loadProperties();
        }
        return properties;
    }

    public static void loadProperties() throws IOException {
        properties = new Properties();
        InputStream inputStream = PropertyUtils.class.getClassLoader().getResourceAsStream(Constants.PRODUCTION_PROPERTIES);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Constants.CHARSET_UTF_8);
        properties.load(inputStreamReader);
        inputStreamReader.close();
        inputStream.close();
    }
}
