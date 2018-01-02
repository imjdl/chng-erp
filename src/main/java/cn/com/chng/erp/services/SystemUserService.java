package cn.com.chng.erp.services;

import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.domains.SystemPrivilege;
import cn.com.chng.erp.domains.SystemUser;
import cn.com.chng.erp.mappers.SystemUserMapper;
import cn.com.chng.erp.utils.SearchModel;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Transactional(readOnly = true)
    public List<SystemPrivilege> obtainAllPrivileges(BigInteger userId) {
        return systemUserMapper.findAllPrivileges(Constants.SERVICE_NAME_ERP, userId);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> obtainUserInfo(BigInteger userId) {
        SearchModel searchModel = new SearchModel(true);
        searchModel.addSearchCondition("id", Constants.SQL_OPERATION_SYMBOL_EQUALS, userId);
        SystemUser systemUser = systemUserMapper.find(searchModel);
        Validate.notNull(systemUser, "用户不存在！");
        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("user", systemUser);
        return userInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateLoginLog(String remoteAddress, Integer loginCount, BigInteger userId) {
        systemUserMapper.updateLoginLog(remoteAddress, loginCount, userId);
    }

    @Transactional(readOnly = true)
    public SystemUser findByCodeOrEmailOrMobile(String loginName) {
        return systemUserMapper.findByCodeOrEmailOrMobile(loginName);
    }
}
