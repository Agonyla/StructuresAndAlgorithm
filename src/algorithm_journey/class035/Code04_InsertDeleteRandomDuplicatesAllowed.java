package algorithm_journey.class035;

import java.util.*;

/**
 * O(1) 时间插入、删除和获取随机元素 - 允许重复
 *
 * @author: Agony
 * @create: 2024/6/18 14:07
 * @describe: RandomizedCollection 是一种包含数字集合(可能是重复的)的数据结构。它应该支持插入和删除特定元素，以及删除随机元素。
 * <p>
 * 实现 RandomizedCollection 类:
 * <p>
 * RandomizedCollection()初始化空的 RandomizedCollection 对象。
 * bool insert(int val) 将一个 val 项插入到集合中，即使该项已经存在。如果该项不存在，则返回 true ，否则返回 false 。
 * bool remove(int val) 如果存在，从集合中移除一个 val 项。如果该项存在，则返回 true ，否则返回 false 。注意，如果 val 在集合中出现多次，我们只删除其中一个。
 * int getRandom() 从当前的多个元素集合中返回一个随机元素。每个元素被返回的概率与集合中包含的相同值的数量 线性相关 。
 * 您必须实现类的函数，使每个函数的 平均 时间复杂度为 O(1) 。
 * @link: <a href="https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/description/">O(1) 时间插入、删除和获取随机元素 - 允许重复</a>
 */
public class Code04_InsertDeleteRandomDuplicatesAllowed {


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


    static class RandomizedCollection {

        public Map<Integer, HashSet<Integer>> map;

        public List<Integer> arr;


        public RandomizedCollection() {

            map = new HashMap<>();
            arr = new ArrayList<>();
        }

        /**
         * @param val
         * @return
         */
        public boolean insert(int val) {

            HashSet<Integer> set = map.getOrDefault(val, new HashSet<Integer>());
            set.add(arr.size());
            arr.add(val);
            map.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            HashSet<Integer> set = map.get(val);
            int index = set.iterator().next();
            if (index == arr.size() - 1) {
                arr.remove(arr.size() - 1);
                set.remove(index);
            } else {
                int endValue = arr.get(arr.size() - 1);
                arr.set(index, endValue);
                set.remove(index);
                HashSet<Integer> endValSet = map.get(endValue);
                endValSet.remove(arr.size() - 1);
                endValSet.add(index);
                // 移除操作要左后操作
                arr.remove(arr.size() - 1);
            }
            if (set.isEmpty()) {
                map.remove(val);
            }
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
