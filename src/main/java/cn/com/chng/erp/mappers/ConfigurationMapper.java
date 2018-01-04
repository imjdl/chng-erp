package cn.com.chng.erp.mappers;

import cn.com.chng.erp.domains.Configuration;
import cn.com.chng.erp.utils.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfigurationMapper {
    List<Configuration> findAll(SearchModel searchModel);
}
