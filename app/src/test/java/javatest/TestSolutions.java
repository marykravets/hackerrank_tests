package javatest;

import com.example.test.Solutions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TestSolutions {

    //hackerrank tests

    @Test
    public void countingValleysTest() {
        Solutions mockedObj = mock(Solutions.class);
        int result = mockedObj.countingValleys(10, "UDUUUDUDDD");

        assertEquals(result, 0);
    }

    @Test
    public void sockMerchantTest() {
        Solutions mockedObj = mock(Solutions.class);
        int result = mockedObj.sockMerchant(10, new int[] {1, 2, 3, 2, 1, 4, 6, 5, 4, 1});

        assertEquals(result, 3);
    }

    @Test
    public void jumpingOnCloudsest() {
        Solutions mockedObj = mock(Solutions.class);
        int result = mockedObj.jumpingOnClouds(new int[] {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0});

        assertEquals(result, 28);
    }

    @Test
    public void testMapMerge() {

        Map<Object, Object> mapA = new HashMap<>();
        mapA.put("a", 0);
        Map<Object, Object> mapValue1 = new HashMap<>();
        mapValue1.put("b1", 1);
        mapA.put("b", mapValue1);


        Map<Object, Object> mapB = new HashMap<>();
        mapB.put("a", 1);
        Map<Object, Object> mapValue2 = new HashMap<>();
        mapValue2.put("b2", 2);
        mapB.put("b", mapValue2);

        HashMap<Object, Object> result = Solutions.getMergedHashMap(mapA, mapB);

        System.out.println("-------- = "+((ArrayList)result.get("a")).get(0) + "   " + ((ArrayList)result.get("a")).get(1));
        System.out.println("-------- = "+((ArrayList)result.get("b")).get(0) + "   " + ((ArrayList)result.get("b")).get(1));

        Integer r1 = (Integer) ((ArrayList)result.get("a")).get(0);
        Integer r2 = (Integer) ((ArrayList)result.get("a")).get(1);

        HashMap r3 = (HashMap) ((ArrayList)result.get("b")).get(0);
        HashMap r4 = (HashMap) ((ArrayList)result.get("b")).get(1);

        assertEquals(result.size(), 2);
        assertEquals(((ArrayList)result.get("a")).size(), 2);
        assertEquals(((ArrayList)result.get("b")).size(), 2);
        assertEquals(r1, (Integer)1);
        assertEquals(r2, (Integer)0);
        assertEquals(r3.get("b2"), (Integer)2);
        assertEquals(r4.get("b1"), (Integer)1);
    }
}
