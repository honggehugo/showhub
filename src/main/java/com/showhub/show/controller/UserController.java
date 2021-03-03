package com.showhub.show.controller;


import com.showhub.show.common.lang.Result;
import com.showhub.show.entity.User;
import com.showhub.show.mapper.UserMapper;
import com.showhub.show.service.UserService;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hugo
 * @since 2021-02-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequiresAuthentication
    @GetMapping("/{id}")
    public Result test(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return Result.succ(user);
    }
    @RequestMapping("/h")
    public Object getAll(){
        return userService.count();
    }
    @RequestMapping("/insert")
    public Object insert(){

        return userService.getAll();

    }
    @PostMapping("/i")
    public Result save(@Validated @RequestBody User user) {

        return Result.succ(user);
    }

}
