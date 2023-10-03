package com.douzone.rest.datasource.dao;

import com.douzone.rest.company.vo.DataSourceVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper
public interface DataSourceDao {
    public List<DataSourceVo> getAllDataSourceVo();
    public int insertDataSourceVo(DataSourceVo dataSourceVo);
    public int deleteDataSourceVo(DataSourceVo dataSourceVo);
    public int updateDataSourceVo(DataSourceVo dataSourceVo);
}
