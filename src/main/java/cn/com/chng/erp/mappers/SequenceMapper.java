package cn.com.chng.erp.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liuyandong on 2017/6/16.
 */
@Mapper
public interface SequenceMapper {
    int nextValue(@Param("sequenceName") String sequenceName);
    int currentValue(@Param("sequenceName") String sequenceName);
}
