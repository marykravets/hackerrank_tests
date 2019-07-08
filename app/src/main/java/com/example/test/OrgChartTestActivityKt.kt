package com.example.test

import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.ArrayList

class OrgChartTestActivityKt: AppCompatActivity() {

    private val mEmployeeList = ArrayList<Employee>()

    private inner class Employee(val id: String, val name: String, var managerId: String?)

    fun add(id: String, name: String, managerId: String) {
        if (getEmployeeIndex(id) != -1) {
            return
        }
        mEmployeeList.add(Employee(id, name, managerId))
    }

    fun print() {
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

    fun remove(employeeId: String) {
        val i = getEmployeeIndex(employeeId)
        if (i != -1) {
            mEmployeeList.removeAt(i)
        }
    }

    fun move(employeeId: String, newManagerId: String) {
        val employeeIndex = getEmployeeIndex(employeeId)
        val managerIndex = getEmployeeIndex(newManagerId)
        if (employeeIndex == -1 || managerIndex == -1) {
            return
        }
        mEmployeeList[employeeIndex].managerId = newManagerId
    }

    fun count(employeeId: String): Int {
        val size = mEmployeeList.size
        var count = 0
        for (i in 0 until size) {
            if (mEmployeeList[i].managerId == employeeId || mEmployeeList[i].id == employeeId) {
                count++
            }
        }
        return count
    }

    fun getEmployeeIndex(employeeId: String): Int {
        val size = mEmployeeList.size
        for (i in 0 until size) {
            if (mEmployeeList[i].id != null && mEmployeeList[i].id == employeeId) {
                return i
            }
        }
        return -1
    }

    public override fun onStart() {
        super.onStart()

        mEmployeeList.add(Employee("0", "Employee0", "-1"))
        mEmployeeList.add(Employee("10", "Employee10", "0"))
        mEmployeeList.add(Employee("11", "Employee11", "0"))
        mEmployeeList.add(Employee("12", "Employee12", "10"))
        mEmployeeList.add(Employee("13", "Employee13", "10"))
        mEmployeeList.add(Employee("14", "Employee14", "12"))

        mEmployeeList.add(Employee("15", "Employee15", "-1"))
        mEmployeeList.add(Employee("22", "Employee22", "15"))
        mEmployeeList.add(Employee("23", "Employee23", "-1"))
        mEmployeeList.add(Employee("24", "Employee24", "23"))
        mEmployeeList.add(Employee("25", "Employee25", "23"))
        print()

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
    }
}