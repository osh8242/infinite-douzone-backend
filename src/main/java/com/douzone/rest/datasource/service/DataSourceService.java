package com.douzone.rest.datasource.service;

import com.douzone.rest.company.vo.DataSourceVo;
import com.douzone.rest.datasource.dao.DataSourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSourceService {

    private final DataSourceDao dataSourceDao;
    @Autowired
    public DataSourceService(DataSourceDao dataSourceDao) {
        this.dataSourceDao = dataSourceDao;
    }

    public List<DataSourceVo> getAllDataSourceVo(){
        System.out.println("DataSourceService.getAllDataSourceVo");
        List<DataSourceVo> result = null;
        try {
            result = dataSourceDao.getAllDataSourceVo();
        } catch (Exception e) {
            System.out.println("에러발생 : " + e);
        }
        return result;
    }

    public int insertDataSourceVo(DataSourceVo dataSourceVo){
        System.out.println("DataSourceService.insertDataSourceVo");
        int result =0;
        try {
            result = dataSourceDao.insertDataSourceVo(dataSourceVo);
        } catch (Exception e) {
            System.out.println("에러발생 : " + e);
        }
        return result;
    }

    public int updateDataSourceVo(DataSourceVo dataSourceVo){
        System.out.println("DataSourceService.updateDataSourceVo");
        int result = 0;
        try {
            result = dataSourceDao.updateDataSourceVo(dataSourceVo);
        } catch (Exception e) {
            System.out.println("에러발생 : " + e);
        }
        return result;
    }

    public int deleteDataSourceVo(DataSourceVo dataSourceVo){
        System.out.println("DataSourceService.deleteDataSourceVo");
        int result = 0 ;
        try {
            result = dataSourceDao.deleteDataSourceVo(dataSourceVo);
        } catch (Exception e) {
            System.out.println("에러발생 : " + e);
        }

        return result;
    }
}
