package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[][] array1 = {
                {1,1,0,1,0},
                {0,1,1,1,0},
                {1,1,1,1,0},
                {0,1,1,1,1}
        };

        int[][] array2 = {
                {1,1,0,1,0},
                {0,1,1,1,1},
                {1,1,1,1,1},
                {0,1,1,1,1},
                {0,1,1,1,1}
        };

        int[][] array3 = new int[0][0];

        int[][] array4 = {
                {1,1,0,1}
        };

        int[][] array5 = {
                {1},
                {1},
                {0},
                {1}
        };

        int[][] array6 = {
                {0,0,0,0}
        };

        int[][] array7 = {
                {0},
                {0},
                {0},
                {0}
        };

        int[][] array8 = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };

        int[][] array9 = {
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1}
        };

        int[][] array10 = {
                {1,1,0,1},
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1},
                {0,1,1,1}
        };
        System.out.println(maxSquare(array1));
        System.out.println(maxSquare(array2));
        System.out.println(maxSquare(array3));
        System.out.println(maxSquare(array4));
        System.out.println(maxSquare(array5));
        System.out.println(maxSquare(array6));
        System.out.println(maxSquare(array7));
        System.out.println(maxSquare(array8));
        System.out.println(maxSquare(array9));
        System.out.println(maxSquare(array10));
    }

    public static int maxSquare(int[][] matrix) {
        if (matrix.length == 0) return 0;


        int[][] cache = Arrays.copyOf(matrix, matrix.length);

        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1 && !(i == 0 || j == 0)) {
                    cache[i][j] = 1 + Math.min(cache[i - 1][j],
                            Math.min(cache[i - 1][j - 1], cache[i][j - 1]));
                }

                if (cache[i][j] > result) result = cache[i][j];
            }
        }

        return result*result;
    }
}
