package com.douzone.rest.company.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DataSourceVo implements Serializable {
    @Serial
    private static final long serialVersionUID  = 1L;

    private String companyCode;
    private String password;
    private Integer status;

    public DataSourceVo(String companyCode, String password) {
        this.companyCode = companyCode;
        this.password = password;
        this.status = 1;
    }
}
