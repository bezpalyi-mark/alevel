package com.alevel.java.nix.homeworks.lesson10;

public class ArraysMedian {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return findMedianWithOneEmptyArray(nums2);
        } else if (nums2.length == 0) {
            return findMedianWithOneEmptyArray(nums1);
        }
        double median = 0;
        int minIndex = 0;
        int maxIndex = Math.min(nums1.length, nums2.length);
        int lengthSum = nums1.length + nums2.length;
        int i = (minIndex + maxIndex) / 2;
        int j = ((lengthSum + 1) / 2) - i;
        int counter = 0;
        boolean isEnd = false;
        if (i == 0) {
            if (nums2.length == 1) {
                return Math.min(nums1[j - 1], nums2[0]);
            } else {
                return Math.min(nums2[j - 1], nums1[0]);
            }
        }
        while (!isEnd && counter < maxIndex) {
            j = ((nums1.length + nums2.length + 1) / 2) - i;
            try {
                if (nums1[i - 1] < nums2[j] && nums2[j - 1] < nums1[i]) {
                    if (lengthSum % 2 != 0) {
                        median = Math.max(nums1[i - 1], nums2[j - 1]);
                    } else {
                        median = (nums1[i - 1] + nums2[j]) / 2.0;
                    }
                    isEnd = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (lengthSum % 2 == 0) {
                    if (nums1.length > nums2.length) {
                        median = (nums1[i - 1] + nums1[i]) / 2.0;
                    } else if (nums1.length == nums2.length) {
                        median = (nums1[i - 1] + nums2[j]) / 2.0;
                    } else {
                        median = (nums2[j - 1] + nums2[j]) / 2.0;
                    }
                } else {
                    if (nums1.length > nums2.length) {
                        median = Math.min(nums1[j - 1], nums2[i]);
                    } else {
                        median = Math.min(nums2[j - 1], nums1[i]);
                    }
                }
                isEnd = true;
            }
            i = i + 1;
            counter++;
        }
        return median;
    }

    private double findMedianWithOneEmptyArray(int[] notEmptyArray) {
        if (notEmptyArray.length == 1) return notEmptyArray[0];
        if (notEmptyArray.length % 2 != 0) {
            return notEmptyArray[notEmptyArray.length / 2];
        }
        return (notEmptyArray[notEmptyArray.length / 2 - 1] + notEmptyArray[notEmptyArray.length / 2]) / 2.0;
    }
}
