package com.top.effitopia.service;

public interface RedisService {


    void setValues(String key, String value);

    void setValues(String key, String value, long duration);

    String getValue(String key);

    void deleteValue(String key);

    boolean existValue(String key);
}
