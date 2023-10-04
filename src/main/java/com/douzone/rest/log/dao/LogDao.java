package com.douzone.rest.log.dao;

import com.douzone.rest.log.vo.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao {
    int insertLog(Log log);
}
