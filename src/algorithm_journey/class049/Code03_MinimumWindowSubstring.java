package algorithm_journey.class049;

/**
 * @author: Agony
 * @create: 2024/7/24 9:13
 * @describe:
 * @link:
 */
public class Code03_MinimumWindowSubstring {

    // todo

    public static void main(String[] args) {

    }

    // 最小覆盖子串

    // 思路：
    // 设计一个 int[] cnts = new int[]
    // 下标为字符的ASCII码，所有的字符可以转成数组对应下标。'a' -> 97
    // 值为其欠的个数
    // target="aa"
    // cnts['a']=-2
    // 一个变量 debt，表示总欠债

    // 流程：
    // 串口从左边界开始，右窗口遇到一个字符，执行 cnts[字符]++
    // 如果遇到欠款字符，debt-=1
    // 当debt==0时候，更新答案
    // 左窗口向右滑动，遇到字符执行 cnts[字符]--
    // 如果更新了会让debt!=0时，那就在此停止
    // 更新答案
    // 。。。
    // 循环往复向右滑动，知道右窗口来到字符串边界
}
