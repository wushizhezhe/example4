package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangcz7
 * @Created 2021/2/21 8:04 下午.
 */
public class SimpleDateFormatTest {

    static CountDownLatch latch = new CountDownLatch(1);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {
        Now now ;
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1);
            now = new Now();
            now.start();
        }
        latch.countDown();
    }

    static class Now extends Thread{

        final Date now ;

        Now() {
            this.now = new Date();
        }


        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " date:" + dateFormat.format(now));
            System.out.println(Thread.currentThread().getName() + " safeDate:" + SafeDateFormat.get().format(now));
        }
    }

    static class SafeDateFormat{
        static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal
                .withInitial(() ->  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS") );
        static SimpleDateFormat get(){
            return  dateFormatThreadLocal.get();
        }
    }
}
