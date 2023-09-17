package com.douzone.rest.auth.vo;


import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo {
        private String message;
        private String token;
        private UserVo user;
    }
