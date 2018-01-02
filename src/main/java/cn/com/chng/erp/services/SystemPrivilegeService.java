package cn.com.chng.erp.services;

import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.domains.SystemPrivilege;
import cn.com.chng.erp.mappers.SystemPrivilegeMapper;
import cn.com.chng.erp.utils.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemPrivilegeService {
    @Autowired
    private SystemPrivilegeMapper systemPrivilegeMapper;

    @Transactional(readOnly = true)
    public List<SystemPrivilege> findAll(String serviceName) {
        SearchModel searchModel = new SearchModel(true);
        searchModel.addSearchCondition("service_name", Constants.SQL_OPERATION_SYMBOL_EQUALS, serviceName);
        return systemPrivilegeMapper.findAll(searchModel);
    }
}
