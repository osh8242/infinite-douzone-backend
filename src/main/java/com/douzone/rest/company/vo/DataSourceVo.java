package com.douzone.rest.company.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class DataSourceVo implements Serializable {
    @Serial
    private static final long serialVersionUID  = 1L;

    private String companyCode;
    private String password;
}
