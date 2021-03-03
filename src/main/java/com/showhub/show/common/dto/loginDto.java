package com.showhub.show.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : loginDto
 * @Description :
 * @date : 2021/2/28 11:01
 **/
@Data
public class loginDto implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
