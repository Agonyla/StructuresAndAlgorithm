package algorithm_journey.class024;

/**
 * 数组中的第K个最大元素
 *
 * @author Agony
 * @create 2024/5/31 16:18
 * @describe: 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * @link: <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/description/">数组中的第K个最大元素</a>
 */
public class RandomizedSelect {

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int ans = findKthLargest(arr, 2);
        System.out.println("ans = " + ans);
        arr = new int[]{3, 2, 3, 1, 8, 4, 5, 5, 6};
        ans = findKthLargest(arr, 4);
        System.out.println("ans = " + ans);
    }


    /**
     * 用快排完成
     *
     * @param arr
     * @param k
     * @return
     */
    public static int findKthLargest(int[] arr, int k) {
        return randomizedSelect(arr, arr.length - k);
    }

    /**
     * 随机选择排序
     * partition 数组之后
     * [first, last] 有没有命中 i
     * 没有就选择 i 在的那侧再次 partition
     * 直到[first, last] 命中 i
     *
     * @param arr 数组
     * @param i   第 i 个位置
     * @return 第 i 位置的数
     */
    public static int randomizedSelect(int[] arr, int i) {
        int ans = 0;

        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int x = arr[l + (int) (Math.random() * (r + 1 - l))];
            partition(arr, l, r, x);
            if (i < first) {
                r = first - 1;
            } else if (last < i) {
                l = last + 1;
            } else {
                ans = arr[i];
                break;
            }
        }
        return ans;
    }

    public static int first;
    public static int last;

    public static void partition(int[] arr, int l, int r, int x) {
        int i = l;
        while (i <= r) {
            if (arr[i] < x) {
                swap(arr, l++, i++);
            } else if (arr[i] > x) {
                swap(arr, r--, i);
            } else {
                i++;
            }
        }
        first = l;
        last = r;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}












