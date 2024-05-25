package algorithm_basic.leetcode;

/**
 * @Author Agony
 * @Create 2023/8/30 21:22
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/">二叉树的最近公共祖先</a>
 */
public class LowestCommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return process(root, p, q).target;
    }

    public static class Info {
        boolean findA;
        boolean findB;
        TreeNode target;

        public Info(boolean findA, boolean findB, TreeNode target) {
            this.findA = findA;
            this.findB = findB;
            this.target = target;
        }
    }

    public static Info process(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(root.left, a, b);
        Info rightInfo = process(root.right, a, b);
        boolean findA = root == a || leftInfo.findA || rightInfo.findA;
        boolean findB = root == b || leftInfo.findB || rightInfo.findB;
        TreeNode target = null;
        if (leftInfo.target != null) {
            target = leftInfo.target;
        } else if (rightInfo.target != null) {
            target = rightInfo.target;
        } else {
            if (findA && findB) {
                target = root;
            }
        }
        return new Info(findA, findB, target);
    }
}
