package cn.com.chng.erp.services;

import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.domains.Configuration;
import cn.com.chng.erp.mappers.ConfigurationMapper;
import cn.com.chng.erp.utils.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConfigurationService {
    @Autowired
    private ConfigurationMapper configurationMapper;

    @Transactional(readOnly = true)
    public List<Configuration> findAllByDeploymentEnvironment(String deploymentEnvironment) {
        SearchModel searchModel = new SearchModel(true);
        searchModel.addSearchCondition("deployment_environment", Constants.SQL_OPERATION_SYMBOL_EQUALS, deploymentEnvironment);
        return configurationMapper.findAll(searchModel);
    }
}
