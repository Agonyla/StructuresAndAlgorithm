package algorithm_journey.class066;

/**
 * @author: Agony
 * @create: 2024/7/26 11:23
 * @describe:
 * @link:
 */
public class Code03_DecodeWays {

    // todo

    // 解码方法

    // 思路：

    // numDecoding1
    // -> 暴力递归
    // 设计函数 int f(char[] s, int i)
    // return -> 从i位置出发一共有几种解码方法
    // s -> 字符串转成的数组
    // i -> 当前来到i位置
    // 当i来到边界时，返回1，-> 表示之前的尝试有一种解码方法
    // 如果i位置为0，-> 无法解码，直接返回0
    // 从i+1位置尝试，
    // 从i+2位置尝试，(i+2越界，且i位置和i+1位置的和<=26，才能尝试)
    // 两种答案累加
    //
    // numDecoding2
    // -> 改记忆化搜索
    // 设计 int[] dp = new int[s.length]，初始化都是-1
    // 如果dp[i]!=-1，说明之前来过该位置，直接返回
    // 如果i位置为0，-> 无法解码，直接返回0
    // 依然是两种尝试
    // 在返回之前把当前位置的值记录到dp中
    // 返回ans
    //
    // numDecoding3
    // -> 严格位置依赖的动态规划
    // i位置依赖i+1位置和i+2位置
    // int[] dp = new int[s.length+1]
    // 所以从右往左填，最后返回dp[0]
    // dp[n]=1
    // 如果i位置为0，dp[i]=0
    // 否则 dp[i]=dp[i+1]
    // 如果可以 dp[i]+=dp[i+2]
    // 最后返回 dp[0]
    //
    // numDecoding4
    // 压缩空间
    // 显然i位置只依赖i+1和i+2
    // 那么就不需要这几一个n长度的数组
    // 直接用连个变量代替
    // 在每次使用完之后更新
}
