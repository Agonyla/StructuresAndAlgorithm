package algorithm_journey.class036;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 二叉树的序列化与反序列化
 *
 * @author: Agony
 * @create: 2024/6/20 10:53
 * @describe: 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * @link: <a href="https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/"> 二叉树的序列化与反序列化</a>
 */
public class Code05_PreorderSerializeAndDeserialize {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head.right.left = new TreeNode(15);
        head.right.right = new TreeNode(7);

        f(head);
        System.out.println();

        Codec codec = new Codec();
        String str = codec.serialize(head);
        System.out.println(str);

        TreeNode deserialize = codec.deserialize(str);
        f(deserialize);
        System.out.println();
    }


    /**
     * 先序遍历
     *
     * @param head
     */
    public static void f(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        f(head.left);
        f(head.right);
    }


    // 二叉树先序序列化和反序列化


    public static class Codec {


        /**
         * 先序遍历序列化
         *
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {

            if (root == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            pre(root, sb);
            return sb.toString();
        }


        public static void pre(TreeNode head, StringBuilder sb) {
            if (head == null) {
                sb.append("#,");
            } else {
                sb.append(head.val).append(",");
                pre(head.left, sb);
                pre(head.right, sb);
            }
        }

        /**
         * 先序遍历反序列化
         *
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {

            if (data == null || data.isEmpty()) {
                return null;
            }
            String[] nodes = data.split(",");
            index = 0;
            return preToNode(nodes);
        }

        public static int index;

        public static TreeNode preToNode(String[] nodes) {

            String node = nodes[index++];
            if ("#".equals(node)) {
                return null;
            } else {
                TreeNode head = new TreeNode(Integer.parseInt(node));
                head.left = preToNode(nodes);
                head.right = preToNode(nodes);
                return head;
            }
        }


        // 注意！！！
        // 不能这样写
        // nodes = [3,9,#,#,20,15,#,#,7,#,#,]
        // index = 0
        // 开始递归调用时，第一层 传入 index + 1
        // 在 head.left = preToNode(nodes, index + 1); 调完返回后 index 仍然是 0
        // index 只会在一层调用下一层的时候增加，但是调用完返回之后仍然是之前的值
        // 所有需要用一个静态变量来记录 index 的值
        /*
            public static TreeNode preToNode(String[] nodes, int index) {

            String node = nodes[index];
            if ("#".equals(node)) {
                return null;
            } else {
                TreeNode head = new TreeNode(Integer.parseInt(node));
                head.left = preToNode(nodes, index + 1);
                head.right = preToNode(nodes, index + 1);
                return head;
            }
        }
         */
    }


}








