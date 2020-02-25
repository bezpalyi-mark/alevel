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
        int[][] input15 = {
                {1, 2},
                {3, 4}
        };
        int[][] input16 = {
                {0, 0},
                {0, 0}
        };
        int[][] input17 = {
                {1, 2},
                {-1, 3}
        };
        int[][] input18 = {
                {1, 1},
                {1, 1}
        };
        int[][] input19 = {
                {1, 2},
                {1, 1}
        };
        int[][] input20 = {
                {1, 2, 2},
                {1, 2, 3}
        };
        int[][] input21 = {
                {1, 2, 3},
                {1, 2, 2}
        };
        int[][] input22 = {
                {100_000},
                {100_001}
        };
        int[][] input23 = {
                {1},
                {2, 3}
        };
        int[][] input24 = {
                {3},
                {-2, -1}
        };
        int[][] input25 = {
                {2, 3},
                {1}
        };
        int[][] input26 = {
                {1, 3},
                {2}
        };
        int[][] input27 = {
                {2},
                {1, 3}
        };
        int[][] input28 = {
                {1, 2},
                {3}
        };
        int[][] input29 = {
                {1},
                {2, 3, 4}
        };
        int[][] input30 = {
                {2, 3, 4},
                {1}
        };
        int[][] input31 = {
                {4},
                {1, 2, 3}
        };
        double[] expected = {13.0, 11.0, 3.0, 2.5, 3.0, 2.0, 2.0, 3.0, 2.0, 2.0, 2.0, 2.5, 1, 5, 2.5, 0.0, 1.5, 1.0,
                1.0, 2.0, 2.0, 100_000.5, 2.0, -1.0, 2.0, 2.0, 2.0, 2.0, 2.5, 2.5, 2.5};
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

        actual = arraysMedian.findMedianSortedArrays(input15[0], input15[1]);
        assertEquals(expected[14], actual);

        actual = arraysMedian.findMedianSortedArrays(input16[0], input16[1]);
        assertEquals(expected[15], actual);

        actual = arraysMedian.findMedianSortedArrays(input17[0], input17[1]);
        assertEquals(expected[16], actual);

        actual = arraysMedian.findMedianSortedArrays(input18[0], input18[1]);
        assertEquals(expected[17], actual);

        actual = arraysMedian.findMedianSortedArrays(input19[0], input19[1]);
        assertEquals(expected[18], actual);

        actual = arraysMedian.findMedianSortedArrays(input20[0], input20[1]);
        assertEquals(expected[19], actual);

        actual = arraysMedian.findMedianSortedArrays(input21[0], input21[1]);
        assertEquals(expected[20], actual);

        actual = arraysMedian.findMedianSortedArrays(input22[0], input22[1]);
        assertEquals(expected[21], actual);

        actual = arraysMedian.findMedianSortedArrays(input23[0], input23[1]);
        assertEquals(expected[22], actual);

        actual = arraysMedian.findMedianSortedArrays(input24[0], input24[1]);
        assertEquals(expected[23], actual);

        actual = arraysMedian.findMedianSortedArrays(input25[0], input25[1]);
        assertEquals(expected[24], actual);

        actual = arraysMedian.findMedianSortedArrays(input26[0], input26[1]);
        assertEquals(expected[25], actual);

        actual = arraysMedian.findMedianSortedArrays(input27[0], input27[1]);
        assertEquals(expected[26], actual);

        actual = arraysMedian.findMedianSortedArrays(input28[0], input28[1]);
        assertEquals(expected[27], actual);

        actual = arraysMedian.findMedianSortedArrays(input29[0], input29[1]);
        assertEquals(expected[28], actual);

        actual = arraysMedian.findMedianSortedArrays(input30[0], input30[1]);
        assertEquals(expected[29], actual);

        actual = arraysMedian.findMedianSortedArrays(input31[0], input31[1]);
        assertEquals(expected[30], actual);
    }
}
