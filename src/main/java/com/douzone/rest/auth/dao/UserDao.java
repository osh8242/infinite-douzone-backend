package com.douzone.rest.auth.dao;

import com.douzone.rest.auth.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    UserVo findUserByCd(UserVo user);
    UserVo findUser(UserVo user);

    UserVo findEmail(UserVo user);

    int register(UserVo user);
}
