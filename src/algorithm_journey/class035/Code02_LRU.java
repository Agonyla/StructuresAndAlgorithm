package algorithm_journey.class035;

/**
 * @author: Agony
 * @create: 2024/6/14 10:37
 * @describe:
 * @link:
 */
public class Code02_LRU {


    // todo

    // 实现LRU结构
    // 准备 hashmap 和 双向链表
    // hashmap key 存放键，value 存放链表节点
    // 在执行 get 或 put(更新操作)时候，把当前元素链接双向链表的尾节点
    // 执行 put(放新key)时，删除头节点，把新节点链接到双向链表的尾节点

    // notice 讲道理 单链表应该不行
    // 如 要更新节点位于链表的中间位置，单向链表应该很哪找到它的上一个节点
}
