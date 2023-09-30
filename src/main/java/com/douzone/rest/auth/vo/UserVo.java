package com.douzone.rest.auth.vo;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String companyCode;
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
//    private String birth;
    private String theme;
    private String phone;
    private String empImg;
}
