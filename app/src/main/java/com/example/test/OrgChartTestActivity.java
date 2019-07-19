package com.example.test;

import android.support.v7.app.AppCompatActivity;
import com.example.test.list.implementation.obj.EmployeeJava;
//or comment list.implementation and uncomment tree.implementation
//import com.example.test.tree.implementation.EmployeeJava;

public class OrgChartTestActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();

        new EmployeeJava().runTest();
    }
}
