package algorithm_journey.utils;

import java.util.Arrays;

/**
 * 工具类
 *
 * @author Agony
 * @create 2024/5/22 10:58
 */
public class MathUtils {


    /**
     * 交换数组两个位置的数
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArr(int[] arr) {
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
        System.out.println();
    }

    /**
     * 产生一个长度为 n 最大值为 v 的随机数组
     *
     * @param n 数组长度
     * @param v 数组最大值
     * @return 随机数组
     */
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    /**
     * 拷贝数组
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        int length = arr.length;
        int[] ans = new int[length];
        System.arraycopy(arr, 0, ans, 0, length);
        return ans;
    }


    /**
     * 验证两个数组是否相同
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean sameArray(int[] arr1, int[] arr2) {
        int length = arr1.length;
        for (int i = 0; i < length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 链表节点
     */
    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }


    /**
     * 打印链表
     *
     * @param head
     */
    public static void printList(ListNode head) {
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + " -> ");
            } else {
                System.out.println(head.val);
            }
            head = head.next;
        }
    }


    /**
     * 二叉树节点
     */
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 按层遍历二叉树
     *
     * @param head
     */
    public static void bfs(TreeNode head) {

        TreeNode[] queue = new TreeNode[10001];
        int l = 0, r = 0;
        queue[r++] = head;
        while (l < r) {
            int size = r - l;
            // for循环表示每一层的节点
            // 也可以把这个for循环去掉，但是加上感觉更清晰一点
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
