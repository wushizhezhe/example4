package com.example.demo.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author wangcz7
 * @Created 2021/3/1 10:52 上午.
 */
public class LURCache2 {

    LinkedHashMap<Integer, Integer> cache;

    int capacity;

    public LURCache2(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    public int get(int key){
        int result = -1;
        if (cache.containsKey(key)){
            result = cache.get(key);
            cache.remove(key);
        }
        put(key, result);
        return result;
    }

    public void put(int key, int val){
        cache.remove(key);

        if (capacity == cache.size()){
            Iterator<Integer> iterator = cache.keySet().iterator();
            if (iterator.hasNext()){
                cache.remove(iterator.next());
            }
        }
        cache.put(key, val);
    }
}
