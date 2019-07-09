package com.example.test.obj

import android.util.Log
import java.util.ArrayList

//Employee0
//  Employee10
//    Employee12
//      Employee14
//    Employee13
//  Employee11
//Employee15
//  Employee22
//Employee23
//  Employee24
//  Employee25

open class EmployeeKotlin {
    private val mEmployeeList = ArrayList<Employee>()

    private inner class Employee(val id: String, val name: String, var managerId: String?)

    constructor() {

    }

    open fun runTest() {
        val start = System.currentTimeMillis()

        for (i in 0 until 100) {
            add("0", "Employee0", "-1")
            add("10", "Employee10", "0")
            add("11", "Employee11", "0")
            add("12", "Employee12", "10")
            add("13", "Employee13", "10")
            add("14", "Employee14", "12")

            add("15", "Employee15", "-1")
            add("22", "Employee22", "15")
            add("23", "Employee23", "-1")
            add("24", "Employee24", "23")
            add("25", "Employee25", "23")
            //print()
            Log.d("=test=", "count = " + count("10"))
            move("24", "0")
            remove("14")
        }

        print()
        val end  = System.currentTimeMillis()
        Log.d("=test=", "time = "+(end - start))
    }

    open fun add(id: String, name: String, managerId: String) {
        if (getEmployeeIndex(id) != -1) {
            return
        }
        mEmployeeList.add(Employee(id, name, managerId))
    }

    open fun print() {
        val size = mEmployeeList.size
        var employee: Employee
        for (i in 0 until size) {
            employee = mEmployeeList[i]
            if (employee.managerId == "-1") {
                //System.out.println(employee.getName() + " [" + employee.getId() + "]" );
                Log.d("=test=", employee.name + " [" + employee.id + "]")
                printList("  ", size, employee)
            }
        }
    }

    private fun printList(indent: String, size: Int, employee: Employee) {
        for (j in 0 until size) {
            if (employee.id == mEmployeeList[j].managerId) {
                //System.out.println("  " + mEmployeeList.get(j).getName() + " [" + mEmployeeList.get(j).getId() + "]" );
                Log.d("=test=", indent + mEmployeeList[j].name + " [" + mEmployeeList[j].id + "]")
                printList("$indent  ", size, mEmployeeList[j])
            }
        }
    }

    open fun remove(employeeId: String) {
        val i = getEmployeeIndex(employeeId)
        if (i != -1) {
            mEmployeeList.removeAt(i)
        }
    }

    open fun move(employeeId: String, newManagerId: String) {
        val employeeIndex = getEmployeeIndex(employeeId)
        val managerIndex = getEmployeeIndex(newManagerId)
        if (employeeIndex == -1 || managerIndex == -1) {
            return
        }
        mEmployeeList[employeeIndex].managerId = newManagerId
    }

    open fun count(employeeId: String): Int {
        val size = mEmployeeList.size
        var count = 0
        for (i in 0 until size) {
            if (mEmployeeList[i].managerId == employeeId || mEmployeeList[i].id == employeeId) {
                count++
            }
        }
        return count
    }

    open fun getEmployeeIndex(employeeId: String): Int {
        val size = mEmployeeList.size
        for (i in 0 until size) {
            if (mEmployeeList[i].id != null && mEmployeeList[i].id == employeeId) {
                return i
            }
        }
        return -1
    }
}