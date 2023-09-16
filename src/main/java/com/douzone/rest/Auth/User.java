package com.douzone.rest.Auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class User {
    private String userId;
    private String userPwd;
}
