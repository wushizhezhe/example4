package com.example.demo;

import org.junit.jupiter.api.Test;

/**
 * @author wangcz7
 * @Created 2021/3/1 3:00 下午.
 */

public class RetryTest {

    @Test
    public void continueTest() {

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j == 3) {
                    continue;
                }
            }

        }

    }

    @Test
    public void breakTest() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j == 3) {
                    break;
                }
            }

        }
    }

    @Test
    public void retryContinueTest() {
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j == 3) {
                    continue retry;
                }

            }

        }
    }

    @Test
    public void retryBreakTest() {
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j == 3) {
                    break retry;
                }
            }

        }
    }
}
