package algorithm_journey.class030;

/**
 * 只出现一次的数字 III
 *
 * @author: Agony
 * @create: 2024/6/11 16:04
 * @describe: 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 * @link: <a href="https://leetcode.cn/problems/single-number-iii/description/">只出现一次的数字 III</a>
 */
public class Code05_DoubleNumber {

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 2, 5};
        for (int i : singleNumber(arr)) {
            System.out.print(i + " ");
        }

    }

    /**
     * @param nums
     * @return 返回出现一次的两个数
     */
    public static int[] singleNumber(int[] nums) {

        int eorAll = 0;
        for (int num : nums) {
            eorAll ^= num;
        }
        // eorAll = a^b;
        int rightOne = getRightOne(eorAll);

        // a和b在最右侧1的这一位上不用，可以把nums数组分成这一位上是1和这一位上不是1的两个阵营
        // a和b属于不同阵营
        int eor1 = 0;
        int eor2 = 0;
        for (int num : nums) {
            if ((num & rightOne) == 0) {
                eor1 ^= num;
            }
        }
        // eor1 = a or b;
        eor2 = eorAll ^ eor1;
        return new int[]{eor1, eor2};


    }


    /**
     * Brian Kernighan算法
     * 提取出一个数最右侧的1
     * n&(-n)
     * <p>
     * n   =    1101 0100
     * ~n  =    0010 1011
     * ~n+1=    0010 1100 = -n
     * n&(-n) = 0000 0100
     *
     * @param n 数
     * @return 最右侧的1的二进制
     */
    public static int getRightOne(int n) {
        return n & (-n);
    }
}
