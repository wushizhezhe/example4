package com.example.demo.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangcz7
 * @Created 2021/3/1 11:11 上午.
 */
public class LRUCache3 {
    private Map<Integer, Integer> cache;
    private final int cazpacity;

    public LRUCache3(int cazpacity) {
        this.cazpacity = cazpacity;
        cache = new LinkedHashMap(cazpacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > cazpacity;
            }
        };
    }
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
