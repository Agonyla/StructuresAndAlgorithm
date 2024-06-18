package algorithm_journey.class035;

/**
 * @author: Agony
 * @create: 2024/6/18 14:23
 * @describe:
 * @link:
 */
public class Code05_MedianFinder {

    // todo
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
}
