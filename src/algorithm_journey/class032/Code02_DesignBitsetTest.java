package algorithm_journey.class032;

/**
 * 设计位集
 *
 * @author: Agony
 * @create: 2024/6/13 14:57
 * @describe: 位集 Bitset 是一种能以紧凑形式存储位的数据结构。
 * <p>
 * 请你实现 Bitset 类。
 * <p>
 * Bitset(int size)         用 size 个位初始化 Bitset ，所有位都是 0 。
 * void fix(int idx)        将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
 * void unfix(int idx)      将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
 * void flip()              翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
 * boolean all()            检查 Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * boolean one()            检查 Bitset 中 是否 至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * int count()              返回 Bitset 中值为 1 的位的 总数 。
 * String toString()        返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。
 * <p>
 * Bitset bs = new Bitset(5);   // bitset = "00000".
 * bs.fix(3);                   // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
 * bs.fix(1);                   // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
 * bs.flip();                   // 翻转每一位上的值，此时 bitset = "10101" 。
 * bs.all();                    // 返回 False ，bitset 中的值不全为 1 。
 * bs.unfix(0);                 // 将 idx = 0 处的值更新为 0 ，此时 bitset = "00101" 。
 * bs.flip();                   // 翻转每一位上的值，此时 bitset = "11010" 。
 * bs.one();                    // 返回 True ，至少存在一位的值为 1 。
 * bs.unfix(0);                 // 将 idx = 0 处的值更新为 0 ，此时 bitset = "01010" 。
 * bs.count();                  // 返回 2 ，当前有 2 位的值为 1 。
 * bs.toString();               // 返回 "01010" ，即 bitset 的当前组成情况。
 * @link: <a href="https://leetcode.cn/problems/design-bitset/description/">设计位集</a>
 */
public class Code02_DesignBitsetTest {


    public static void main(String[] args) {
        Bitset bs = new Bitset(5);         // bitset = "00000".
        bs.fix(3);                          // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
        bs.fix(1);                          // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
        bs.flip();                              // 翻转每一位上的值，此时 bitset = "10101" 。
        System.out.println(bs.all());           // 返回 False ，bitset 中的值不全为 1 。
        bs.unfix(0);                        // 将 idx = 0 处的值更新为 0 ，此时 bitset = "00101" 。
        bs.flip();                              // 翻转每一位上的值，此时 bitset = "11010" 。
        System.out.println(bs.one());           // 返回 True ，至少存在一位的值为 1 。
        bs.unfix(0);                        // 将 idx = 0 处的值更新为 0 ，此时 bitset = "01010" 。
        System.out.println(bs.count());         // 返回 2 ，当前有 2 位的值为 1 。
        System.out.println(bs.toString());      // 返回 "01010" ，即 bitset 的当前组成情况。


        //[
        // "Bitset",    18
        // "fix",       3       000100000000000000
        // "unfix",     8       000100000000000000
        // "count",             1
        // "toString",
        // "toString",
        // "count",             1
        // "toString",
        // "fix",       11      000100000001000000
        // "flip",              111011111110111111
        // "count",             15
        // "unfix",     3       111011111110111111
        // "flip",              000100000001000000
        // "count",     2       这里出现问题
        // "toString",
        // "fix",       8       000100001001000000
        // "fix",       10      000100001011000000
        // "fix",       5       000101001011000000
        // "unfix",     3       000001001011000000


        new Bitset(18);
        bs.fix(3);
        bs.unfix(8);

        // todo 验证一下

    }

    static class Bitset {


        private final int[] arr;
        private int ones;
        private int zeros;
        private boolean reverse;
        private final int limit;

        public Bitset(int size) {
            arr = new int[(size + 32 - 1) / 32];
            this.ones = 0;
            this.zeros = size;
            this.reverse = false;
            this.limit = size;
        }

        /**
         * 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
         *
         * @param idx
         */
        public void fix(int idx) {
            int index = idx / 32;
            int bit = idx % 32;
            // 还没有翻转
            if (!reverse) {
                // 1: 存在
                // 0: 不存在
                // 只有在该位为 0 才执行
                if ((arr[index] & 1 << bit) == 0) {
                    arr[index] |= 1 << bit;
                    ones++;
                    zeros--;
                }
            } else { // 反转之后
                // 1: 不存在
                // 0: 存在
                // 因为只有当该位为1时才执行
                if ((arr[index] & 1 << bit) != 0) {
                    // 所以可以用 arr[index] ^= 1 << bit;
                    arr[index] &= ~(1 << bit);
                    ones++;
                    zeros--;
                }
            }
        }

        /**
         * 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
         *
         * @param idx
         */
        public void unfix(int idx) {
            int index = idx / 32;
            int bit = idx % 32;
            if (!reverse) {
                if ((arr[index] & 1 << bit) == 1) {
                    arr[index] &= ~(1 << bit);
                    ones--;
                    zeros++;
                }
            } else {
                if ((arr[index] & 1 << bit) != 1) {
                    arr[index] |= 1 << bit;
                    ones--;
                    zeros++;
                }
            }
        }


        /**
         * 翻转 Bitset 中每一位上的值。
         */
        public void flip() {
            reverse = !reverse;
            int temp = ones;
            ones = zeros;
            zeros = temp;
        }

        public boolean all() {
            return ones == limit;
        }

        public boolean one() {
            return ones >= 1;
        }

        public int count() {
            return ones;
        }


        /**
         * 返回 Bitset 的当前组成情况
         *
         * @return
         */
        public String toString() {

            // arr[0] = 0100 0100   有2和6
            // arr[1] = 1100 0110   有9、10、14、15
            // str = 00100010 0110 0011
            StringBuilder sb = new StringBuilder();
            int status = 0;
            int index = 0;
            for (int i = 0; i < limit; i++) {
                for (int j = 0; j < 32 && index < limit; j++) {
                    status = arr[i] >> j & 1;
                    status ^= reverse ? 1 : 0;
                    sb.append(status);
                    index++;
                }
            }
            return sb.toString();
        }
    }

}
