package com.douzone.rest.auth;

import com.douzone.rest.auth.dao.UserDao;
import com.douzone.rest.auth.jwt.JwtService;
import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public int register(UserVo user) {
        System.out.println("Service resgither");
        System.out.println(user);
        int result;
        ResponseVo response = new ResponseVo();
        UserVo findUserId = userDao.findUser(user);
        if (findUserId != null) {
            System.out.println("already exist ID");
            result = 0;
        } else {
            // bcryptpasswordEncoder
            String encodedPassword = passwordEncoder.encode(user.getUserPwd());
            System.out.println("암호화 비밀번호 출력");
            System.out.println(encodedPassword);
            user.setUserPwd(encodedPassword);

            result = userDao.register(user);
            System.out.println("no one id  so reulst:::" + result);
        }

//        hashed_password = bcrypt.hashpw(a.encode('utf-8'), bcrypt.gensalt())
//        user.setUserPwd();

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

    public int checkVaildId(UserVo user) {
        int result;
        System.out.println("servicee idddd check param:"+user);
        UserVo userResult = userDao.findUser(user);
        if (userResult == null)
            result = 1;
        else
            result = 0;

        return result;
    }
    public int checkVaildEmail (UserVo user) {
        int result;
        System.out.println("servicee idddd check param:"+user);
        UserVo userResult = userDao.findEmail(user);
        if (userResult == null)
            result = 1;
        else
            result = 0;

        return result;
    }
}
