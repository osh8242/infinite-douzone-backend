package com.douzone.rest.log.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Log {
    private String companyCode;
    private String userId;
    private String ipAddress;
    private String token;
    private String requestUrl;
    private String message;
    private String queryString;
    private String requestMethod;
}
