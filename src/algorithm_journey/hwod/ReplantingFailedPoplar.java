package algorithm_journey.hwod;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 12:25
 * @describe: 补种未成活胡杨
 */
public class ReplantingFailedPoplar {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        // 一共有多少棵树
        int n = scanner.nextInt();

        boolean[] trees = new boolean[n + 1];

        Arrays.fill(trees, true);
        // 死了多少棵树
        int m = scanner.nextInt();

        // 制定位置设置为 false
        for (int i = 0; i < m; i++) {
            int place = scanner.nextInt();
            trees[place] = false;
        }

        // 可以补救多少棵树
        int k = scanner.nextInt();

        // 窗口内死数的总量
        int deadTreeNum = 0;

        // 坐窗口 从1开始往右滑
        int left = 1;
        
        int ans = 0;

        for (int right = 1; right <= n; right++) {

            if (!trees[right]) {
                deadTreeNum++;
            }

            while (deadTreeNum > k) {
                if (!trees[left]) {
                    deadTreeNum--;
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }
        System.out.println(ans);
    }
}
