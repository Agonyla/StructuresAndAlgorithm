package algorithm_journey.class030;

import algorithm_journey.utils.MathUtils;

/**
 * 只出现一次的数字 II
 *
 * @author: Agony
 * @create: 2024/6/11 17:23
 * @describe: 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 * @link: <a href="https://leetcode.cn/problems/single-number-ii/description/">只出现一次的数字 II</a>
 */
public class Code06_OneKindNumberLessMTimes {

    public static void main(String[] args) {
        int a = 7;
        int[] cnts = new int[5];
        for (int i = 0; i < 5; i++) {
            cnts[i] = a >>> i & 1;
        }
        MathUtils.printArr(cnts);

        int[] arr = {0, 1, 0, 1, 0, 1, 99};
        System.out.println(singleNumber(arr));

        arr = new int[]{2, 2, 3, 2};
        System.out.println(singleNumber(arr));

    }

    public static int singleNumber(int[] nums) {
        return find(nums, 3);
    }


    /**
     * 数组中只有一个元素出现了小于M次，别的都出现了M次
     * <p>
     * 如果一个数出现了M次数，那么它在各个位上 1 的出现次数也为M次
     * 准备一个32位的数组，用来记录数组 arr 所有数在各个位上出现 1 的次数和
     * cnts[0] 表示 arr 在第 0 位置出现 1 的次数
     * cnts[1] 表示 arr 在第 1 位置出现 1 的次数
     * ...
     * 如果 cnts[i]%M==0 就表示在该位上，1都出现了M次，即改为上所的数没有出现
     * 如果 cnts[i]%M!=0 就表示在该位上，出现次数小于M次的数在该位上位1
     * 然后通过拼接各个位上的1得到ans
     *
     * @param arr 数组
     * @param M   出现次数
     * @return 出现次数小于M的数
     */
    public static int find(int[] arr, int M) {
        int ans = 0;
        int[] cnts = new int[32];
        // 记录 arr 所有数在各个位上出现 1 的次数和
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                cnts[i] += num >>> i & 1;
            }
        }
        // 拼接各个位上的1得到ans
        for (int i = 0; i < 32; i++) {
            if (cnts[i] % M != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
