package com.douzone.rest.swsm.dao;

import com.douzone.rest.swsm.vo.Swsm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//전체조회 getAll[VO명]
//조건조회 get[VO명]by[PK명]

@Mapper
public interface SwsmDao {
    List<Swsm> getAllSwsm();
    List<Swsm> getCodeNameRrnByWithholdingYear(Swsm swsm);
    int insertSwsm(Swsm swsm);
    int insertSwsmByEmp(Swsm swsm);

    int updateSwsmByEmp(Swsm swsm);
    int updateSwsm(Swsm swsm);
    int deleteSwsm(Swsm swsm);


}
