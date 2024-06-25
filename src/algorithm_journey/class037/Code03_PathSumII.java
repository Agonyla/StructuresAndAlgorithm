package algorithm_journey.class037;

/**
 * @author: Agony
 * @create: 2024/6/25 10:58
 * @describe:
 * @link:
 */
public class Code03_PathSumII {


    // todo

    // 收集累加和等于aim的所有路径

    // 思路
    // 递归调用
    // 设计 f(TreeNode head, int sum)  head -> 当前节点，sum -> 之前的累加和
    // 准备全局遍历，target -> 目标和 ，List<List<Integer>> ans ，List<Integer> path 记录节点路径
    // 当函数返回的时候，需要擦除路径中该节点 --->  恢复现场（回溯）
    // 需要注意的是，每次往 ans 中添加 path 的时候，需要 克隆一份 path 再添加
    // 不然所有的 path 都是指向同一份内存
}
