package com.explore.enums;

public enum SexEnum {
    MALE("1","男"),FEMALE("2","女");

    String value;
    String name;

    SexEnum(String value, String name) {
        this.value=value;
        this.name=name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
    public static SexEnum getByValue(String value) {
        if(value ==null){
            return null;
        }
        for(SexEnum typeEnum : SexEnum.values()) {
            if(typeEnum.value.equals(value)) {
                return typeEnum;
            }
        }
        throw new IllegalArgumentException("No element matches " + value);
    }

}
