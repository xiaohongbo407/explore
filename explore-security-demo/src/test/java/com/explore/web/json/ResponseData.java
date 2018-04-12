package com.explore.web.json;

import java.io.Serializable;

/**
 * @program: explore
 * @description: 响应
 * @author: XiaoHongBo
 * @create: 2018-03-28 17:33
 **/
public class ResponseData<T> implements Serializable {

    private String status;
    T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
