package com.example.test0.http;


import java.io.Serializable;

/**
 * 秘钥实体类
 */
public class KeyBean implements Serializable {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
