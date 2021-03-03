package com.showhub.show.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : MybatisPlusConfig
 * @Description :
 * @date : 2021/2/7 11:57
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.showhub.show.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
