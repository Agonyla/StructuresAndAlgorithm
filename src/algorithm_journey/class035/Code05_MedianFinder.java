package algorithm_journey.class035;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数据流的中位数
 *
 * @author: Agony
 * @create: 2024/6/18 14:23
 * @describe: 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * <p>
 * MedianFinder() 初始化 MedianFinder 对象。
 * <p>
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * <p>
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 * @link: <a href="https://leetcode.cn/problems/find-median-from-data-stream/description/">数据流的中位数</a>
 */
public class Code05_MedianFinder {


    public static void main(String[] args) {


        Integer[] arr = new Integer[]{4, 1, 6, 3, 2};
        Arrays.sort(arr, (a, b) -> b - a);
        System.out.println(Arrays.toString(arr));

        System.out.println(Math.abs(-1));

        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());

    }


    //
    // 快速获得数据流的中位数的结构
    //
    // 准备一个大根堆 maxHeap 和一个小根堆 minHeap
    // 当堆为空时 or 加入元素 <= 大根堆堆顶元素时，加入大根堆
    // 加入元素 > 大根堆堆顶元素时加入小根队
    // 如果两个堆的元素差值 >= 2 时，需要把较多的弹出加入另一个堆
    // 获取中位数时
    // 如果两个堆大小一致，返回两个堆顶的平均数
    // 大小不一致，返回较多堆的堆顶元素


    static class MedianFinder {


        public PriorityQueue<Integer> maxHeap;
        public PriorityQueue<Integer> minHeap;


        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>((a, b) -> a - b);
        }


        /**
         * @param num
         */
        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            balance();
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }


        public void balance() {
            if (Math.abs(maxHeap.size() - minHeap.size()) >= 2) {
                if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(maxHeap.poll());
                } else {
                    maxHeap.add(minHeap.poll());
                }
            }
        }
    }
}
