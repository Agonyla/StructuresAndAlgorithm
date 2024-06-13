package algorithm_journey.class032;

import java.util.HashSet;

/**
 * 位图的实现
 *
 * @author: Agony
 * @create: 2024/6/11 19:51
 * @describe: 位图的实现
 * <p>
 * 其实就是用bit组成的数组来存放值，用bit状态1、0代表存在、不存在，取值和存值操作都用位运算
 * 限制是必须为连续范围且不能过大。好处是极大的节省空间，因为1个数字只占用1个bit的空间。
 * <p>
 * 如 一个 int 有32wei，就可以表示 0～31 这32个数字是否存在
 * int[4] arr; 就可以表示 0～127 这128个数字是否存在
 * <p>
 * Bitset(int size)             初始化位图的大小，只支持0~n-1所有数字的增删改查
 * void add(int num)            把num加入到位图
 * void remove(int num)         把num从位图中删除
 * void reverse(int num)        如果位图里没有num，就加入；如果位图里有num，就删除
 * boolean contains(int num)    查询num是否在位图中
 * @link:
 */
public class Code01_Bitset {


    // 对数器测试
    public static void main(String[] args) {
        int n = 1000;
        int testTimes = 10000;
        System.out.println("测试开始");
        // 实现的位图结构
        Bitset bitSet = new Bitset(n);
        // 直接用HashSet做对比测试
        HashSet<Integer> hashSet = new HashSet<>();
        System.out.println("调用阶段开始");
        for (int i = 0; i < testTimes; i++) {
            double decide = Math.random();
            // number -> 0 ~ n-1，等概率得到
            int number = (int) (Math.random() * n);
            if (decide < 0.333) {
                bitSet.add(number);
                hashSet.add(number);
            } else if (decide < 0.666) {
                bitSet.remove(number);
                hashSet.remove(number);
            } else {
                bitSet.reverse(number);
                if (hashSet.contains(number)) {
                    hashSet.remove(number);
                } else {
                    hashSet.add(number);
                }
            }
        }
        System.out.println("调用阶段结束");
        System.out.println("验证阶段开始");
        for (int i = 0; i < n; i++) {
            if (bitSet.contains(i) != hashSet.contains(i)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("验证阶段结束");
        System.out.println("测试结束");
    }


    static class Bitset {


        public int[] arr;


        public Bitset(int size) {
            // 向上取整数
            // (a+b-1)/b -> 实现结果向上取整
            // 1. a是b的整数倍 -> (b-1)/b < 1
            // 2. a不是b整数倍 -> 1/b < 1
            arr = new int[(size + 32 - 1) / 32];
        }

        /**
         * 添加
         * i = num / 32; 数组索引
         * j = num % 32; 在一个 int 中的第几位
         * 如 num=33
         * i=1，j=1 表示 arr[1] 这个数的二进制中 0000...0010 该位是 1
         *
         * @param num
         */
        public void add(int num) {
            arr[num / 32] |= 1 << (num % 32);
        }


        /**
         * 移除
         *
         * @param num
         */
        public void remove(int num) {
            arr[num / 32] &= ~(1 << (num % 32));
        }


        /**
         * 反转
         *
         * @param num
         */
        public void reverse(int num) {
            arr[num / 32] ^= 1 << (num % 32);
        }

        /**
         * 是否包含
         * int j = 1 << (num % 32); 表示该数字在二进制中的位置
         * int i = arr[num / 32] & 1 << (num % 32);
         * & 操作之后如果 存在的话 name i==j, 否则就不存在
         *
         * @param num
         * @return
         */
        public boolean contains(int num) {

            // int i = arr[num / 32] & 1 << (num % 32);
            // int j = 1 << (num % 32);
            // return i == j;

            // 也可以通过把 arr[num / 32] 右移实现
            // return ((arr[num / 32] >> (num % 32)) & 1) == 1;

            return (arr[num / 32] & 1 << (num % 32)) == 1 << num % 32;
        }
    }
}











