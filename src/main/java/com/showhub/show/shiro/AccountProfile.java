package com.showhub.show.shiro;
import lombok.Data;

import java.io.Serializable;
/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : AccountProfile
 * @Description :
 * @date : 2021/2/27 16:50
 **/



@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}