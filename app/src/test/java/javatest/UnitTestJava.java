package javatest;
import static org.mockito.Mockito.*;

import com.example.test.list.implementation.obj.EmployeeJava;
import com.example.test.list.implementation.obj.EmployeeKotlin;
import org.junit.Test;

import java.util.ArrayList;

public class UnitTestJava {

    @Test
    public void javaTimeTest() {
        EmployeeJava mockedObj = mock(EmployeeJava.class);

        long start = System.currentTimeMillis();

        for (int i = 0; i <= 10000; i++){
            mockedObj.add("0", "Employee0", "-1");
            mockedObj.add("10", "Employee10", "0");
            mockedObj.add("11", "Employee11", "0");
            mockedObj.add("12", "Employee12", "10");
            mockedObj.add("13", "Employee13", "10");
            mockedObj.add("14", "Employee14", "12");

            mockedObj.add("15", "Employee15", "-1");
            mockedObj.add("22", "Employee22", "15");
            mockedObj.add("23", "Employee23", "-1");
            mockedObj.add("24", "Employee24", "23");
            mockedObj.add("25", "Employee25", "23");
            mockedObj.move("24", "0");
            mockedObj.remove("14");
        }

        mockedObj.print();
        long end  = System.currentTimeMillis();
        System.out.println("java_java time = "+(end - start));
    }

    @Test //calling Kotlin class from java test
    public void KotlinJavaTimeTest() {
        EmployeeKotlin mockedObj = mock(EmployeeKotlin.class);

        long start = System.currentTimeMillis();

        for (int i = 0; i <= 10000; i++){
            mockedObj.add("0", "Employee0", "-1");
            mockedObj.add("10", "Employee10", "0");
            mockedObj.add("11", "Employee11", "0");
            mockedObj.add("12", "Employee12", "10");
            mockedObj.add("13", "Employee13", "10");
            mockedObj.add("14", "Employee14", "12");

            mockedObj.add("15", "Employee15", "-1");
            mockedObj.add("22", "Employee22", "15");
            mockedObj.add("23", "Employee23", "-1");
            mockedObj.add("24", "Employee24", "23");
            mockedObj.add("25", "Employee25", "23");
            mockedObj.move("24", "0");
            mockedObj.remove("14");
        }

        mockedObj.print();
        long end  = System.currentTimeMillis();
        System.out.println("java_kotlin time = "+(end - start));
    }

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
