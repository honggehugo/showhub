package com.showhub.show;

import java.util.Map;

/**
 * @author : hzh
 * @version : 1.0
 * @ClassName : IRedisServie
 * @Description :
 * @date : 2021/2/27 14:06
 **/
public class IRedisServie {
    public interface IRedisService {

        // 加入元素
        void setValue(String key, Map<String, Object> value);
        // 加入元素
        void setValue(String key, String value);
        // 加入元素
        void setValue(String key, Object value);
        // 获取元素
        Object getMapValue(String key);
        // 获取元素
        Object getValue(String key);
    }

}
