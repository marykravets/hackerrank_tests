package com.example.test

import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.test.list.implementation.obj.EmployeeKotlin
import helper.bindView

class OrgChartTestActivityKt : AppCompatActivity() {

    private val mTextView by bindView<TextView>(R.id.text_view)

    public override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)
        mTextView.setText(getString(R.string.app_name))

        EmployeeKotlin().runTest()
    }
}