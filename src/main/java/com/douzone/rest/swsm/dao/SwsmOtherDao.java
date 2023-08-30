package com.douzone.rest.swsm.dao;

import com.douzone.rest.swsm.vo.SwsmOther;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SwsmOtherDao {
    List<SwsmOther> getAllSwsmOther();
    List<SwsmOther> getSwsmOtherByCdEmp(SwsmOther swsmOther);
//    List<SwsmOther> getAllSwsmOther(SwsmOther swsmOther);

    int insertSwsmOther(SwsmOther swsmOther);
    int updateSwsmOther(SwsmOther swsmOther);
    int deleteSwsmOther(SwsmOther swsmOther);
}
