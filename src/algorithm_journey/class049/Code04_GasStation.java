package algorithm_journey.class049;

/**
 * 加油站
 *
 * @author: Agony
 * @create: 2024/7/24 9:58
 * @describe: 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * @link: <a href="https://leetcode.cn/problems/gas-station/description/">加油站</a>
 */
public class Code04_GasStation {

    // todo

    // 加油站
    // 以上题目都是用结尾情况下的答案
    // 该题是用开头讨论

    // 思路：
    // 右窗口r：表示要进来的数
    // r=(l+length++)%n
    // 可以用来表示循环
    // 如 数组长度n=7，当前l在3位置，此时数组长度length=0
    // r=(l+length++)%n=3%7=3,表示3位置的数字要进来了，length=1
    //
    // 左窗口从0开始，右窗口往右边扩
    // 如果累加和 sum>=0，右窗口不断往右扩
    // 当sum小于0时，
    // 跳出循环，左窗口往右移动，且窗口长度减1
    // sum一直>=0，右窗口不断向右扩
    // 直到窗口长度等于数组长度


    /**
     * 加油站
     *
     * @param gas  油数组
     * @param cost 消耗数组
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        return 0;
    }

}
