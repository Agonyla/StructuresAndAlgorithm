package algorithm_journey.class036;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 二叉树的完全性检验
 *
 * @author: Agony
 * @create: 2024/6/20 14:19
 * @describe: 给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。
 * <p>
 * 在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
 * @link: <a href="https://leetcode.cn/problems/check-completeness-of-a-binary-tree/description/">二叉树的完全性检验</a>
 */
public class Code08_CompletenessOfBinaryTree {


    public static void main(String[] args) {
        //     [1,2,3,5,null,7,8]
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(5);
        head.right.left = new TreeNode(7);
        head.right.right = new TreeNode(8);

        System.out.println(isCompleteTree(head));
    }


    // 验证完全二叉树
    //
    // 思路
    // BFS
    // 1. 有右无左 -> false
    // 2. 若是碰到有孩子不全的节点，那么接下来所有的节点都必须是叶节点(没有孩子的节点)


    /**
     * 是否是完全二叉树
     * 完全二叉树：从上到下，从左到右放置节点
     *
     * @param root
     * @return
     */
    public static boolean isCompleteTree(TreeNode root) {
        return bfs(root);
    }

    public static int MAXN = 101;

    public static TreeNode[] queue = new TreeNode[MAXN];

    public static int l, r;

    public static boolean flag = false;

    /**
     * 按层遍历
     *
     * @param head
     * @return
     */
    public static boolean bfs(TreeNode head) {
        if (head == null) {
            return true;
        }
        l = r = 0;
        // flag = false;
        queue[r++] = head;
        while (l < r) {
            int size = r - l;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue[l++];
                // 无左有右
                if (cur.left == null && cur.right != null) {
                    return false;
                }
                // 出现左右不全，但是不是叶节点
                if (flag && (cur.left != null || cur.right != null)) {
                    return false;
                }
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
                // 出现叶节点
                if (cur.left == null || cur.right == null) {
                    flag = true;
                }

            }
        }
        return true;
    }

}




