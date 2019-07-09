package com.example.test;

import android.support.v7.app.AppCompatActivity;
import com.example.test.obj.EmployeeJava;

public class OrgChartTestActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();

        new EmployeeJava();
    }
}
