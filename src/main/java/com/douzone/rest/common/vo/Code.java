package com.douzone.rest.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Code {
    private String parentId;
    private String codeId;
    private String codeName;
}
