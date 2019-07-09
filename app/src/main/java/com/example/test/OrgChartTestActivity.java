package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//hackerrank test
public class OrgChartTestActivity extends AppCompatActivity {

    private List<Employee> mEmployeeList = new ArrayList<>();

    private class Employee {
        private String id,name,managerId;

        public Employee(String id, String name, String managerId) {
            this.id = id;
            this.name = name;
            this.managerId = managerId;
        }
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getManagerId() {
            return managerId;
        }
        public void setManagerId(String newId) {
            managerId = newId;
        }
    }

    public void add(String id, String name, String managerId)
    {
        if (getEmployeeIndex(id) != -1) {
            return;
        }
        mEmployeeList.add(new Employee(id, name, managerId));
    }

    public void print() {
        final int size = mEmployeeList.size();
        Employee employee;
        for (int i = 0; i < size; i++) {
            employee = mEmployeeList.get(i);
            if (employee.getManagerId().equals("-1")) {
                //System.out.println(employee.getName() + " [" + employee.getId() + "]" );
                Log.d("=test=", employee.getName() + " [" + employee.getId() + "]");
                printList("  ", size, employee);
            }
        }
    }

    private void printList(String indent, int size, Employee employee) {
        for (int j = 0; j < size; j++) {
            if (employee.getId().equals(mEmployeeList.get(j).getManagerId())) {
                //System.out.println("  " + mEmployeeList.get(j).getName() + " [" + mEmployeeList.get(j).getId() + "]" );
                Log.d("=test=", indent + mEmployeeList.get(j).getName() + " [" + mEmployeeList.get(j).getId() + "]");
                printList(indent + "  ", size, mEmployeeList.get(j));
            }
        }
    }

    public void remove(String employeeId)
    {
        final int i = getEmployeeIndex(employeeId);
        if (i != -1) {
            mEmployeeList.remove(i);
        }
    }

    public void move(String employeeId, String newManagerId)
    {
        final int employeeIndex = getEmployeeIndex(employeeId);
        final int managerIndex = getEmployeeIndex(newManagerId);
        if (employeeIndex == -1 || managerIndex == -1) {
            return;
        }
        mEmployeeList.get(employeeIndex).setManagerId(newManagerId);
    }

    public int count(String employeeId)
    {
        final int size = mEmployeeList.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (mEmployeeList.get(i).getManagerId().equals(employeeId)
                    || mEmployeeList.get(i).getId().equals(employeeId)) {
                count++;
            }
        }
        return count;
    }

    public int getEmployeeIndex(String employeeId) {
        final int size = mEmployeeList.size();
        for (int i = 0; i < size; i++) {
            if (mEmployeeList.get(i).getId() != null && mEmployeeList.get(i).getId().equals(employeeId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onStart() {
        super.onStart();

        long start = System.currentTimeMillis();

        for (int i = 0; i <= 100; i++){
            add("0", "Employee0", "-1");
            add("10", "Employee10", "0");
            add("11", "Employee11", "0");
            add("12", "Employee12", "10");
            add("13", "Employee13", "10");
            add("14", "Employee14", "12");

            add("15", "Employee15", "-1");
            add("22", "Employee22", "15");
            add("23", "Employee23", "-1");
            add("24", "Employee24", "23");
            add("25", "Employee25", "23");
            //print();
            Log.d("=test=", "count = " + count("10"));
            move("24", "0");
            remove("14");
        }

        print();
        long end  = System.currentTimeMillis();
        Log.d("=test=", "time = "+(end - start));

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
