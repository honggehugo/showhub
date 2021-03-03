package com.showhub.show.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showhub.show.common.lang.Result;
import com.showhub.show.entity.Blog;
import com.showhub.show.service.BlogService;
import com.showhub.show.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hugo
 * @since 2021-02-17
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1")Integer currentPage){
        Page page = new Page(currentPage,5);
         IPage pageData = blogService.page(page,new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }
    @GetMapping("/blogs/{id}")
    public Result detail(@PathVariable(name = "1")Long id){

        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"查无此纪录");

        return Result.succ(blog);
    }

    /**
     * @RequiresAuthentication  需要登录得到权限才能访问此接口
     * @Validated 实体类检验注解
     * @param blog
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/blogs/edit")
    public Result edit(@Validated  @RequestBody Blog blog){
        Blog temp = null;
        if(blog.getId() != null){
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"没有权限编辑");
        }else{
            temp = new Blog();
            //temp.setUserId(ShiroUtil.getProfile().getId());
            Long l = ShiroUtil.getProfile().getId();
            System.out.println(l);
            temp.setUserId((long)1);
            Long aLong =  ShiroUtil.getProfile().getId();
            temp.setCreated(LocalDateTime.now());
            temp.setStatus("0");
        }
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);
        return Result.succ(null);
    }
}
