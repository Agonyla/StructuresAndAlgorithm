package algorithm_basic.class20;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Agony
 * @Create 2023/9/22 19:08
 * @Version 1.0
 * @describe: 题目
 * <p>
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 */

// arr : 2, 6, 8, 5, 6, 6, 9, 5, 4, 8,
// 6个人 洗咖啡时间2 挥发时间9
// 最短时间13
public class Code03_Coffee {


    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }


    public static class Machine {
        int startTime;
        int costTime;

        public Machine(int startTime, int costTime) {
            this.startTime = startTime;
            this.costTime = costTime;
        }
    }

    /**
     * @param arr 每一个咖啡机冲一杯咖啡的时间
     * @param n   有n个人需要喝咖啡
     * @param a   洗完一个杯子时间
     * @param b   杯子自然挥发干净的时间
     * @return 所有杯子洗完后来到最少的时间点
     */
    public static int bestTime1(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //小根堆，模拟一群人的排队最优解
        PriorityQueue<Machine> heap = new PriorityQueue<>((Machine o1, Machine o2) -> (o1.startTime + o1.costTime) - (o2.startTime + o2.costTime));

        for (int i : arr) {
            heap.add(new Machine(0, i));
        }
        //记录每个人排队喝完咖啡之后的时间点
        int[] startTimeArr = new int[n];
        for (int i = 0; i < n; i++) {
            Machine poll = heap.poll();
            poll.startTime += poll.costTime;
            startTimeArr[i] = poll.startTime;
            heap.add(poll);
        }
        return minTime1(startTimeArr, a, b, 0, 0);
    }

    /**
     * @param arr      每个人排队喝完咖啡之后的时间点
     * @param a        洗完一个杯子时间
     * @param b        杯子自然挥发干净的时间
     * @param index    当前的杯子
     * @param washTime 洗杯子机器可以用的时间点
     * @return 所有杯子洗完后来到最少的时间点
     * @describe: 递归尝试
     */
    public static int minTime1(int[] arr, int a, int b, int index, int washTime) {
        // base case
        if (index == arr.length) {
            return 0;
        }
        // 当前的杯子选择清洗
        int finishWash = Math.max(arr[index], washTime) + a;
        int finishWash2 = minTime1(arr, a, b, index + 1, finishWash); // 下一个杯子
        int p1 = Math.max(finishWash, finishWash2); // 洗的时间的最大值

        // 当前杯子选择挥发
        int finishWash1 = arr[index] + b;
        int finishWash3 = minTime1(arr, a, b, index + 1, washTime); // 下一个杯子
        int p2 = Math.max(finishWash1, finishWash3); // 挥发时间的最大值
        return Math.min(p1, p2); // 取清洗或者挥发时间的最小值
    }

    public static int bestTime2(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //小根堆，模拟一群人的排队最优解
        PriorityQueue<Machine> heap = new PriorityQueue<>((Machine o1, Machine o2) -> (o1.startTime + o1.costTime) - (o2.startTime + o2.costTime));

        for (int i : arr) {
            heap.add(new Machine(0, i));
        }
        //记录每个人排队喝完咖啡之后的时间点
        int[] startTimeArr = new int[n];
        for (int i = 0; i < n; i++) {
            Machine poll = heap.poll();
            poll.startTime += poll.costTime;
            startTimeArr[i] = poll.startTime;
            heap.add(poll);
        }
        return minTime2(startTimeArr, a, b, 0);
    }

    /**
     * @param arr      每个人排队喝完咖啡之后的时间点
     * @param a        洗完一个杯子时间
     * @param b        杯子自然挥发干净的时间
     * @param washTime 洗杯子机器可以用的时间点
     * @return 所有杯子洗完后来到最少的时间点
     * @describe: 动态规划
     */
    public static int minTime2(int[] arr, int a, int b, int washTime) {

        // index 取值范围：0~arr.length
        // washTime 取值范围：没有明确 => 按照全部选择洗的情况取最大值
        int maxWashTime = washTime;
        int N = arr.length;
        for (int i : arr) {
            maxWashTime = Math.max(maxWashTime, i) + a;
        }
        int[][] dp = new int[N + 1][maxWashTime + 1];

        // index == N 时全是0  

        // index 依赖index+1, 从下往上遍历
        for (int index = N - 1; index >= 0; index--) {
            for (int wash = 0; wash <= maxWashTime; wash++) {
                // 当前的杯子选择清洗
                int finishWash = Math.max(arr[index], wash) + a;
                // 当wash接近maxWashTime的时候，finishWash 此时可能会越界
                // 比如有十杯咖啡要清洗，最大清洗时间是100，即没有必要去讨论第五杯咖啡在100时刻闲下来的情况
                //  => 因为这个wash是间断变化的，处于每个间断点之间的值无意义，只是为了做成表而填
                // 比如 arr = {1,7} a=5
                // wash真正可用的值{0,6,12},别的值没意义只是为了填表而村存在，当wash>6时，finishWash 就可能越界
                if (finishWash > maxWashTime) {
                    break;
                }
                int finishWash2 = dp[index + 1][finishWash]; // 下一个杯子
                int p1 = Math.max(finishWash, finishWash2); // 洗的时间的最大值

                // 当前杯子选择挥发
                int finishWash1 = arr[index] + b;
                int finishWash3 = dp[index + 1][washTime]; // 下一个杯子
                int p2 = Math.max(finishWash1, finishWash3); // 挥发时间的最大值
                dp[index][wash] = Math.min(p1, p2);
            }
        }
        return dp[0][0];


    }

    public static void main(String[] args) {


        // arr : 2, 6, 8, 5, 6, 6, 9, 5, 4, 8,
        // 6个人 洗咖啡时间2 挥发时间9
        int[] arr = {2, 6, 8, 5, 6, 6, 9, 5, 4, 8};
        int n = 6;
        int a = 2;
        int b = 9;
        System.out.println(right(arr, n, a, b));
        System.out.println(bestTime1(arr, n, a, b));
        System.out.println(bestTime2(arr, n, a, b));

    }


}