package algorithm_journey.class037;

/**
 * @author: Agony
 * @create: 2024/6/27 10:10
 * @describe:
 * @link:
 */
public class Code06_TrimBinarySearchTree {


    // todo

    // 修剪搜索二叉树
    //
    // 思路
    // 已知搜索二叉树 -> 左树 < 当前值 < 右树
    // 当前值在这个范围内
    // 如果当前值的左树 < 范围，那么左树的左树必然小于这个范围，返回右树返回的结果，head.left 去连接这个结果
    // 如果当前值的右树 > 范围，那么右树的右树必然大于这个范围，返回左树返回的结果，head.right去连接这个结果

}
