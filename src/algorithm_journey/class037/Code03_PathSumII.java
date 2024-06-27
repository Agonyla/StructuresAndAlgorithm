package algorithm_journey.class037;

import algorithm_journey.utils.MathUtils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和 II
 *
 * @author: Agony
 * @create: 2024/6/25 10:58
 * @describe: 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * @link: <a href="https://leetcode.cn/problems/path-sum-ii/description/">路径总和 II</a>
 */
public class Code03_PathSumII {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);

        head.left = new TreeNode(4);
        head.left.left = new TreeNode(11);
        head.left.left.left = new TreeNode(7);
        head.left.left.right = new TreeNode(2);

        head.right = new TreeNode(8);
        head.right.left = new TreeNode(13);
        head.right.right = new TreeNode(4);
        head.right.right.left = new TreeNode(5);
        head.right.right.right = new TreeNode(1);

        List<List<Integer>> lists = pathSum(head, 22);

        lists.forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });

        System.out.println("============================================");

        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = null;

        lists = pathSum(head, 1);
        lists.forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });

    }
    

    // 收集累加和等于aim的所有路径

    // 思路
    // 递归调用
    // 设计 f(TreeNode head, int sum)  head -> 当前节点，sum -> 之前的累加和
    // 准备全局遍历，target -> 目标和 ，List<List<Integer>> ans ，List<Integer> path 记录节点路径
    // 当函数返回的时候，需要擦除路径中该节点 --->  恢复现场（回溯）
    // 需要注意的是，每次往 ans 中添加 path 的时候，需要 克隆一份 path 再添加
    // 不然所有的 path 都是指向同一份内存


    /**
     * 收集累加和等于aim的所有路径
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        ans.clear();
        path.clear();
        f(root, 0);
        return ans;
    }

    // 目标和
    public static int target;

    // 所有路径
    public static List<List<Integer>> ans = new ArrayList<>();

    // 路径
    public static List<Integer> path = new ArrayList<>();


    /**
     * @param head 当前节点
     * @param sum  之前的累加和
     */
    public static void f(TreeNode head, int sum) {
        if (head == null) {
            return;
        }
        // 只有在当前节点是叶节点的时候才执行把 path 添加到 ans 的操作
        if (head.left == null && head.right == null) {
            if (head.val + sum == target) {
                path.add(head.val);
                addPathToAns(path);
                path.remove(path.size() - 1);
            }
        } else { // 不是叶节点就把 head 添加到 path 然后找下面的节点
            path.add(head.val);
            f(head.left, sum + head.val);
            f(head.right, sum + head.val);
            path.remove(path.size() - 1);
        }

    }

    /**
     * 把 path 添加到 ans
     *
     * @param path
     */
    public static void addPathToAns(List<Integer> path) {
        List<Integer> list = new ArrayList<>(path);
        ans.add(list);
    }
}

















