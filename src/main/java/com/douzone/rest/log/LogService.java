package com.douzone.rest.log;
import com.douzone.rest.log.dao.LogDao;
import com.douzone.rest.log.vo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogService {
    private LogDao logDao;

    @Autowired
    public LogService(LogDao logDao) {
        this.logDao = logDao;
    }

    public int insertLog(Log log){
        int result = 0;
        try {
            result = logDao.insertLog(log);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("insertLog에서 터짐");
        }
        return result;
    }
}
