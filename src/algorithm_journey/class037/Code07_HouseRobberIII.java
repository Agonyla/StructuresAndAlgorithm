package algorithm_journey.class037;

/**
 * @author: Agony
 * @create: 2024/6/27 10:20
 * @describe:
 * @link:
 */
public class Code07_HouseRobberIII {


    // todo

    // todo 看一下 递归图！！！

    // 二叉树打家劫舍问题
    // 不能偷相邻节点，当前头节点不偷，可以偷所有子节点，只是相邻的不能一块偷
    // 思路
    // 准备两个全局变量，yes，no
    // yes -> 表示完成了一组子树的遍历，返回之后，yes 变成这组子树在偷当前节点的情况下，最大的收益
    // no -> 表示完成了一组子树的遍历，返回之后，no 变成这组子树在不偷当前节点的情况下，最大的收益
    // 如 来到 a 节点，a.val = 10
    // yes -> 10 + 不偷 a.left + 不偷 a.right
    // no -> max(偷 a.left, 不偷 a.left) + max(偷 a.right, 不偷 a.right)
    // return max(yes, no)
    //
    // -> 可以把 左神的代码 改为 带有返回值的 f 试一下
}
