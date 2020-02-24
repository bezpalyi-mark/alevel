package com.alevel.java.nix.homeworks.lesson10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArraysMedianTests {
    @Test
    public void assertFindMedianSortedArrays() {
        int[][] input1 = {
                {3, 5, 10, 11, 17},
                {9, 13, 20, 21, 23, 27}
        };
        int[][] input2 = {
                {2, 3, 5, 8},
                {10, 12, 14, 16, 18, 20}
        };
        int[][] input3 = {
                {1, 2, 3, 5},
                {4}
        };
        int[][] input4 = {
                {2, 98},
                {1, 3}
        };
        int[][] input5 = {
                {1, 3, 5},
                {2, 4}
        };
        int[][] input6 = {
                {1, 1, 1, 2, 2, 2},
                {-1, 12, 38}
        };
        int[][] input7 = {
                {1, 2, 3, 5},
                {2}
        };
        int[][] input8 = {
                {4},
                {1, 2, 3, 5}
        };
        int[][] input9 = {
                {2},
                {1, 2, 3, 5}
        };
        int[][] input10 = {
                {2, 2, 2, 2},
                {2}
        };
        int[][] input11 = {
                {2, 2, 2, 2},
                {2, 2}
        };
        int[][] input12 = {
                {},
                {1, 2, 3, 4}
        };
        int[][] input13 = {
                {},
                {1}
        };
        int[][] input14 = {
                {},
                {1, 9}
        };
        double[] expected = {13.0, 11.0, 3.0, 2.5, 3.0, 2.0, 2.0, 3.0, 2.0, 2.0, 2.0, 2.5, 1, 5};
        double actual;
        ArraysMedian arraysMedian = new ArraysMedian();

        actual = arraysMedian.findMedianSortedArrays(input1[0], input1[1]);
        assertEquals(expected[0], actual);

        actual = arraysMedian.findMedianSortedArrays(input2[0], input2[1]);
        assertEquals(expected[1], actual);

        actual = arraysMedian.findMedianSortedArrays(input3[0], input3[1]);
        assertEquals(expected[2], actual);

        actual = arraysMedian.findMedianSortedArrays(input4[0], input4[1]);
        assertEquals(expected[3], actual);

        actual = arraysMedian.findMedianSortedArrays(input5[0], input5[1]);
        assertEquals(expected[4], actual);

        actual = arraysMedian.findMedianSortedArrays(input6[0], input6[1]);
        assertEquals(expected[5], actual);

        actual = arraysMedian.findMedianSortedArrays(input7[0], input7[1]);
        assertEquals(expected[6], actual);

        actual = arraysMedian.findMedianSortedArrays(input8[0], input8[1]);
        assertEquals(expected[7], actual);

        actual = arraysMedian.findMedianSortedArrays(input9[0], input9[1]);
        assertEquals(expected[8], actual);

        actual = arraysMedian.findMedianSortedArrays(input10[0], input10[1]);
        assertEquals(expected[9], actual);

        actual = arraysMedian.findMedianSortedArrays(input11[0], input11[1]);
        assertEquals(expected[10], actual);

        actual = arraysMedian.findMedianSortedArrays(input12[0], input12[1]);
        assertEquals(expected[11], actual);

        actual = arraysMedian.findMedianSortedArrays(input13[0], input13[1]);
        assertEquals(expected[12], actual);

        actual = arraysMedian.findMedianSortedArrays(input14[0], input14[1]);
        assertEquals(expected[13], actual);
    }
}
