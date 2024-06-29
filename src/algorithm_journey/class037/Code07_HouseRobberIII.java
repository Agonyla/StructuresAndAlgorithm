package algorithm_journey.class037;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 打家劫舍 III
 *
 * @author: Agony
 * @create: 2024/6/27 10:20
 * @describe: 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * @link: <a href="https://leetcode.cn/problems/house-robber-iii/description/">打家劫舍 III</a>
 */
public class Code07_HouseRobberIII {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.right = new TreeNode(3);
        head.right.right = new TreeNode(1);

        System.out.println(rob(head));

    }
    

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


    /**
     * 打家劫舍问题
     *
     * @param root
     * @return
     */
    public static int rob(TreeNode root) {
        // g(root);
        // return Math.max(yes, no);
        int[] ans = f(root);
        return Math.max(ans[0], ans[1]);
    }


    // 表示在偷当前节点的最大收益
    public static int yes;

    // 表示不偷当前节点的最大手艺
    public static int no;

    /**
     * @param head
     */
    public static void g(TreeNode head) {
        if (head == null) {
            yes = 0;
            no = 0;
            return;
        }
        int curYes = head.val;
        int curNo = 0;

        // 去左边递归
        g(head.left);
        // 这时 yes 就变成偷左树的头节点的最大收益
        // no 就变成不偷左树的头结点的最大手艺
        curYes += no;
        curNo += Math.max(yes, no);

        g(head.right);
        curYes += no;
        curNo += Math.max(yes, no);

        yes = curYes;
        no = curNo;
    }


    /**
     * int[] ans = new int[2];
     * ans[0] -> 表示偷当前节点的最大收益
     * ans[1] -> 表示不偷当前节点的最大收益
     *
     * @param head
     * @return
     */
    public static int[] f(TreeNode head) {
        int[] ans = new int[2];
        if (head == null) {
            return ans;
        }

        // 左树的最大收益
        int[] left = f(head.left);
        // 右树的最大收益
        int[] right = f(head.right);
        ans[0] = head.val + left[1] + right[1];
        ans[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return ans;
    }

}












