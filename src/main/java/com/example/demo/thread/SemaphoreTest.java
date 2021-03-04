package com.example.demo.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author wangcz7
 * @Created 2021/3/4 1:56 下午.
 */
public class SemaphoreTest {


    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        final CountDownLatch countDownLatch = new CountDownLatch(8);

        for (int i = 0; i < 8; i++) {
            int finalI = i;
            if (i == 5) {
                Thread.sleep(1000);
                new Thread(() -> {
                    stopCarNotWait(semaphore, finalI);
                    countDownLatch.countDown();
                }).start();
                continue;
            }
            new Thread(() -> {
                stopCarWait(semaphore, finalI);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        log("总共还剩：" + semaphore.availablePermits() + "个车位");
    }

    private static void log(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.format(formatter) + "  " + s);
    }

    private static void stopCarWait(Semaphore semaphore, int finalI) {
        String format = String.format("车牌号%d", finalI);
        try {
            semaphore.acquire(1);
            log(format + "找到车位了，去停车了");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(1);
            log(format + "开走了");
        }

    }

    private static void stopCarNotWait(Semaphore semaphore, int finalI) {
        String format = String.format("车牌号%d", finalI);
        if(semaphore.tryAcquire()){
            log(format + "找到车位了，去停车了");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log(format + "开走了");
            semaphore.release();

        } else {
            log(format + "没有停车位了，不在这里等了去其他地方停车去了");
        }
    }

}
