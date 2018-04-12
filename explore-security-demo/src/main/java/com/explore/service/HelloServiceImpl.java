package com.explore.service;

import org.springframework.stereotype.Service;

/**
 * Created by xiaohb on 2018/1/4.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        return "hello "+name;
    }
}
