package com.example.demo;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Guarded Suspension 模式
 * @author wangcz7
 * @Created 2021/2/21 8:48 下午.
 */
public class GuardedTest {

    static GuardedObject guard = new GuardedObject();

    public static void main(String[] args) {


        Thread t1 = new Guest();
        t1.start();

        Thread t2 = new Thread(() -> {
            guard.onChange(new Object());
            System.out.println("收拾完毕。。。");
        });
        t2.start();

    }

    static class Guest extends Thread{
        @Override
        public void run(){
            System.out.println("等位。。。。");
            guard.get(Objects::nonNull);
            System.out.println("开始吃饭。。。。");
        }
    }
}
class GuardedObject<T> {
    T obj;

    final Lock lock = new ReentrantLock();

    final Condition done = lock.newCondition();

    final int timeout = 1;

    T get(Predicate<T> p){
        lock.lock();
        try {
            while (!p.test(obj)){
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return obj;
    }
    void  onChange(T obj){
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}