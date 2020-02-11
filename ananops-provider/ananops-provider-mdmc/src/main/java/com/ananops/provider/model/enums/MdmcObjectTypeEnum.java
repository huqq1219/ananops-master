package com.ananops.provider.model.enums;

public enum MdmcObjectTypeEnum {

    NORMAL(1, "常规维修类型"),

    IMC(2, "巡检报修类型"),

    ALARM(3, "报警急单类型")
    ;
    private Integer code;

    private String description;

    MdmcObjectTypeEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode(){
        return code;
    }
}
