package com.showhub.show.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : JwtToken
 * @Description :
 * @date : 2021/2/18 22:19
 **/
public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }

}
