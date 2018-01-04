package cn.com.chng.erp.mappers;

import cn.com.chng.erp.domains.PowerStation;
import cn.com.chng.erp.utils.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PowerStationMapper {
    long insert(PowerStation powerStation);
    long update(PowerStation powerStation);
    PowerStation find(SearchModel searchModel);
    List<PowerStation> findAll(SearchModel searchModel);
    List<PowerStation> findAllPaged(SearchModel searchModel);
    long count(SearchModel searchModel);
}
