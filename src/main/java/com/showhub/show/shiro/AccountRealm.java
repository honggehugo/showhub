package com.showhub.show.shiro;

import com.showhub.show.entity.User;
import com.showhub.show.service.UserService;
import com.showhub.show.util.jwtUtils;
import io.jsonwebtoken.Jwt;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : AccountRealm
 * @Description :
 * @date : 2021/2/18 22:16
 **/
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    com.showhub.show.util.jwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    //获取用户权限，把用户信息封装返回给shiro
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken)token;
        String userId = jwtUtils.getClaimByToken((String)jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if(user == null){
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getStatus() == -1){
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        try {
            BeanUtils.copyProperties(user,profile);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }


}
