package com.example.test

import android.support.v7.app.AppCompatActivity
import com.example.test.list.implementation.obj.EmployeeKotlin

class OrgChartTestActivityKt: AppCompatActivity() {


    public override fun onStart() {
        super.onStart()

        EmployeeKotlin().runTest()
    }
}