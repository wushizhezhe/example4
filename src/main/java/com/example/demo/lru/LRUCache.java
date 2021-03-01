package com.example.demo.lru;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author wangcz7
 * @Created 2021/3/1 10:07 上午.
 */
public class LRUCache {

    LinkedList<Node> cache;

    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedList<>();
    }

    public int get(int key) {
        Iterator<Node> iterator = cache.descendingIterator();
        int result = -1;
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.key == key){
                result = next.val;
                iterator.remove();
                put(key, result);
                break;
            }
        }
        return result;
    }

    public void put(int key, int value){
        Iterator<Node> iterator = cache.iterator();
        while (iterator.hasNext()){
            Node next = iterator.next();
            if (next.key == key){
                iterator.remove();
                break;
            }
        }

        if (capacity == cache.size()){
            cache.removeFirst();
        }
        cache.add(new Node(key, value));
    }
}

class Node {
    int key;
    int val;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}