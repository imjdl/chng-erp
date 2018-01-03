package cn.com.chng.erp.mappers;

import cn.com.chng.erp.domains.SystemPrivilege;
import cn.com.chng.erp.domains.SystemUser;
import cn.com.chng.erp.utils.SearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by liuyandong on 2017/4/18.
 */
@Mapper
public interface SystemUserMapper {
    long insert(SystemUser systemUser);
    long update(SystemUser systemUser);
    SystemUser find(SearchModel searchModel);
    List<SystemUser> findAll(SearchModel searchModel);
    List<SystemUser> findAllPaged(SearchModel searchModel);
    List<SystemPrivilege> findAllPrivileges(@Param("serviceName") String serviceName, @Param("userId") BigInteger userId);
    long updateLoginLog(@Param("remoteAddress") String remoteAddress, @Param("loginCount") Integer loginCount, @Param("userId") BigInteger userId);
    SystemUser findByCodeOrEmailOrMobile(@Param("loginName") String loginName);
    long count(SearchModel searchModel);
}
