package com.douzone.rest.swsm.dao;

import com.douzone.rest.swsm.vo.Swsm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

//전체조회 getAll[VO명]
//조건조회 get[VO명]by[PK명]

@Mapper
public interface SwsmDao {

    public int insertSwsm(Swsm swsm);
    Swsm getSwsmByCdEmp(Swsm swsm);
    List<Swsm> getAllSwsm();
    List<Swsm> getCodeNameRrnByWithholdingYear(Swsm swsm);
    public List<Swsm> getSwsmListForSwsm(Map<String, String> map);

    public int deleteSwsmByCdEmp(Swsm swsm);
    int insertSwsmEmp(Swsm swsm);

    int updateSwsmByEmp(Swsm swsm);

    int updateSwsm(Swsm swsm);

//    int insertSwsm(Swsm swsm);
//    int deleteSwsm(Swsm swsm);
//  int insertSwsmByEmp(Swsm swsm);
}
