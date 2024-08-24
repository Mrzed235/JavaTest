package com.opencc.LeetCodeTest;
import java.util.Arrays;

public class FindPoint {
    public static int[] findThirdPoint(int[] p1, int[] p2) {
        int x1 = p1[0];
        int y1 = p1[1];
        int x2 = p2[0];
        int y2 = p2[1];

        // 水平方向
        if (x1 == x2) {
            if (y1 < y2) {
                if (y1 + 1 <= 18) {
                    return new int[]{x1, y1 + 1};
                } else {
                    return new int[]{-1, -1};
                }
            } else {
                if (y1 - 1 >= 0) {
                    return new int[]{x1, y1 - 1};
                } else {
                    return new int[]{-1, -1};
                }
            }
        }

        // 垂直方向
        if (y1 == y2) {
            if (x1 < x2) {
                if (x1 + 1 <= 18) {
                    return new int[]{x1 + 1, y1};
                } else {
                    return new int[]{-1, -1};
                }
            } else {
                if (x1 - 1 >= 0) {
                    return new int[]{x1 - 1, y1};
                } else {
                    return new int[]{-1, -1};
                }
            }
        }

        // 45 度方向
        if (x2 - x1 == y2 - y1) {
            if (x1 < x2 && y1 < y2) {
                if (x1 + 1 <= 18 && y1 + 1 <= 18) {
                    return new int[]{x1 + 1, y1 + 1};
                } else {
                    return new int[]{-1, -1};
                }
            } else if (x1 > x2 && y1 > y2) {
                if (x1 - 1 >= 0 && y1 - 1 >= 0) {
                    return new int[]{x1 - 1, y1 - 1};
                } else {
                    return new int[]{-1, -1};
                }
            }
        }

        // 135 度方向
        if (x2 - x1 == -(y2 - y1)) {
            if (x1 < x2 && y1 > y2) {
                if (x1 + 1 <= 18 && y1 - 1 >= 0) {
                    return new int[]{x1 + 1, y1 - 1};
                } else {
                    return new int[]{-1, -1};
                }
            } else if (x1 > x2 && y1 < y2) {
                if (x1 - 1 >= 0 && y1 + 1 <= 18) {
                    return new int[]{x1 - 1, y1 + 1};
                } else {
                    return new int[]{-1, -1};
                }
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] p1 = {5, 5};
        int[] p2 = {5, 8};
        int[] result = findThirdPoint(p1, p2);
        System.out.println(Arrays.toString(result));
    }
}