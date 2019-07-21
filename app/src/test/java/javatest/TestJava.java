package javatest;

import com.example.test.list.implementation.obj.EmployeeJava;
import org.junit.Test;

import java.util.ArrayList;

public class TestJava {

    @Test
    public void javaListTest() {
        ArrayList<EmployeeJava> list = new ArrayList<>();

        long start = System.currentTimeMillis();

        EmployeeJava e;
        for (int i = 0; i <= 10000; i++){
            e = new EmployeeJava();
            e.add(String.valueOf(i), "name", "0");
            list.add(e);
        }

        long end  = System.currentTimeMillis();
        System.out.println("ArrayList<Employee> add time = "+(end - start));

        start = System.currentTimeMillis();

        for (int i = 10000; i > 0; i--) {
            list.remove(i);
        }

        end  = System.currentTimeMillis();
        System.out.println("ArrayList<Employee> remove time = "+(end - start));
    }

    @Test
    public void javaListStringTest() {
        int n = 1000;

        long start = System.currentTimeMillis();
        for (int j = 0; j <= n; j++) {
            ArrayList<String> list = new ArrayList<>();

            for (int i = 0; i <= 10000; i++) {
                list.add("str" + i);
            }

            list.remove(100);
        }
        long end = System.currentTimeMillis();
        System.out.println("javaListStringTest time = "+(end-start));
    }
}
