package algorithm_journey.class048;

/**
 * 用邮票贴满网格图
 *
 * @author: Agony
 * @create: 2024/7/21 16:23
 * @describe: 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 * <p>
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 * <p>
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 * @link: <a href="https://leetcode.cn/problems/stamping-the-grid/description/">用邮票贴满网格图</a>
 */
public class Code04_StampingTheGrid {

    // todo

    public static void main(String[] args) {

    }

    // 用邮票贴满网格图

    // 注意：
    // 3x2的邮票，不能把它横过来贴

    // 思路：
    // 遍历数组，来到一个数，看它按照邮票的规格能不能贴
    // 判断能不能贴，看这个返回的累加和是不是为0
    // 准备一个差分数组，
    // 在这个差分数组上贴邮票 -> 贴了的位置变1
    // 原始数组某一位置为0，差分数组该位置为1 -> 说明该位置已经被贴了邮票


    /**
     * 用邮票贴满网格图
     *
     * @param grid        网格
     * @param stampHeight 邮票高度(行)
     * @param stampWidth  邮票宽度(列)
     * @return 能否贴满
     */
    public static boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        return false;
    }
}
