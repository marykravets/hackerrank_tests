package com.example.test;

import android.support.v7.app.AppCompatActivity;
import com.example.test.list.implementation.obj.EmployeeJava;

public class OrgChartTestActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();

        new EmployeeJava().runTest();
    }
}
