package com.showhub.show.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.showhub.show.common.dto.loginDto;
import com.showhub.show.common.lang.Result;
import com.showhub.show.entity.User;
import com.showhub.show.service.UserService;
import com.showhub.show.util.jwtUtils;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : AccountController
 * @Description :
 * @date : 2021/2/28 10:58
 **/
@RestController
public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    jwtUtils jwtUtils;
    @PostMapping("/login")
    public Result login(@Validated @RequestBody loginDto loginDto, HttpServletResponse response){

        User user = userService.getOne(new QueryWrapper<User>().eq("username",loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
           String pwd =  SecureUtil.md5(loginDto.getPassword());
            return Result.fail("密码不正确");
        }
        String jwt  = jwtUtils.generateToken(user.getId());

        //设置头信息
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        //返回
        return Result.succ(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("email",user.getEmail()).map());
    }
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        //SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
