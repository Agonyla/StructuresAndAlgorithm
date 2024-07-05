package algorithm_journey.class040;

/**
 * @author: Agony
 * @create: 2024/7/3 15:07
 * @describe:
 * @link:
 */
public class NQueens {

    // todo

    // N皇后问题
    //
    // 思路
    // 设计函数 int f(int[] path, int i, int n)
    // return -> 一共有几种摆法
    // path -> 路径用来记录一行摆放的位置，如 path = {1,3,0,2}
    // 表示第 0 行摆在第 1 列；第 1 行摆在第 3 列；第 2 行摆在第0列；第 3 行摆在第 2 列
    // i -> 表示当前来到第几行
    // n -> 表示 n 皇后问题
    // 到第 i 行了
    // 从 0 到 n 每一列都去尝试能不能放

    // 验证能不能摆放
    // 不能摆放在同一列 或者 对角线
    // 当前想在 i 行，j 列摆放
    // k 从 0 到 i for循环
    // 如果 path[k]==j || Math.abs(k-i) == Math.abs(path[k]-j) 那么就摆不了
    // 位于同一列：path[k]==j
    // 位于同一对角线：Math.abs(k-i) == Math.abs(path[k]-j) -> 斜率一样

}