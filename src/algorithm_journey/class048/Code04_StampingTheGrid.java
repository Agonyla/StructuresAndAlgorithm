package algorithm_journey.class048;

/**
 * @author: Agony
 * @create: 2024/7/21 16:23
 * @describe:
 * @link:
 */
public class Code04_StampingTheGrid {

    // todo

    // 用邮票贴满网格图

    // 注意：
    // 3x2的邮票，不能把它横过来贴

    // 思路：
    // 遍历数组，来到一个数，看它按照邮票的规格能不能贴
    // 判断能不能贴，看这个返回的累加和是不是为0
    // 准备一个差分数组，
    // 在这个数组上贴邮票 -> 贴了的位置变1
    // 原始数组某一位置为0，差分数组该位置为1 -> 说明该位置已经被贴了邮票
}
