package algorithm_journey.class037;


import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 验证二叉搜索树
 *
 * @author: Agony
 * @create: 2024/6/25 14:14
 * @describe: 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树
 * 只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * @link: <a href="https://leetcode.cn/problems/validate-binary-search-tree/description/">验证二叉搜索树</a>
 */
public class Code05_ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(1);
        head.right = new TreeNode(3);
        System.out.println(isValidBST(head));

        head = new TreeNode(5);
        head.left = new TreeNode(1);
        head.right = new TreeNode(4);
        head.right.left = new TreeNode(3);
        head.right.right = new TreeNode(6);
        System.out.println(isValidBST(head));

    }


    // 验证搜索二叉树 -> 左树最大值 < 头 < 右树最小值
    //
    // 思路
    // 中序遍历一直是升序的话，就是搜索二叉树
    // 当递归来到 null 时，下面的操作是为了不让空节点影响判断
    // if (head == null) {
    //      min = Long.MAX_VALUE;
    //      max = Long.MIN_VALUE;
    //      return true;
    // }


    /**
     * 验证搜索二叉树
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return f(root);
    }

    // 为什么要用 long
    // 如果用 int
    // -> 只有一个头节点且头节点是整型最大值
    // 左树返回整型最小值
    // 在最后比较时，head.val < rightMin 就会返回false
    // 所以需要一个更大的值
    public static long min;
    public static long max;


    /**
     * 判断是否是搜索二叉树
     * 用两个变量 min, max 来记录 来记录一棵树的最小值和最大值
     * 只返回一棵树的最大值是不行的
     * 当棵树作为右树时，应该返回最小值给头节点，以判断整棵树是不是搜索二叉树
     *
     * @param head
     * @return
     */
    public static boolean f(TreeNode head) {

        // 为了不影响 下面 min, max 的更新
        // 当头节点的左右树都为空，就是搜索二叉树
        // 左树节点的最大值 -> Long.MIN_VALUE
        // 右树节点的最小值 -> Long.MAX_VALUE
        // 让其天然满足 leftMax < head.val < rightMin
        if (head == null) {
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
            return true;
        }

        boolean leftOK = f(head.left);
        long leftMin = min;
        long leftMax = max;

        boolean rightOK = f(head.right);
        long rightMin = min;
        long rightMax = max;

        // 更新为整棵树的最小值
        min = Math.min(Math.min(leftMin, rightMin), head.val);

        // 更新为整根树的最大值
        max = Math.max(Math.max(leftMax, rightMax), head.val);

        // 如果左树是搜索 右树是搜索 且左树最大值<当前值<右树最小值
        return leftOK && rightOK && leftMax < head.val && head.val < rightMin;
    }


    /**
     * 返回二叉树的值
     * 有问题
     *
     * @param head
     * @return
     */
    @Deprecated
    public static int fa(TreeNode head) {

        // if (!isSearch || head == null) {
        //     return Integer.MIN_VALUE;
        // }
        // int left = f(head.left);
        // int right = f(head.right);
        // if (left > head.val || head.val > right) {
        //     isSearch = false;
        // }
        // return Math.max(Math.max(left, right), head.val);

        //
        // int left = Integer.MIN_VALUE;
        // int right = Integer.MAX_VALUE;
        // if (head.left != null) {
        //     left = f(head.left);
        // }
        // if (head.right != null) {
        //     right = f(head.right);
        // }
        // if (left > head.val || head.val > right) {
        //     isSearch = false;
        // }
        //
        // return left != Integer.MIN_VALUE && right != Integer.MAX_VALUE ? Math.max(Math.max(left, right), head.val) :
        //         right == Integer.MAX_VALUE ? head.val : Math.max(head.val, right);


        return 0;
    }
}












