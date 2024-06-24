package algorithm_journey.class036;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * @author: Agony
 * @create: 2024/6/20 11:09
 * @describe:
 * @link:
 */
public class Code06_LevelOrderSerializeAndDeserialize {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.right.left = new TreeNode(4);
        head.right.right = new TreeNode(5);

        bfs(head);

        Codec codec = new Codec();
        String str = codec.serialize(head);
        System.out.println(str);

        TreeNode newHead = codec.deserialize(str);
        bfs(newHead);

    }


    // 二叉树按层序列化和反序列化


    public static class Codec {


        public static int MAXN = 10001;

        public static TreeNode[] queue = new TreeNode[MAXN];

        public static int l, r;

        /**
         * 按层遍历
         * 遇到 null 就用 '#' 代替
         *
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {

            StringBuilder sb = new StringBuilder();
            if (root == null) {
                return sb.toString();
            }
            sb.append(root.val).append(",");
            l = r = 0;
            queue[r++] = root;
            while (l < r) {
                TreeNode cur = queue[l++];
                if (cur.left != null) {
                    sb.append(cur.left.val).append(",");
                    queue[r++] = cur.left;
                } else {
                    sb.append("#,");
                }
                if (cur.right != null) {
                    sb.append(cur.right.val).append(",");
                    queue[r++] = cur.right;
                } else {
                    sb.append("#,");
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.

        /**
         * 按层反序列化
         *
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {

            if (data == null || data.isEmpty()) {
                return null;
            }

            String[] nodes = data.split(",");
            int index = 0;
            l = r = 0;
            TreeNode head = generateNode(nodes[index++]);
            queue[r++] = head;
            while (l < r) {
                TreeNode cur = queue[l++];
                cur.left = generateNode(nodes[index++]);
                cur.right = generateNode(nodes[index++]);
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
            }
            return head;
        }

        public static TreeNode generateNode(String val) {
            return "#".equals(val) ? null : new TreeNode(Integer.parseInt(val));
        }
    }


    public static int MAXN = 10001;

    public static TreeNode[] queue = new TreeNode[MAXN];

    public static int l, r;

    /**
     * 按层遍历二叉树
     *
     * @param head
     */
    public static void bfs(TreeNode head) {

        l = r = 0;
        queue[r++] = head;
        while (l < r) {
            int size = r - l;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue[l++];
                System.out.print(cur.val + " ");
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
            }
        }
        System.out.println();
    }
}
