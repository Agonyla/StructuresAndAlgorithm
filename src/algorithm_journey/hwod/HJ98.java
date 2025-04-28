package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/28 13:32
 * @describe: 喜欢切数组的红
 * <a href="https://www.nowcoder.com/practice/74cb703f25dc4956acb3b08028a1f4b4?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">喜欢切数组的红</a>
 */
public class HJ98 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取数组长度
        int n = scanner.nextInt();

        // 读取数组元素
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        // 计算切分方案数
        int result = countCuttingWays(arr, n);
        System.out.println(result);
    }

    public static int countCuttingWays(int[] arr, int n) {

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        if (sum % 3 != 0) {
            return 0;
        }
        int targetSum = sum / 3;

        // 前缀和 以及 前缀 是否存在正数
        int[] prefixSum = new int[n + 1];
        boolean[] prefixPositive = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
            prefixPositive[i + 1] = prefixPositive[i] || arr[i] > 0;
        }

        int count = 0;


        for (int i = 1; i < n - 1; i++) {

            // 第一次划分是否满足要求
            if (prefixSum[i] != targetSum || !prefixPositive[i]) {
                continue;
            }


            // 第二次划分
            for (int j = i + 1; j < n; j++) {

                int sum2 = prefixSum[j] - prefixSum[i];
                if (sum2 != targetSum) {
                    continue;
                }

                int sum3 = prefixSum[n] - prefixSum[j];
                if (sum3 != targetSum) {
                    continue;
                }

                // 检查划分区域是否有正数
                boolean hasPositiveInteger2 = checkPositive(arr, prefixPositive, i, j);
                if (!hasPositiveInteger2) {
                    continue;
                }

                boolean hasPositiveInteger3 = checkPositive(arr, prefixPositive, j, n);
                if (!hasPositiveInteger3) {
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    private static boolean checkPositive(int[] arr, boolean[] prefixPositive, int start, int end) {

        if (prefixPositive[end] && !prefixPositive[start]) {
            return true;
        }
        for (int i = start; i < end; i++) {
            if (arr[i] > 0) {
                return true;
            }
        }
        return false;
    }
}