package com.showhub.show.service;

import com.showhub.show.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hugo
 * @since 2021-02-08
 */
public interface UserService extends IService<User> {
    Object getAll();

}
