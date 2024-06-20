package algorithm_journey.class035;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * O(1) 时间插入、删除和获取随机元素
 *
 * @author: Agony
 * @create: 2024/6/14 11:27
 * @describe: 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * @link: <a href="https://leetcode.cn/problems/insert-delete-getrandom-o1/description/">O(1) 时间插入、删除和获取随机元素</a>
 */
public class Code03_InsertDeleteRandom {


    // 插入、删除和获取随机元素O(1)时间的结构
    // 准备 hashmap 和 arrList
    // key 存放元素，value 存放下标
    // 1. add 操作 ----
    //
    // 如果 hashmap中已经含有 该元素，直接return
    // 否则同时向 hashmap 和 arr 中添加
    // 2. random 操作 --------
    //
    // 在下标范围内随机出一个数返回
    // 3. remove(value) 操作  ---------
    // 如果只是普通删除，那么在操作了之后 在下标范围内会有洞，再随机的话效率会很低
    // 先在 hashmap 中找到该元素的下标 valueIndex
    // 在 arr 中 删除该元素，找到最后一个元素 endValue，并把最后一个元素的下标改成 valueIndex
    // 因为 arr 中的元素是按顺序排序的，直接这样操作可能不行
    // 即 先把 valueIndex 的位置的值替换为 endValue 然后再移除最后一个操作
    // -> arr.set(valueIndex, endValue)
    // -> arr.remove(arr.size() - 1)
    // 再在 hashmap 中 删除该元素，然后把 endValue 的 value 值改为 valueIndex
    // -> hashmap.put(endValue, valueIndex)
    // -> hashmap.remove(value)


    static class RandomizedSet {

        public HashMap<Integer, Integer> map;
        public List<Integer> arr;

        public RandomizedSet() {
            map = new HashMap<>();
            arr = new ArrayList<>();
        }


        /**
         * @param val
         * @return
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, arr.size());
            arr.add(val);
            return true;
        }

        /**
         * @param val
         * @return
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int index = map.get(val);
            int endValue = arr.get(arr.size() - 1);
            arr.set(index, endValue);
            arr.remove(arr.size() - 1);
            map.put(endValue, index);
            map.remove(val);
            return true;
        }


        /**
         * @return
         */
        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }
}
