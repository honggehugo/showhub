package com.showhub.show;

import com.showhub.show.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : sbootTest
 * @Description :
 * @date : 2021/2/27 14:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShowApplication.class)
public class sbootTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void getRedis(){
        redisTemplate.opsForValue().set("java","我爱java");
        String str =(String) redisTemplate.opsForValue().get("java");
        System.out.println(str);
        User mUser=new User();
        mUser.setId(1);
        mUser.setCreated("aa");
        redisTemplate.opsForValue().set("user",mUser);
        User user =(User) redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }
}
