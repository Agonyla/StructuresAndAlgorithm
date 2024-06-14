package algorithm_journey.class035;

/**
 * @author: Agony
 * @create: 2024/6/14 11:27
 * @describe:
 * @link:
 */
public class Code03_InsertDeleteRandom {


    // todo

    // 插入、删除和获取随机元素O(1)时间的结构
    // 准备 hashmap 和 arrList
    // key 存放元素，value 存放下标
    // add 操作 ----
    // 如果 hashmap中已经含有 该元素，直接return
    // 否则同时向 hashmap 和 arr 中添加
    // random 操作 --------
    // 在下标范围内随机出一个数返回
    // remove(value) 操作  --------- // 如果只是普通删除，那么在操作了之后 在下标范围内会有洞，再随机的话效率会很低
    // 先在 hashmap 中找到该元素的下标 valueIndex
    // 在 arr 中 删除该元素，找到最后一个元素 endValue，并把最后一个元素的下标改成 valueIndex
    // 因为 arr 中的元素是按顺序排序的，直接这样操作可能不行
    // 即 先把 valueIndex 的位置的值替换为 endValue 然后再移除最后一个操作
    // -> arr.set(valueIndex, endValue)
    // -> arr.remove(arr.size() - 1)
    // 再在 hashmap 中 删除该元素，然后把 endValue 的 value 值改为 valueIndex
    // -> hashmap.put(endValue, valueIndex)
    // -> hashmap.remove(value)

}
