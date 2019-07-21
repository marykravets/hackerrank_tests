package javatest;

import com.example.test.Solutions;
import org.junit.Test;

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
}
