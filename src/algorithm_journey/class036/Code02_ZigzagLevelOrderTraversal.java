package algorithm_journey.class036;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的锯齿形层序遍历
 *
 * @author: Agony
 * @create: 2024/6/19 10:24
 * @describe: 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * @link: <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/">二叉树的锯齿形层序遍历</a>
 */
public class Code02_ZigzagLevelOrderTraversal {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head.right.left = new TreeNode(15);
        head.right.right = new TreeNode(7);
        System.out.println("Test Begin");
        List<List<Integer>> lists = zigzagLevelOrder(head);

        System.out.println(lists);

        lists.forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });

        //     [1,2,3,4,null,null,5]
        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.right = new TreeNode(5);
        lists = zigzagLevelOrder(head);
        lists.forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });

    }


    // 二叉树的锯齿形层序遍历
    //
    // 和上题类似
    // 在弹出前先收集数组中的元素
    // 从左往右收集 -> 收集 l -> r-1
    // 从右往左收集 -> 收集 r-1 -> l
    // 用一个变量 reverse 记录收集的顺序

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static int MAXN = 2001;

    public static TreeNode[] queue = new TreeNode[MAXN];

    public static int l, r = 0;

    public static boolean reverse = false;


    /**
     * 二叉树的锯齿形层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        // 初始化
        l = r = 0;
        reverse = false;
        queue[r++] = root;
        while (l < r) {
            int size = r - l;
            ArrayList<Integer> list = new ArrayList<>();

            // 先收集
            // 没有反转从左往右收集
            // if (!reverse) {
            //     for (int i = l; i < r; i++) {
            //         TreeNode cur = queue[i];
            //         list.add(cur.val);
            //     }
            //
            // } else {
            //     for (int i = r - 1; i >= l; i--) {
            //         TreeNode cur = queue[i];
            //         list.add(cur.val);
            //     }
            // }

            // -> 简便写法
            for (int i = 0; i < size; i++) {
                int index = reverse ? r - 1 - i : l + i;
                TreeNode cur = queue[index];
                list.add(cur.val);
            }

            // 再加入
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue[l++];
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
            }
            reverse = !reverse;
            ans.add(list);
        }

        return ans;
    }


}
