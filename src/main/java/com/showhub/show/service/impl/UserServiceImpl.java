package com.showhub.show.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.showhub.show.entity.User;
import com.showhub.show.mapper.UserMapper;
import com.showhub.show.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hugo
 * @since 2021-02-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private  UserMapper userMapper;
    @Override
    public List<User> getAll() {

        return null;
    }
}
