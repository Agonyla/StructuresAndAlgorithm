package algorithm_journey.class036;

import java.util.*;

/**
 * 二叉树的层序遍历
 *
 * @author: Agony
 * @create: 2024/6/19 09:17
 * @describe: 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）
 * @link: <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/description/">二叉树的层序遍历</a>
 */
public class Code01_LevelOrderTraversal {


    public static void main(String[] args) {

        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head.right.left = new TreeNode(15);
        head.right.right = new TreeNode(7);

        List<List<Integer>> lists = levelOrderBetter(head);
        lists.forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });

    }


    // 二叉树的层序遍历 BFS (Breadth First Search)
    //
    // 思路
    // 准备一个队列
    // 头节点加入
    // 弹出，有左加左，有右加右
    // 准备一个 hashmap，key：存放节点，value：存放位于第几层
    // 每当队列添加节点时，就在 hashmap 中记录该节点的位置信息
    //
    //
    //
    // 优化
    // 用数组代替队列
    // l=r=0
    // 加入元素，元素来到r的位置，r++
    // 弹出元素，弹出l位置的元素，l--
    // 头节点加入队列       while(队列不为空) 即  l < r
    // 1. 获取队列的大小 size
    // 2. 以下步骤共重复 size 次
    // 弹出节点，有左加左，有右加右
    //
    // 在重复 size 次的过程中，每次弹出的节点，就是该层所有的节点。


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

    /**
     * 二叉树的层序遍历
     * 普通 bfs
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        if (root == null) {
            return ans;
        }
        queue.add(root);
        map.put(root, 0);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer level = map.get(cur);
            // 如果没有新层就新建
            if (ans.size() == level) {
                ans.add(new ArrayList<>());
            }
            ans.get(level).add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, level + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, level + 1);
            }
        }
        return ans;
    }


    /**
     * 二叉树的层序遍历
     * for 循环
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBetter(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);

                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }


    public static int MAX_LENGTH = 2001;

    public static TreeNode[] queue = new TreeNode[MAX_LENGTH];

    // 左下标
    public static int l;

    // 有下标
    public static int r;


    /**
     * 二叉树的层序遍历
     * 用数组代替 linkedList
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBetterByArr(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }
        // 队列清零
        l = r = 0;
        queue[r++] = root;
        while (l < r) {
            int size = r - l;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue[l++];
                list.add(cur.val);
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
            }
            ans.add(list);
        }
        return ans;
    }


    List<List<Integer>> lists = new ArrayList<>();

    /**
     * 最优解 -- 可以学习一下
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBest(TreeNode root) {
        bfs(root, 0);
        return lists;
    }

    public void bfs(TreeNode node, int level) {
        // 节点真的存在，更新层数
        if (node == null) {
            return;
        }
        level++;
        // list的数量要和层数相同
        if (lists.size() < level) {
            // 否则，初始化一个新层的子list，存放新层的结果
            lists.add(new ArrayList<Integer>());
        }
        // lists的index从0开始，所以level层存储在lists的level - 1位置
        lists.get(level - 1).add(node.val);
        // 递归
        bfs(node.left, level);
        bfs(node.right, level);
    }

}
