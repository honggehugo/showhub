package com.showhub.show.util;

import com.showhub.show.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : ShiroUtil
 * @Description :
 * @date : 2021/2/28 15:38
 **/
public class ShiroUtil {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
