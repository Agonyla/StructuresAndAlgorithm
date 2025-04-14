package algorithm_journey.hwod;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/14 11:31
 * @describe: 称砝码
 * <a href="https://www.nowcoder.com/practice/f9a4c19050fc477e9e27eb75f3bfd49c?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">称砝码</a>
 */
public class HJ41 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] weights = new int[n];
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }


        HashSet<Integer> set = new HashSet<>();

        set.add(0);
        // process(set, weights, nums, 0, 0);
        // System.out.println(set.size());
        System.out.println(dpSolution(weights, nums));
    }


    public static void process(HashSet<Integer> set, int[] weights, int[] nums, int index, int have) {

        if (index == weights.length) {
            return;
        }


        for (int i = 0; i <= nums[index]; i++) {
            int weight = have + weights[index] * i;
            set.add(weight);
            process(set, weights, nums, index + 1, weight);
        }
    }


    public static int dpSolution(int[] weights, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        set.add(0);

        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            int count = nums[i];

            // 创建一个副本用于遍历
            Integer[] currentWeights = set.toArray(new Integer[0]);

            for (int j = 1; j <= count; j++) {
                int additionalWeight = j * weight;
                for (Integer currentWeight : currentWeights) {
                    set.add(currentWeight + additionalWeight);
                }
            }
        }

        return set.size();
    }
}
