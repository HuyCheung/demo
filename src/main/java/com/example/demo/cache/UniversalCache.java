package com.example.demo.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用缓存
 *
 * @author Huy Cheung
 * @date 2022/05/26
 */
public interface UniversalCache {
    ConcurrentHashMap<String,String> UNIVERSAL_CACHE=new ConcurrentHashMap<>();

}