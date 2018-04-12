package com.explore.web.controller;

import com.explore.dto.User;
import com.explore.dto.UserQueryCondition;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohb on 2018/1/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /* 获取当前用户 */
    @GetMapping("/auth")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/authentication")
    public Object getCurrentUser(Authentication authentication){
        return authentication;
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
    /* 获取当前用户  end*/

    @GetMapping()
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 2, size = 17, sort = "username,age") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.JSON_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {
//        throw new RuntimeException(id);
        System.out.println("进入getUserInfo方法");
        User user = new User();
        user.setUsername("joy");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user){
        System.out.println(user.getBirthday());
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));

        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@PathVariable String id,@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField()+" "+fieldError.getDefaultMessage());
            });
        }
        System.out.println(id);
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }



}
