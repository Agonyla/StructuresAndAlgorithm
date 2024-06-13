package algorithm_journey.class034;

/**
 * @author: Agony
 * @create: 2024/6/13 10:50
 * @describe:
 * @link:
 */
public class Code05_LinkedListCycleII {


    // todo

    // 返回链表的第一个入环节点
    // 容器方法
    // 准备一个 hashset ，遍历链表，过程中先检查该节点在不在链表中，不在则放入该节点，在就直接返回

    // 快慢指针
    // 直接记流程❕❕❕无需证明
    // 快指针 F，慢指针 S，F走两步数，S走一步
    // 若 F.next==null || F.next.next ==null 说明不是环形链表
    // 若 F==S，两个指针相遇了
    // F 回到头节点，每次走一步，S待在原处，每次走一步
    // 之后一定会在入环节点出相遇
}
