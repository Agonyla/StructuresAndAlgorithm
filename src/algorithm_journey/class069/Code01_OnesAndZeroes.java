package algorithm_journey.class069;

/**
 * @author: Agony
 * @create: 2024/9/9 20:06
 * @describe:
 * @link:
 */
public class Code01_OnesAndZeroes {


    // todo

    // 一和零(多维费用背包)
    //
    // 暴力递归
    // 设计 int[] f(String[] strs, int i, int zeros, int ones)
    // -> 返回strs从 i 位置开始 希望0的数量不超过zeros、一的数量不超过ones 的最长子集数量
    // strs -> 字符串数组
    // i -> strs[i...]
    // zeros -> 0 的数量不超过 zeros
    // ones -> 1 的数量不超过 ones
    // 可能性分析：
    // 来到i位置
    // 1️⃣. 不选择当前字符串
    // -> 调用 f(strs, i+1, zeros, ones)
    // 2️⃣. 选择当前字符串
    // 需要满足strs[i]字符串的0的数量超过zeros，1的数量不超过ones
    // -> 调用 f(strs, i+1, zeros-z, ones-o)  (z表示当前字符串中0的数量，o表示当前字符串中1的数量)
    // 然后返回两种可能性中的最大值
    //
    // 记忆化搜索
    // 可变参数：i，zeros，ones
    // 设计 int[][][] dp = new int[][][];
    // i取值范围：0~strs.length-1
    // zeros取值范围：0~zeros
    // ones取值范围：0~ones
    // -> dp = new int[strs.length][zeros+1][ones+1]
    // ...
    //
    // 动态规划
    // 位置依赖：
    // 1️⃣. 先建立一个三维坐标轴
    // 2️⃣. 从递归分析依赖，当来到(i,z,o)位置时，都依赖上一层 (i+1, , )
    // 3️⃣. 由于每一层内之间的格子相互之间是不依赖的，所有按照顺序填都可以
    // 填表顺序：
    // 从最上面一层开始，整体顺序从上往下开始填
    // 特殊位置分析：
    // 最上面一层 i==length，dp表都是0
    //
    // 动态规划 + 空间压缩
    // 用二维表代替三维表
    // 因为每一层都依赖上一层，所以可以用二维表示三维
    // 当一层更新完之后，该层都表示上一层的值
    // 此时来到 (i,zeros,ones)位置时
    // 从上面递归可以看出 f(strs, i+1, zeros-z, ones-o)
    // (zeros, ones) 都依赖其所有左边及下边的值 (也就是该位置对应的上一层所有左边及下边的值)
    // 所有二维dp的填表顺序为：
    // 总体方向从右往左，然后再从上往下
    //

}
