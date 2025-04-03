package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 13:35
 * @describe: 查找充电设备组合
 */
public class FindChargingDeviceCombination {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取充电设备个数n
        int n = scanner.nextInt();

        // 读取每个充电设备的输出功率
        int[] powers = new int[n];
        for (int i = 0; i < n; i++) {
            powers[i] = scanner.nextInt();
        }

        // 读取充电站最大输出功率p_max
        int p_max = scanner.nextInt();

        // 调用函数找到最优解
        int result = findOptimalPower(powers, p_max);

        // 输出结果
        System.out.println(result);

        scanner.close();
    }

    public static int findOptimalPower(int[] powers, int p_max) {
        // 使用动态规划解决
        // dp[j]表示是否可以组成功率j
        boolean[] dp = new boolean[p_max + 1];

        // 初始化：不选任何设备，功率为0是可行的
        dp[0] = true;

        // 对每个充电设备
        for (int power : powers) {
            // 从大到小遍历，避免重复计算
            for (int j = p_max; j >= power; j--) {
                // 如果j-power可行，那么j也可行
                if (dp[j - power]) {
                    dp[j] = true;
                }
            }
        }

        // 从p_max开始向下查找第一个可行的值
        for (int j = p_max; j >= 0; j--) {
            if (dp[j]) {
                return j;
            }
        }

        // 如果没有可行解，返回0（理论上不会发生，因为至少可以选择不使用任何设备）
        return 0;
    }

}
