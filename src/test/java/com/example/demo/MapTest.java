package com.example.demo;

import org.junit.jupiter.api.Test;

/**
 * @author wangcz7
 * @Created 2020/7/30 9:56 上午.
 */
public class MapTest {

    @Test
    public  void  testMapUtil(){



    }
    public int countBinarySubstrings(String s) {
        int n = 0;
        int r = 0;
        byte[] bytes = s.getBytes();
        for(int i = 0; i < bytes.length - 2;i++){

            for (int j = i + 1; j < bytes.length - 1; j++){
                if(bytes[i] != bytes[j]){
                    int end = j + (j - i - 1 );
                    if(end > bytes.length - 1){
                        break;
                    }
                    if (end == j){
                        r++;
                        break;
                    }
                    for(int x = j + 1 ; x <= end ;x ++){
                        if (bytes[j] != bytes[x]){
                            break;
                        }
                    }
                    r++;
                    break;
                }
            }
        }
        return r;
    }
    public int countBinarySubstrings2(String s) {
        int n = 0;

        byte[] bytes = s.getBytes();
        int[] ss = new int[bytes.length];
        int z = 0;
        int i = 0;
        int j = 1;
        while(i < bytes.length - 1){
            int total = 1;
            for(j = i + 1 ; j < bytes.length ;j++){
                if(bytes[i] == bytes[j]){
                    total ++;
                } else{
                    i = j;
                    ss[z] = total;
                    z++;
                    if (i == bytes.length - 1){
                        ss[z] = 1;
                    }
                    break;
                }
            }
        }
        for(int a = 0; a < bytes.length - 1;a++){
            if (ss[a] == 0 || ss[a + 1] == 0){
                break;
            }
            if (ss[a] > ss[a + 1]){
                n += ss[a + 1];
            } else{
                n += ss[a + 1];
            }
        }
        return n;
    }

    @Test
    void name() {
        int i = countBinarySubstrings2("00110");
        System.out.println(i);
    }
}
