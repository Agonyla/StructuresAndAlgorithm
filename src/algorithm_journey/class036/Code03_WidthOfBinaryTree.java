package algorithm_journey.class036;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 二叉树最大宽度
 *
 * @author: Agony
 * @create: 2024/6/19 10:37
 * @describe: 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * <p>
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * <p>
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
 * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * @link: <a href="https://leetcode.cn/problems/maximum-width-of-binary-tree/description/">二叉树最大宽度</a>
 */
public class Code03_WidthOfBinaryTree {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(3);
        head.right = new TreeNode(2);
        head.left.left = new TreeNode(5);
        head.left.right = new TreeNode(3);
        head.right.right = new TreeNode(9);
        System.out.println(widthOfBinaryTree(head));

    }
    // todo
    //
    // 二叉树的最大特殊宽度
    //
    // 也是宽度遍历
    // 两个队列 nodeQueue 节点队列，idQueue 编号队列
    // 当前节点编号为 i，左孩子节点 2*i，右孩子节点 2*i+1
    // 在放入节点队列时，同时把编号放入编号队列
    // 最后 最右编号 - 最左编号


    public static int MAXN = 3001;

    // 节点队列
    public static TreeNode[] nodeQueue = new TreeNode[MAXN];

    // 编号队列
    public static int[] idQueue = new int[MAXN];

    public static int l, r;

    /**
     * 二叉树最大宽度
     *
     * @param root 头节点
     * @return 最大宽度
     */
    public static int widthOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int max = 1;
        l = r = 0;
        nodeQueue[r] = root;
        idQueue[r] = 1;
        r++;
        while (l < r) {
            int size = r - l;
            max = Math.max(max, idQueue[r - 1] - idQueue[l] + 1);
            for (int i = 0; i < size; i++) {
                TreeNode cur = nodeQueue[l];
                int id = idQueue[l];
                l++;
                if (cur.left != null) {
                    nodeQueue[r] = cur.left;
                    idQueue[r] = id * 2;
                    r++;
                }
                if (cur.right != null) {
                    nodeQueue[r] = cur.right;
                    idQueue[r] = id * 2 + 1;
                    r++;
                }
            }
        }
        return max;
    }
}
