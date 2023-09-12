package com.douzone.rest.emp.vo;

import lombok.Data;

@Data
public class EmpMenuUsage {
    private String cdEmp;
    private String nmKrname; //편의를 위해 추가한 컬럼, DB엔 없다
    private String useMenuList;
}
