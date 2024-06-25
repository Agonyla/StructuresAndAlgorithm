package algorithm_journey.class037;

/**
 * @author: Agony
 * @create: 2024/6/24 19:51
 * @describe:
 * @link:
 */
public class Code02_LowestCommonAncestorBinarySearch {


    // todo

    // 搜索二叉树上寻找两个节点的最近公共祖先
    //
    // 搜索二叉树 -> 树上没有重复的值 且 每一个节点左边整体的结果 < 头 < 右边整体的结果
    // 思路
    // 假设两个数 p < q
    // 1. 当前节点 < p -> 去右树找
    // 2. 当前节点 > q -> 去左数找
    // 3. p < 当前节点 < q -> 但前节点就是公共祖先
    // 4. 先遇到谁，谁就是公共祖先
}
