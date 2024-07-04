package algorithm_journey.class038;

/**
 * @author: Agony
 * @create: 2024/7/1 20:33
 * @describe:
 * @link:
 */
public class Code07_TowerOfHanoi {


    public static void main(String[] args) {
        hanoi(3, "左", "右", "中");
    }

    // 打印n层汉诺塔问题的最优移动轨迹

    // 思路
    // 实现函数 f(int i, String from, String to, String other)
    // i -> 有 i 个元素
    // from -> 从 from 的位置出发
    // to -> 来到 to 的位置
    // other -> 其余位置

    // 先把上面的 i-1 个元素 从 from 移到 other -> f(i-1, from, other, to)
    // 把底下的 i 移到 to，System.out.println("....")
    // 再把上面的 i-1 个元素 从 other 移到 to -> f(i-1, other, to, from)

    // 把底部上面的所有元素看成一个整体 -> 实现只有两个元素的移动


    /**
     * 汉诺塔移动顺序
     * 把所有的元素从 from 移到 to
     *
     * @param i     一共 i 个元素
     * @param from  初始位置
     * @param to    目标位置
     * @param other 其他位置
     */
    public static void hanoi(int i, String from, String to, String other) {
        if (i == 0) {
            return;
        }
        hanoi(i - 1, from, other, to);
        System.out.println("第 " + i + " 个从 " + from + " 移动到 " + to);
        hanoi(i - 1, other, to, from);
    }

}
