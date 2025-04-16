package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/16 13:08
 * @describe: 矩阵乘法
 * <a href="https://www.nowcoder.com/practice/ebe941260f8c4210aa8c17e99cbc663b?tpId=37&tqId=21292&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">矩阵乘法</a>
 */
public class HJ69 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();

        int[][] arr1 = new int[x][y];
        int[][] arr2 = new int[y][z];

        int[][] ans = new int[x][z];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arr1[i][j] = scanner.nextInt();
            }
        }

        for (int j = 0; j < y; j++) {
            for (int k = 0; k < z; k++) {
                arr2[j][k] = scanner.nextInt();
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < z; j++) {
                int temp = 0;
                for (int y1 = 0; y1 < y; y1++) {
                    temp += arr1[i][y1] * arr2[y1][j];
                }
                ans[i][j] = temp;
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < z; j++) {
                if (j == z - 1) {
                    System.out.print(ans[i][j]);
                } else {
                    System.out.print(ans[i][j] + " ");
                }
            }
            System.out.println();
        }


    }
}
