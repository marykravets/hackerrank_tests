package com.example.test.tree.implementation;

import android.util.Log;

import java.util.ArrayList;

//Employee0
//  Employee10
//    Employee12
//    Employee13
//  Employee11
//Employee15
//  Employee22
//Employee23
//  Employee24
//  Employee25

public class EmployeeJava {
    private ArrayList<Employee> mEmployeeList = new ArrayList();

    private class Employee {

        private String mId, mName, mManagerId;
        private ArrayList<Employee> mEmployeesList;

        public Employee(String id, String name, String managerId) {
            mId = id;
            mName = name;
            mManagerId = managerId;
            mEmployeesList = new ArrayList<>();
        }

        public String getId() {
            return mId;
        }

        public String getName() {
            return mName;
        }

        public String getManagerId() {
            return mManagerId;
        }

        public ArrayList<Employee> getEmployeeList() {
            return mEmployeesList;
        }

        public void addEmployee(String id, String name, String managerId) {
            mEmployeesList.add(new Employee(id, name, managerId));
        }
    }

    public void runTest() {
        long start = System.currentTimeMillis();

        add("0", "Employee0", "-1");
        add("10", "Employee10", "0");
        add("11", "Employee11", "0");
        add("12", "Employee12", "10");
        add("13", "Employee13", "10");

        add("15", "Employee15", "-1");
        add("22", "Employee22", "15");
        add("23", "Employee23", "-1");
        add("24", "Employee24", "23");
        add("25", "Employee25", "23");

        print();
        long end  = System.currentTimeMillis();
        Log.d("=test=", "time = "+(end - start));
    }

    public void add(String id, String name, String managerId) {
        if (getEmployeeNode(id) != null) {
            return;
        }

        Employee manager = getEmployeeNode(managerId);
        if (manager != null) {
            manager.addEmployee(id, name, managerId);
            return;
        }

        mEmployeeList.add(new Employee(id, name, managerId));
    }

    public Employee getEmployeeNode(String employeeId) {
        Employee returnVal = null;
        final int size = mEmployeeList.size();
        Employee e;
        for (int i = 0; i < size; i++) {
            e = mEmployeeList.get(i);
            if (e != null && e.getId().equals(employeeId)) {
                return e;
            }
            returnVal = getEmployeeNode(e.getEmployeeList(), employeeId);
        }

        return returnVal;
    }

    public Employee getEmployeeNode(ArrayList<Employee> t, String employeeId) {
        final int size = t.size();
        Employee e;
        for (int i = 0; i < size; i++) {
            e = t.get(i);
            if (e != null && e.getId().equals(employeeId)) {
                return e;
            }
            getEmployeeNode(e.getEmployeeList(), employeeId);
        }

        return null;
    }

    public void print() {
        final int size = mEmployeeList.size();
        Employee employee;
        for (int i = 0; i < size; i++) {
            employee = mEmployeeList.get(i);
            if (employee.getManagerId().equals("-1")) {
                //System.out.println(employee.getName() + " [" + employee.getId() + "]" );
                Log.d("=test=", employee.getName() + " [" + employee.getId() + "]");
                printList("  ", employee.getId(), employee.getEmployeeList());
            }
        }
    }

    private void printList(String indent, String managerId, ArrayList<Employee> employeeList) {
        for (int j = 0; j < employeeList.size(); j++) {
            if (employeeList.get(j).getManagerId().equals(managerId)) {
                //System.out.println("  " + mEmployeeList.get(j).getName() + " [" + mEmployeeList.get(j).getId() + "]" );
                Log.d("=test=", indent + employeeList.get(j).getName() + " [" + employeeList.get(j).getId() + "]");
                printList(indent + "  ", employeeList.get(j).getId(), employeeList.get(j).getEmployeeList());
            }
        }
    }
}
