package com.explore.dto;

import com.explore.enums.SexEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public class UserConstExt extends User{

    private String sexName;

    public String getSexName() {
        if(sexName !=null){
            return sexName;
        }
        try {
            this.sexName = SexEnum.getByValue(this.getSex()).getName();
            return sexName;
        } catch (Exception e) {
            return sexName;
        }
    }

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setId("1");
        user.setUsername("2");
        user.setBirthday(new Date());
        user.setSex("1");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(user);
        System.out.println("User = "+json);

        UserConstExt temp =mapper.readValue(json ,UserConstExt.class);
        System.out.println(temp.getSexName());

        String json1 = mapper.writeValueAsString(temp);
        System.out.println("UserConstExt = "+json1);


    }
}
