package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author wangcz7
 * @Created 2021/2/20 9:10 下午.
 */
public class MRTest {

    public static void main(String[] args) {
        String[] fc = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        MR m = new MR(fc, 0, fc.length);
        Map<String, Integer> r = forkJoinPool.invoke(m);
        System.out.println(r);
    }
}

class MR extends RecursiveTask<Map<String, Integer>> {

    final String[] fc;
    final int a;
    final int b;

    MR(String[] fc, int a, int b) {
        this.fc = fc;
        this.a = a;
        this.b = b;
    }

    @Override
    protected Map<String, Integer> compute() {
        if (b - a == 1) {
            return c(fc[a]);
        }
        int t = (b + a) / 2;
        MR m1 = new MR(fc, a, t);
        m1.fork();
        MR m2 = new MR(fc, t, b);
        return merge(m2.compute(), m1.join());
    }

    Map<String, Integer> merge(Map<String, Integer> r1, Map<String, Integer> r2) {
        Map<String, Integer> result =
                new HashMap<>();
        result.putAll(r1);
        // 合并结果
        r2.forEach((k, v) -> {
            Integer c = result.get(k);
            if (c != null)
                result.put(k, c+v);
            else
                result.put(k, v);
        });
        return result;
    }


    Map<String, Integer> c(String s) {
        Map<String, Integer> r = new HashMap<>();
        String[] s1 = s.split(" ");
        for (String s2 : s1) {
            if (r.containsKey(s2)) {
                r.put(s2, r.get(s2) + 1);
            } else {
                r.put(s2, 1);
            }
        }
        return r;
    }
}
