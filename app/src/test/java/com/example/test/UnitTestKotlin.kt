package com.example.test

import org.mockito.Mockito.*

import com.example.test.obj.EmployeeJava
import com.example.test.obj.EmployeeKotlin
import org.junit.Test

class UnitTestKotlin {

    @Test //check java code from Kotlin test
    fun kotlinJavaTimeTest() {
        val mockedObj = mock(EmployeeJava::class.java)

        val start = System.currentTimeMillis()

        for (i in 0..10000) {
            mockedObj.add("0", "Employee0", "-1")
            mockedObj.add("10", "Employee10", "0")
            mockedObj.add("11", "Employee11", "0")
            mockedObj.add("12", "Employee12", "10")
            mockedObj.add("13", "Employee13", "10")
            mockedObj.add("14", "Employee14", "12")

            mockedObj.add("15", "Employee15", "-1")
            mockedObj.add("22", "Employee22", "15")
            mockedObj.add("23", "Employee23", "-1")
            mockedObj.add("24", "Employee24", "23")
            mockedObj.add("25", "Employee25", "23")
            mockedObj.move("24", "0")
            mockedObj.remove("14")
        }

        mockedObj.print()
        val end = System.currentTimeMillis()
        println("kotlin_java time = " + (end - start))
    }

    @Test //calling Kotlin class from Kotlin test
    fun KotlinFromKotlinTimeTest() {
        val mockedObj = mock(EmployeeKotlin::class.java)

        val start = System.currentTimeMillis()

        for (i in 0..10000) {
            mockedObj.add("0", "Employee0", "-1")
            mockedObj.add("10", "Employee10", "0")
            mockedObj.add("11", "Employee11", "0")
            mockedObj.add("12", "Employee12", "10")
            mockedObj.add("13", "Employee13", "10")
            mockedObj.add("14", "Employee14", "12")

            mockedObj.add("15", "Employee15", "-1")
            mockedObj.add("22", "Employee22", "15")
            mockedObj.add("23", "Employee23", "-1")
            mockedObj.add("24", "Employee24", "23")
            mockedObj.add("25", "Employee25", "23")
            mockedObj.move("24", "0")
            mockedObj.remove("14")
        }

        mockedObj.print()
        val end = System.currentTimeMillis()
        println("kotlin_kotlin time = " + (end - start))
    }

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
}
