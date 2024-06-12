package algorithm_journey.class031;

/**
 * 数字范围按位与
 *
 * @author: Agony
 * @create: 2024/6/12 15:30
 * @describe: 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * @link: <a href="https://leetcode.cn/problems/bitwise-and-of-numbers-range/description/">数字范围按位与</a>
 */
public class Code04_LeftToRightAnd {

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5, 7));
        System.out.println(rangeBitwiseAnd(0, 0));
        System.out.println(rangeBitwiseAnd(1, 2147483647));
    }


    /**
     * left == right 时，直接返回
     * left < right 此时 right - 1 必然在[left, right]内，所以 right 最右侧的1不可能保留
     * eg. right = 0101 1010
     * right -1  = 0101 1001
     * 然后 right =  right - right最右侧1的二进制表达
     * -> right = 0101 1000
     * 再次判断 left 是否小于 right
     * 如果 left < right -> 最右侧的1又不能保留
     * right     = 0101 1000
     * right - 1 = 0101 0111
     * right 再减去right最右侧1的二进制表达
     * right = 0101 0000
     * ...
     * 直到 right <= left
     *
     * @param left
     * @param right
     * @return
     */
    public static int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right -= right & -right;
        }
        return right;
    }
}
