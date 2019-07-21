package com.example.test;

import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();

        //hackerrank tests
        int val1 = Solutions.countingValleys(10, "UDUUUDUDDD");
        int val2 = Solutions.sockMerchant(10, new int[] {1, 2, 3, 2, 1, 4, 6, 5, 4, 1});
        int val3 = Solutions.jumpingOnClouds(new int[] {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0});
    }
}

