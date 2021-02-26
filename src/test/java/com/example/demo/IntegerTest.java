package com.example.demo;

import org.junit.jupiter.api.Test;

/**
 * @author wangcz7
 * @Created 2020/8/7 4:06 下午.
 */
public class IntegerTest {

    @Test
    void test1() {
        int i = primePalindrome(3503054);
        System.out.println(i);
    }
    public int primePalindrome(int N) {
        if(N < 2){
            return 2;
        }
        while(true){
            boolean ishui = ishuiwenshu(N);
            if(ishui){
                boolean isSu = isSu(N, 2);
                if(isSu){
                    return N;
                }
            }
            N++;
        }
    }

    public boolean ishuiwenshu(int n){
        byte[] s = String.valueOf(n).getBytes();
        for(int i = 0;i < s.length / 2; i++){
            if(s[i] != s[s.length - 1 - i]){
                return false;
            }
        }
        return true;
    }

    public boolean isSu(int n, int a ){

        if (n / 2 < a){
            return true;
        }
        if (n % a == 0){
            return false;
        } else{
            return isSu(n, ++a);
        }

    }
}
