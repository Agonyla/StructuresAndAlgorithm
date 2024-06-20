package algorithm_journey.class035;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 最大频率栈
 *
 * @author: Agony
 * @create: 2024/6/18 14:35
 * @describe: 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 * <p>
 * 实现 FreqStack 类:
 * <p>
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素
 * @link: <a href="https://leetcode.cn/problems/maximum-frequency-stack/description/">最大频率栈</a>
 */
public class Code06_MaximumFrequencyStack {


    // 最大频率栈
    // 准备 topTimes 用来统计最大出现的次数
    // hashmap<Integer, Integer>，key：表示加入的元素，value：表示加入的次数
    // hashmap<Integer,ArrayList<Integer>> key：表示第几层，即最大出现的次数，value：表示出现次数的所有元素
    // 加入操作
    // 看出现次数有没有大于 topTimes，有就更新，对应该改变 hashmap 的值
    // 弹出操作
    // 找到 topTimes 层的 list，返回最后的值，并删除该元素，如果删除后该 list为空了，就直接从hashmap中移除，并更新 topTimes 的值


    static class FreqStack {

        public HashMap<Integer, Integer> valueTimes;

        public HashMap<Integer, ArrayList<Integer>> cntsValue;

        public int topTimes;


        public FreqStack() {
            valueTimes = new HashMap<>();
            cntsValue = new HashMap<>();
            topTimes = 0;
        }

        /**
         * @param val
         */
        public void push(int val) {

            Integer times = valueTimes.getOrDefault(val, 0);
            valueTimes.put(val, times + 1);
            Integer curTopTimes = valueTimes.get(val);

            if (!cntsValue.containsKey(curTopTimes)) {
                cntsValue.put(curTopTimes, new ArrayList<>());
            }
            ArrayList<Integer> valueList = cntsValue.get(curTopTimes);
            valueList.add(val);
            topTimes = Math.max(topTimes, curTopTimes);
        }


        /**
         * @return
         */
        public int pop() {
            ArrayList<Integer> valueList = cntsValue.get(topTimes);
            Integer ans = valueList.remove(valueList.size() - 1);
            if (valueList.isEmpty()) {
                cntsValue.remove(topTimes--);
            }
            Integer times = valueTimes.get(ans);
            if (times == 1) {
                valueTimes.remove(ans);
            } else {
                valueTimes.put(ans, times - 1);
            }
            return ans;
        }
    }

}










