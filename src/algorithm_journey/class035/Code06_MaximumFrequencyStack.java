package algorithm_journey.class035;

/**
 * @author: Agony
 * @create: 2024/6/18 14:35
 * @describe:
 * @link:
 */
public class Code06_MaximumFrequencyStack {

    // todo

    // 最大频率栈
    // 准备 topTimes 用来统计最大出现的次数
    // hashmap<Integer, Integer>，key：表示加入的元素，value：表示加入的次数
    // hashmap<Integer,ArrayList<Integer>> key：表示第几层，即最大出现的次数，value：表示出现次数的所有元素
    // 加入操作
    // 看出现次数有没有大于 topTimes，有就更新，对应该改变 hashmap 的值
    // 弹出操作
    // 找到 topTimes 层的 list，返回最后的值，并删除该元素，如果删除后该 list为空了，就直接从hashmap中移除，并更新 topTimes 的值
}
