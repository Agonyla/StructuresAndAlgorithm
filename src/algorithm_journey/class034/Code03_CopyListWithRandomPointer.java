package algorithm_journey.class034;

/**
 * @author: Agony
 * @create: 2024/6/13 10:29
 * @describe:
 * @link:
 */
public class Code03_CopyListWithRandomPointer {

    // todo

    // 复制带随机指针的链表
    // 容器方法
    // 准备一个 hashmap ，key 装原链表节点，value 装新的节点
    // 遍历该 hashmap，在value中的节点的next指针，指向key中的节点的next指针所对应的value节点
    // 如
    // key: 1, value: 1'
    // key: 2, value: 2'
    // key: 3, value: 3'
    // key: 4, value: 4'
    // 遍历到 1，1的next指针指向2，2所对应的value是2'，所以 1'的next指针就指向2'
    // 即 通过 1 的next 找到 2，再通过 key 是 2 找到 value 2'
    // random指针同理

}
