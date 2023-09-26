package com.douzone.rest.auth.dao;

import com.douzone.rest.auth.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    UserVo findUser(UserVo user);

    int register(UserVo user);
}
