package com.douzone.rest.auth;

import com.douzone.rest.auth.dao.UserDao;
import com.douzone.rest.auth.jwt.JwtService;
import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtService jwtService;

    public ResponseVo findUser(UserVo userVo) {
        ResponseVo response = new ResponseVo();
        UserVo user = userDao.findUser(userVo);
        String result;


        if (user == null) {
            response.setMessage("아이디가 일치하지 않습니다.");
        } else if (user.getUserPwd().equals(userVo.getUserPwd())) {

         // JWT 토큰
            String jwtToken = jwtService.generateToken(user.getUserId());

            response.setMessage("SUCCESS");
            response.setToken(jwtToken);
            response.setUser(user);

        } else {
            response.setMessage("비밀번호가 일치 하지 않습니다..");
        }
        return response;
    }
}
