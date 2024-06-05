package algorithm_journey.class027;

import java.util.Arrays;

/**
 * @author Agony
 * @create 2024/6/5 11:34
 */
public class MaxCover2 {


    public static void main(String[] args) {
        int[][] intervals = {
                // {0, 30},
                // {5, 10},
                // {15, 20},
                {7, 10},
                {2, 4},
        };
        int ans = minMeetingRooms(intervals);
        System.out.println("ans = " + ans);

    }

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        size = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && heap[0] <= intervals[i][0]) {
                pop();
            }
            add(intervals[i][1]);
            ans = Math.max(size, ans);
        }
        return ans;
    }


    // intervals的最大长度
    public static int MAXN = 10001;
    // 小根堆
    public static int[] heap = new int[MAXN];
    // 堆的大小
    public static int size;

    public static void add(int x) {
        heap[size] = x;
        int i = size++;
        while (heap[i] < heap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void pop() {
        swap(0, --size);
        int i = 0, l = 1;
        while (l < size) {
            int best = l + 1 < size && heap[l + 1] < heap[l] ? l + 1 : l;
            best = heap[best] < heap[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(i, best);
            i = best;
            l = i * 2 + 1;
        }
    }

    public static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

}
