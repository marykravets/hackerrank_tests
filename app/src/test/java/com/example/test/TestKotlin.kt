package com.example.test

import com.example.test.list.implementation.obj.EmployeeKotlin
import org.junit.Test

class TestKotlin {

    @Test
    fun kotlinListEmployeeTest() {
        var list = arrayListOf<EmployeeKotlin>()

        var start = System.currentTimeMillis()

        var e : EmployeeKotlin
        for (i in 0..10000) {
            e = EmployeeKotlin()
            e.add(i.toString(), "name", "0")
            list.add(e)
        }

        var end = System.currentTimeMillis()
        println("ArrayList<Employee> add time = " + (end - start))

        start = System.currentTimeMillis()

        for (i in 10000..0) {
            list.removeAt(i)
        }

        end = System.currentTimeMillis()
        println("ArrayList<Employee> remove time = " + (end - start))
    }

    @Test
    fun kotlinListStringTest() {
        val n = 1000

        var start = System.currentTimeMillis()
        for (j in 0..n) {
            var list = arrayListOf<String>()

            for (i in 0..10000) {
                list.add("str" + i)
            }

            list.removeAt(100)
        }

        var end = System.currentTimeMillis()
        println("kotlinListStringTest time = " + (end-start))
    }

    @Test
    fun kotlinListNoFunctionTest() {
        val n = 100000
        var list = arrayListOf<String>()
        for (j in 0..n) {
            list.add("str" + j)
        }

        var start = System.currentTimeMillis()
        for (i in 0..list.size-1) {
            val obj = list.get(i)
        }

        var end = System.currentTimeMillis()
        println("NOT forEachIndexed time = " + (end-start))
    }

    @Test
    fun kotlinListFunctionTest() {
        val n = 100000
        var list = arrayListOf<String>()
        for (j in 0..n) {
            list.add("str" + j)
        }

        var start = System.currentTimeMillis()
        list.forEachIndexed {i, it ->
            val obj = list.get(i)
        }

        var end = System.currentTimeMillis()
        println("forEachIndexed time = " + (end-start))
    }
}