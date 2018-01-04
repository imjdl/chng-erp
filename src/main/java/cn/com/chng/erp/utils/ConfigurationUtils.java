package cn.com.chng.erp.utils;

import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.domains.Configuration;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;

public class ConfigurationUtils {
    public static void loadConfigurations(List<Configuration> configurations) throws IOException {
        for (Configuration configuration : configurations) {
            String key = String.format("_%s_%s_%s", configuration.getDeploymentEnvironment(), configuration.getPartitionCode(), configuration.getServiceName());
            CacheUtils.hset(key, configuration.getConfigurationKey(), configuration.getConfigurationValue());
        }
    }

    public static String getConfiguration(String configurationKey) throws IOException {
        String configurationValue = PropertyUtils.getProperty(configurationKey);
        if (StringUtils.isBlank(configurationValue)) {
            String deploymentEnvironment = PropertyUtils.getProperty(Constants.DEPLOYMENT_ENVIRONMENT);
            String partitionCode = PropertyUtils.getProperty(Constants.PARTITION_CODE);
            String serviceName = PropertyUtils.getProperty(Constants.SERVICE_NAME);
            String key = String.format("_%s_%s_%s", deploymentEnvironment, partitionCode, serviceName);
            configurationValue = CacheUtils.hget(key, configurationKey);
        }
        return configurationValue;
    }

    public static String getConfiguration(String configurationKey, String defaultValue) throws IOException {
        String configurationValue = getConfiguration(configurationKey);
        if (StringUtils.isNotBlank(configurationValue)) {
            return configurationValue;
        }
        return defaultValue;
    }
}
