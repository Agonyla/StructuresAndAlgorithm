package algorithm_journey.leetcode;

import algorithm_journey.utils.MathUtils;

import java.util.Stack;

/**
 * @author: Agony
 * @create: 2025/4/6 16:00
 * @describe: 每日温度
 * <a href="https://leetcode.cn/problems/daily-temperatures/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">每日温度</a>
 */
public class LeetCode739 {


    public static void main(String[] args) {


        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ans1 = dailyTemperatures(temperatures);
        MathUtils.printArr(ans1);
        int[] ans2 = dailyTemperatures2(temperatures);
        MathUtils.printArr(ans2);

        temperatures = new int[]{30, 40, 50, 60};
        ans1 = dailyTemperatures(temperatures);
        MathUtils.printArr(ans1);
        ans2 = dailyTemperatures2(temperatures);
        MathUtils.printArr(ans2);


        temperatures = new int[]{30, 60, 90};
        ans1 = dailyTemperatures(temperatures);
        MathUtils.printArr(ans1);
        ans2 = dailyTemperatures2(temperatures);
        MathUtils.printArr(ans2);


    }

    public static int[] dailyTemperatures(int[] temperatures) {

        int[] ans = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {


            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }


    public static int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = temperatures[i];
            while (!stack.isEmpty() && current > temperatures[stack.peek()]) {

                int preIndex = stack.pop();
                ans[preIndex] = i - preIndex;
            }
            stack.push(i);
        }

        return ans;
    }
}
