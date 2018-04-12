package com.explore.exception;

/**
 * Created by xiaohb on 2018/1/5.
 */
public class UserNotExistException extends RuntimeException {



    private String id;

    public UserNotExistException(String id){
        super("user not exist:"+id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
