package com.explore.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping
    public String hello(){
        /*if(true){
            throw new UserNotExistException("12345123451111111111");
        }*/
        return "hello DemoApplication";
    }

    @PostMapping
    public String postHello(@RequestParam String id, @RequestParam String name){
        return "Hello POST";
    }

    @PutMapping("/{id}")
    public String putHello(@PathVariable String id, @RequestParam String name){
        return "Hello POST";
    }
}
