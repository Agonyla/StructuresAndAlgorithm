package algorithm_journey.class045;

/**
 * 数组中两个数的最大异或值
 *
 * @author: Agony
 * @create: 2024/7/12 10:12
 * @describe: 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * <p>
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28
 * <p>
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * @link: <a href="https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/description/">数组中两个数的最大异或值</a>
 */
public class Code02_TwoNumbersMaximumXor {


    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(findMaximumXOR(nums));
        System.out.println(right(nums));

        nums = new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        System.out.println(findMaximumXOR(nums));


        System.out.println("===========");

        System.out.println(right(nums));
    }

    // 数组中两个数的最大异或值

    // 思路
    // 前缀树实现
    // 把数组中的所有数构建成一棵前缀树
    // 构建的时候先把数组中的最大值取出，int 类型 31 位，如果最大值最高位在15位，那么前的0，就可以直接省略了，不需要浪费空间去构建
    //
    // 数组中的每一个数和前缀树中的值去异或求最大值

    // hashset实现
    // 先取出最大值，从最大值的最高位开始构建
    // want -> 想要的状态位
    // num -> 实际的状态位，只保留最高位到i位置的状态，i一下的位置都置0
    // 如果 num 和 set 中的某个数 x 异或 能达成 want，就让 ans = want
    // 即 want = num ^ x
    // 那么 只要去set中查询是否存在 x ，能达成want
    // -> set.contains(x) -> x = num * want


    /**
     * 暴力求解用于验证
     *
     * @param nums
     * @return
     */
    public static int right(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    /**
     * 求数组中两个数的最大异或值
     *
     * @param nums
     * @return
     */
    public static int findMaximumXOR(int[] nums) {

        build(nums);
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, maxXOR(num));
        }
        clear();
        return max;
    }


    /**
     * 当前数字在前缀树中异或的最大值
     *
     * @param num
     * @return
     */
    public static int maxXOR(int num) {

        // 自己异或自己肯定能得到0
        int ans = 0;
        int cur = 1;
        for (int i = high; i >= 0; i--) {

            // num当前位的状态
            int status = (num >> i) & 1;

            // 想要位的状态
            int want = status ^ 1;
            // 如果没有就取相反的状态
            if (tree[cur][want] == 0) {
                want ^= 1;
            }
            ans |= (status ^ want) << i;
            cur = tree[cur][want];
        }
        return ans;
    }


    public static int MAX_LENGTH = 3000001;

    // 只存放 1 和 0
    public static int[][] tree = new int[MAX_LENGTH][2];

    public static int cnt;


    // 数组中最大数的最高位
    public static int high;

    /**
     * 初始化前缀树
     *
     * @param arr
     */
    public static void build(int[] arr) {
        cnt = 1;
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }

        high = 31 - Integer.numberOfLeadingZeros(max);

        for (int i : arr) {
            insert(i);
        }

    }


    /**
     * 把数字插入到前缀树
     *
     * @param num
     */
    public static void insert(int num) {

        int cur = 1;

        for (int i = high; i >= 0; i--) {
            int path = (num >> i) & 1;
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
        }
    }


    /**
     * 清除前缀树
     */
    public static void clear() {
        for (int i = 0; i < cnt; i++) {
            tree[i][0] = tree[i][1] = 0;
        }
    }


}























