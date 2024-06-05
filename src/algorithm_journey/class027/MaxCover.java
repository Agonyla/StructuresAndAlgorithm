package algorithm_journey.class027;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 会议室 II
 *
 * @author Agony
 * @create 2024/6/5 10:59
 * @describe: 给你一个会议时间安排的数组 intervals ，
 * 每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
 * @link: <a href="https://leetcode.cn/problems/meeting-rooms-ii/description/">会议室 II</a>
 */
public class MaxCover {

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

    /**
     * 把每个会议时间按照开始时间排序
     * [0,30],[5,10],[15,20]
     * 遍历会议时间，把结束时间加入小根堆
     * 当有会议时间加入堆时，如果开始时间>=堆顶元素时(结束时间),弹出堆顶元素
     * -> 表示之前的会议已经结束了新的会议才开始
     * 堆的size就是最小数量
     *
     * @param intervals 会议时间安排数组
     * @return 所需会议室的最小数量
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            while (!heap.isEmpty() && heap.peek() <= intervals[i][0]) {
                heap.poll();
            }
            heap.add(intervals[i][1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }
}
