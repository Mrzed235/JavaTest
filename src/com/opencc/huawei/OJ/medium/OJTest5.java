package com.opencc.huawei.OJ.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 土地分配
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=48945dc256e14711bf5470b5b393fccc">...</a>
 */

class CoordsInfo {
    int startRow;
    int endRow;
    int startCol;
    int endCol;

    public CoordsInfo(int startRow, int endRow, int startCol, int endCol) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
    }

    public void setRow(int Row) {
        if (Row > this.endRow) {
            this.endRow = Row;
        }
        if (Row < this.startRow) {
            this.startRow = Row;
        }
    }

    public void setCol(int Col) {
        if (Col > this.endCol) {
            this.endCol = Col;
        }
        if (Col < this.startCol) {
            this.startCol = Col;
        }
    }
}

public class OJTest5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        HashMap<Integer, CoordsInfo> hashMap = new HashMap<>();
        int[][] field = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = field[i][j];
                if (num != 0) {
                    if (!hashMap.containsKey(num)) {
                        hashMap.put(num, new CoordsInfo(i, i, j, j));
                    } else {
                        CoordsInfo info = hashMap.get(num);
                        info.setRow(i);
                        info.setCol(j);
                    }
                }
            }
        }

        int maxArea = 0;
        for (Map.Entry<Integer, CoordsInfo> integerCoordsInfoEntry : hashMap.entrySet()) {
            CoordsInfo info = integerCoordsInfoEntry.getValue();
            maxArea = Math.max(maxArea, (info.endRow - info.startRow + 1) * (info.endCol - info.startCol + 1));
        }
        System.out.println(maxArea);
        in.close();
    }
}
