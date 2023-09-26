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

    public int register(UserVo user){
        System.out.println("Service resgither");
        System.out.println(user);
//        hashed_password = bcrypt.hashpw(a.encode('utf-8'), bcrypt.gensalt())
//        user.setUserPwd();
        int result=userDao.register(user);
        System.out.println("reulst:::"+result);
        return result;
    }

    public ResponseVo findUser(UserVo userVo) {
        ResponseVo response = new ResponseVo();
        UserVo user = userDao.findUser(userVo);
        String result;

        if (user == null) {
            response.setMessage("CHECK_ID");
        } else if (user.getUserPwd().equals(userVo.getUserPwd())) {
            // JWT 토큰
            String jwtToken = jwtService.generateToken(user.getUserId());
            System.out.println("TOKEN...");
            System.out.println(jwtToken);
            response.setMessage("SUCCESS");
            response.setToken(jwtToken);
            response.setUser(user);
        } else {
            response.setMessage("CHECK_PWD");
        }
        return response;
    }
}
