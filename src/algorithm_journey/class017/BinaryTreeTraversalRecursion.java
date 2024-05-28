package algorithm_journey.class017;

/**
 * @author Agony
 * @create 2024/5/28 10:43
 * <p>
 * // 递归序的解释
 * // 用递归实现二叉树的三序遍历
 */
public class BinaryTreeTraversalRecursion {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        System.out.println("========先序遍历========");
        preOrder(head);
        System.out.println();
        System.out.println("========中序遍历========");
        inOrder(head);
        System.out.println();
        System.out.println("========后序遍历========");
        afterOrder(head);

    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 递归序
     *
     * @param head
     */
    public static void f(TreeNode head) {
        if (head == null) {
            return;
        }
        // 位置1 第一次到达 此时打印就是先序
        f(head.left);
        // 位置2 第二次到达 此时打印就是中序
        f(head.right);
        // 位置3 第三次到达 此时打印就是后序
    }

    /**
     * 先序遍历
     *
     * @param head
     */
    public static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 中序遍历
     *
     * @param head
     */
    public static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.print(head.val + " ");
        inOrder(head.right);
    }

    /**
     * 后序遍历
     *
     * @param head
     */
    public static void afterOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        afterOrder(head.left);
        afterOrder(head.right);
        System.out.print(head.val + " ");
    }


}






















