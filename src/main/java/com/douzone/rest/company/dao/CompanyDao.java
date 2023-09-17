package com.douzone.rest.company.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyDao {
    List<String> getTableNames(String sourceSchema);
    String getTableDDL(String tableName, String sourceSchema);
    void executeDDL(String ddl);
}
