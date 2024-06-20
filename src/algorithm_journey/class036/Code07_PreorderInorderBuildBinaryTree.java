package algorithm_journey.class036;

/**
 * @author: Agony
 * @create: 2024/6/20 14:19
 * @describe:
 * @link:
 */
public class Code07_PreorderInorderBuildBinaryTree {


    // todo

    // 利用先序与中序遍历序列构造二叉树
    //
    // 递归
    // int[] pre: 先序数组， int[] in: 中序数组  总长度为n
    // []
    // f(pre, 0, n-1, in, 0, n-1) -> 返回树的头节点
    // 显然 0 位置是头节点所处位置
    // 找到 该位置 在 in 数组中的下标，假设为 k
    // 那么 k 左边的就是 头节点的左树，右边的就是右树
    // 调用递归
    // f(pre, 1, k, in, 0,k-1) -> 返回左树头节点，同理
    // f(pre, k+1, n-1, in, k+1, n-1) -> 返回右树头节点
    // 反复递归
    // -> 优化
    // 可以先用 map 记录 in 数组每个元素的下标，就不用每次找头节点取返回遍历
}
