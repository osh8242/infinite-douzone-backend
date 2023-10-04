package com.douzone.rest.common.dao;

import com.douzone.rest.common.vo.Code;
import com.douzone.rest.emp.vo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonDao {
    List<Code> getAllCode(Code code);
}
