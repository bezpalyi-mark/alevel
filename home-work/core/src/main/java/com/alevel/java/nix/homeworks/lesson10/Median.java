package com.alevel.java.nix.homeworks.lesson10;

public class Median {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int minIndex = 0;
        int maxIndex = nums1.length;
        int lengthSum = nums1.length + nums2.length;
        while (minIndex <= maxIndex) {
            int i = (minIndex + maxIndex) / 2;
            int j = ((lengthSum + 1) / 2) - i;
            if (i < maxIndex && nums2[j - 1] > nums1[i]) {
                minIndex = i + 1;
            } else if (i > minIndex && nums1[i - 1] > nums2[j]) {
                maxIndex = i - 1;
            } else {
                int fromFirst;
                if (i == 0) {
                    fromFirst = nums2[j - 1];
                } else if (j == 0) {
                    fromFirst = nums1[i - 1];
                } else {
                    fromFirst = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if (lengthSum % 2 != 0) {
                    return fromFirst;
                }

                int fromSecond;
                if (i == nums1.length) {
                    fromSecond = nums2[j];
                } else if (j == nums2.length) {
                    fromSecond = nums1[i];
                } else {
                    fromSecond = Math.min(nums1[i], nums2[j]);
                }
                return (fromFirst + fromSecond) / 2.0;
            }
        }
        return 0.0;
    }
}
