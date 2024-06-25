package algorithm_journey.class036;

import algorithm_journey.utils.MathUtils;
import algorithm_journey.utils.MathUtils.TreeNode;

import java.util.HashMap;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author: Agony
 * @create: 2024/6/20 14:19
 * @describe: 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * @link: <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/">从前序与中序遍历序列构造二叉树</a>
 */
public class Code07_PreorderInorderBuildBinaryTree {


    public static void main(String[] args) {
        // TreeNode head = new TreeNode(3);
        // head.left = new TreeNode(9);
        // head.right = new TreeNode(20);
        // head.right.left = new TreeNode(15);
        // head.right.right = new TreeNode(7);

        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        TreeNode head = buildTree(preOrder, inOrder);
        MathUtils.preTreeNode(head);
        System.out.println();

        preOrder = new int[]{1, 2, 3};
        inOrder = new int[]{3, 2, 1};
        head = buildTree(preOrder, inOrder);
        MathUtils.preTreeNode(head);
        System.out.println();


    }
    

    // 利用先序与中序遍历序列构造二叉树
    //
    // 递归
    // int[] pre: 先序数组， int[] in: 中序数组  总长度为n
    // []
    // f(pre, 0, n-1, in, 0, n-1) -> 返回树的头节点
    // 显然 0 位置是头节点所处位置
    // 找到 该位置 在 in 数组中的下标，假设为 k
    // 那么 k 左边的就是 头节点的左树，右边的就是右树
    // 调用递归
    // f(pre, 1, k, in, 0,k-1) -> 返回左树头节点，同理
    // f(pre, k+1, n-1, in, k+1, n-1) -> 返回右树头节点
    // 反复递归
    // -> 优化
    // 可以先用 map 记录 in 数组每个元素的下标，就不用每次找头节点取返回遍历


    /**
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        map.clear();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }


    public static HashMap<Integer, Integer> map = new HashMap<>();

    /**
     * @param pre 先序数组
     * @param l1  先序数组左下标
     * @param r1  先序数组右下标
     * @param in  中序数组
     * @param l2  中序数组左下标
     * @param r2  中序数组右下标
     * @return 头节点
     */
    public static TreeNode f(int[] pre, int l1, int r1, int[] in, int l2, int r2) {

        if (l1 > r1 || l2 > r2 || r1 >= pre.length || r2 >= in.length) {
            return null;
        }

        TreeNode head = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return head;
        }
        Integer headIndex = map.get(pre[l1]);
        // l2 < headIndex < r2
        // 这里 r1 不能直接用 index 传入，应该算出偏移量，用 l1 + 偏移量
        int offset = headIndex - l2;
        head.left = f(pre, l1 + 1, l1 + offset, in, l2, headIndex - 1);
        head.right = f(pre, l1 + offset + 1, r1, in, headIndex + 1, r2);
        return head;
    }
}
