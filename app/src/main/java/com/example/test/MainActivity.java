package com.example.test;

import android.support.v7.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();

        //hackerrank tests
        int val1 = countingValleys(10, "UDUUUDUDDD");
        int val2 = sockMerchant(10, new int[] {1,2,3,2,1,4,6,5,4,1});
        int val3 = jumpingOnClouds(new int[] {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0});
    }

    public static int sockMerchant(int n, int[] ar) {
        HashMap<Integer, Integer> map = new HashMap();
        int size = 0, val;

        for (int i = 0; i < n; i++) {
            if (map.containsKey(ar[i])) {
                val = map.get(ar[i]);

                map.put(ar[i], ++val);
                if ((val & 1) == 0) {
                    size++;
                }
            } else {
                map.put(ar[i], 1);
            }
        }

        return size;
    }

    public static int countingValleys(int n, String s) {

        int seaLevel = 0, count = 0;
        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == 'U') {
                seaLevel++;
            } else {
                if (seaLevel == 0) {
                    count++;
                }

                seaLevel--;
            }
        }

        return count;
    }

    public static int jumpingOnClouds(int[] c) {
        int jump = -1, len = c.length;
        for (int i = 0; i < len; i++, jump++) {
            if (i < len-2 && c[i+2] == 0) {
                i++;
            }
        }
        return jump;
    }
}

