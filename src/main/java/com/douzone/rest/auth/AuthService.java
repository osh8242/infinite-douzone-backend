package com.douzone.rest.auth;

import com.douzone.rest.auth.dao.UserDao;
import com.douzone.rest.auth.jwt.JwtService;
import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public int register(UserVo user) {
        logger.info("Service register: {}", user);
        UserVo findUserId = userDao.findUser(user);
        if (findUserId != null) {
            logger.warn("User with given ID already exists.");
            return 0;
        } else {
            String salt=generateSalt();
            user.setSalt(salt);
            String encodedPassword = passwordEncoder.encode(user.getUserPwd()+salt);
            user.setUserPwd(encodedPassword);
            return userDao.register(user);
        }
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }


    public ResponseVo findUser(UserVo userVo, String clientIp) {
        ResponseVo response = new ResponseVo();
        UserVo user = userDao.findUser(userVo);

        if (user == null) {
            response.setMessage("CHECK_ID");
            return response;
        }

        String salt=user.getSalt();

        if (passwordEncoder.matches(userVo.getUserPwd()+salt, user.getUserPwd())) {
            String jwtToken = jwtService.generateAccessToken(user.getUserId(), user.getCompanyCode(), clientIp);
            logger.info("Generated token: {}", jwtToken);
            response.setMessage("SUCCESS");
            response.setToken(jwtToken);
            response.setUser(user);
        } else {
            response.setMessage("CHECK_PWD");
        }

        return response;
    }


    public int checkValidCd(UserVo user) {
        UserVo userResult = userDao.findUserByCd(user);
        return userResult == null ? 1 : 0;
    }
    public int checkValidId(UserVo user) {
        UserVo userResult = userDao.findUser(user);
        return userResult == null ? 1 : 0;
    }

    public int checkValidEmail(UserVo user) {
        UserVo userResult = userDao.findEmail(user);
        return userResult == null ? 1 : 0;
    }

    public UserVo IdByEmail(UserVo user){
        return userDao.findEmail(user);
    }
}
