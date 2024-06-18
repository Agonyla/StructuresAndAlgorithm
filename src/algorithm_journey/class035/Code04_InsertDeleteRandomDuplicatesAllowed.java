package algorithm_journey.class035;

/**
 * @author: Agony
 * @create: 2024/6/18 14:07
 * @describe:
 * @link:
 */
public class Code04_InsertDeleteRandomDuplicatesAllowed {


    // todo
    //
    // 插入、删除和获取随机元素O(1)时间且允许有重复数字的结构
    // 准备 hashmap 和 arrList
    // key 存放元素，value 是一个hashset存放该元素的所有下标
    // add、random操作类似之前的
    // remove操作
    // eg.
    // key: a, value: (0,2)
    // key: b, value: (1,3,4)
    // remove(a), 返回set里第一个下标 0，把arrList中找到 0 和最后一个下标 4
    // 把arrList中 0 的位置替换为下标 4 的元素，size-1
    // 再把map中该元素 value集合中的 4 改为 0
    // ！！！ 如果被移除的元素刚刚在arrList的最后一个元素，就直接移除就行，无需这么繁琐
    // 如果被删除元素删除之后set集合为空的话，需要在map中移除该元素


}
