package com.example.demo.thread;

import java.util.concurrent.RecursiveTask;

/**
 * 斐波那契数列
 * 0 1 1 2 3 5 8 ......
 *
 * @author wangcz7
 * @Created 2021/2/20 8:27 下午.
 */
public class FibonacciTest {


    Integer[] results = new Integer[100];
    public static void main(String[] args) {
        FibonacciTest test = new FibonacciTest();
//        for (int i = 0; i < 100; i++) {
        System.out.println(
                test.fibonacci(5)
        );
//        }

        /*ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        Fibonacci fibonacci = new Fibonacci(45);
        Integer invoke = forkJoinPool.invoke(fibonacci);
        System.out.println(invoke);*/
    }


    int fibonacci(int n) {
        if (results[n] != null){
            return results[n];
        }
        if (n < 2) {
            results[n] = n;
            return n;
        }
        int r =  fibonacci(n - 1) + fibonacci(n - 2);
        results[n] = r;
        return r;
    }


}

class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}
