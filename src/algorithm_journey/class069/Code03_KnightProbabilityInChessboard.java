package algorithm_journey.class069;

/**
 * @author: Agony
 * @create: 2024/9/11 10:32
 * @describe:
 * @link:
 */
public class Code03_KnightProbabilityInChessboard {

    // todo

    // 骑士在棋盘上的概率
    //
    // 暴力递归
    // 设计 double f(int n, int i, int j, int k)
    // -> 返回当前处于(i,j)位置还剩下k步不越界的概率
    // n -> 棋盘大小
    // i -> 当前来到第i行
    // j -> 当前来到第j列
    // k -> 还剩下k步
    // 边界条件：
    // i<0||i>=n||j<0||j>=n
    // 可能性分析：
    // 1️⃣k==0 -> 说明肯定不越界
    // 2️⃣k!=0 -> 继续往八个方向尝试，每一种都是等概率的所以要/8
    //
    // 动态规划
    // 讲解中没提，可以自己尝试一下 ❓❓❓
}
