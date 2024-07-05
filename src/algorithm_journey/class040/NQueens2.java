package algorithm_journey.class040;

/**
 * @author: Agony
 * @create: 2024/7/5 10:58
 * @describe:
 * @link:
 */
public class NQueens2 {

    // todo

    // N皇后问题
    // 位运算解决

    // 思路
    // 设计函数 int f(int limit, int col, int left, int right)
    // return -> 一共有几种摆法
    // limit -> 表示几皇后问题 limit = 00111111, 表示这是6皇后问题（有几个1就表示几皇后）
    // col   ->  哪些列已经放过了，如我在第0行的第2列放了1，那么在下一行中 col = 0000100，在下一行中，第1列就不能放置了
    // left -> 表示左下角不能放了，如我在第0行的第2列放了1，那么在下一行中 left = 0000010，在下一行中，第1列就不能放置了
    // right -> 表示右下角不能放了，如我在第0行的第2列放了1，那么在下一行中 right = 0001000，在下一行中，第3列就不能放置了
    // 那么在第1行 ban = 0001110，在有1的位置都不能放
    // 然后需要反转一下
    // candidate = limit & (~ban) = 0110001
    // 只有在 1 位置 上可以放
    // 然后通过 while循环直到candidate都为0
    // 取出 candidate最右侧的1依次尝试
    // place=candidate&(-candidate)
    // 把最右侧的1置0
    // candidate ^= place
    // 到下一层去尝试
    // 直到 col == limit 表示所有的列的放满了，说明这是一种可行的方式
}
