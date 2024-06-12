package algorithm_journey.class031;

/**
 * 颠倒二进制位
 *
 * @author: Agony
 * @create: 2024/6/12 15:51
 * @describe: 颠倒给定的 32 位无符号整数的二进制位。
 * n=1010 0110 -> n=0110 0101
 * @link: <a href="https://leetcode.cn/problems/reverse-bits/description/">颠倒二进制位</a>
 */
public class Code05_ReverseBits {


    /**
     * 交换实现
     * 如  n = abcd efgh
     * 1v1 交换
     * ->  n = badc fehg
     * 2v2 交换
     * ->  n = dcba hgfe
     * 4v4 交换
     * ->  n = hgfe dcba
     * <p>
     * 实现 1v1 交换
     * n & 1010 1010 -> a0c0 e0g0
     * n & 0101 0101 -> 0b0d 0f0h
     * n & 1010 1010 >>1 -> 0a0c 0e0g
     * n & 0101 0101 >>1 -> b0d0 f0h0
     * | 操作 -> n = badc fehg
     * 实现 2v2
     * n & 1100 1100 -> ba00 fe00 >> 2 -> 00ba 00fe
     * n & 0011 0011 -> 00dc 00hg << 2 -> dc00 hg00
     * | 操作 -> n = dcba hgfe
     * ...
     *
     * @param n
     * @return
     */
    public static int reverseBits(int n) {
        // 1v1
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        // 2v2
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        // 4v4
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        // 8v8
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        // 16v16
        n = (n >>> 16) | (n << 16);
        return n;
    }

    /**
     * 传统实现
     *
     * @param n
     * @return
     */
    public int reverseBitsTraditional(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int t = (n >> i) & 1;
            if (t == 1) {
                ans |= (1 << (31 - i));
            }
        }
        return ans;
    }
}
