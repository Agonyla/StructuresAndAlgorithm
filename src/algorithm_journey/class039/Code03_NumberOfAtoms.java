package algorithm_journey.class039;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 原子的数量
 *
 * @author: Agony
 * @create: 2024/7/3 09:46
 * @describe: 给你一个字符串化学式 formula ，返回 每种原子的数量 。
 * <p>
 * 原子总是以一个大写字母开始，接着跟随 0 个或任意个小写字母，表示原子的名字。
 * <p>
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。
 * <p>
 * 例如，"H2O" 和 "H2O2" 是可行的，但 "H1O2" 这个表达是不可行的。
 * 两个化学式连在一起可以构成新的化学式。
 * <p>
 * 例如 "H2O2He3Mg4" 也是化学式。
 * 由括号括起的化学式并佐以数字（可选择性添加）也是化学式。
 * <p>
 * 例如 "(H2O2)" 和 "(H2O2)3" 是化学式。
 * 返回所有原子的数量，格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * <p>
 * 输入：formula = "Mg(OH)2"
 * 输出："H2MgO2"
 * 解释：原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * @link: <a href="https://leetcode.cn/problems/number-of-atoms/">原子的数量</a>
 */
public class Code03_NumberOfAtoms {


    public static void main(String[] args) {


        // HashMap 并不保证元素的顺序，所以遍历 HashMap 时元素的输出顺序可能是随机的
        HashMap<String, Integer> map = new HashMap<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        map.put("H", 3);
        map.put("A", 3);
        map.put("B", 3);
        map.put("G", 2);
        map.put("Mn", 2);
        treeMap.put("H", 3);
        treeMap.put("A", 3);
        treeMap.put("B", 3);
        treeMap.put("G", 2);
        treeMap.put("Mn", 2);

        System.out.println("hashmap:");
        for (Map.Entry<String, Integer> set : map.entrySet()) {
            System.out.println(set.getKey() + ": " + set.getValue());
        }
        System.out.println("treemap");
        for (Map.Entry<String, Integer> set : treeMap.entrySet()) {
            System.out.println(set.getKey() + ": " + set.getValue());
        }

        String s1 = "H2O";                // H2O
        String s2 = "Mg(OH)2";            // H2MgO2
        String s3 = "K4(ON(SO3)2)2";      // K4N2O14S4

        System.out.println(countOfAtoms(s1));
        System.out.println(countOfAtoms(s2));
        System.out.println(countOfAtoms(s3));


    }

    // 含有嵌套的分子式求原子数量

    // 思路
    // 实现函数 TreeMap<String, Integer> f(char[] s, int index), 全局变量 int where ->
    // s -> 提供的字符串，
    // index -> 字符串的下标位置
    // 函数内局部变量
    // StringBuilder element -> 用于收集元素
    // TreeMap<String, Integer> ans -> key: 出现的元素，value：出现的次数。作为答案返回
    // TreeMap<String, Integer> pre -> 用来记录下一层收集的答案
    // int count -> 统计元素出现的个数

    // 当遇到大写字母 或者 '(' 时，执行清算
    // 1. 把收集到的元素 element 和 次数 count 放入 ans 中，然后清零
    // 2. 或者把 调用 f() 函数的收集到的 pre * 次数 count 放入 ans 中，然后 把 pre 清空，count 清零
    // 1 和 2 只会执行一个


    /**
     * 含有嵌套的分子式求原子数量
     * 输入：formula = "Mg(OH)2"
     * 输出："H2MgO2"
     *
     * @param formula 方程表达式
     * @return 按照字典序输出字符串
     */
    public static String countOfAtoms(String formula) {

        char[] s = formula.toCharArray();
        where = 0;
        TreeMap<String, Integer> map = f(s, 0);
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> set : map.entrySet()) {
            sb.append(set.getKey()).append(set.getValue() == 1 ? "" : set.getValue());
        }
        return sb.toString();
    }


    public static int where;


    /**
     * 把字符串的组成记录到map中
     *
     * @param s 转化后的字符数组
     * @param i 数组下标
     * @return 记录好组成的map
     */
    public static TreeMap<String, Integer> f(char[] s, int i) {


        // 记录该层的组成
        TreeMap<String, Integer> ans = new TreeMap<>();

        // 接受下层的组成
        TreeMap<String, Integer> next = null;

        // 记录字母
        StringBuilder element = new StringBuilder();

        // 记录数字
        int count = 0;

        while (i < s.length && s[i] != ')') {

            if (s[i] >= 'A' && s[i] <= 'Z' || s[i] == '(') {
                // 遇到大写字母或者 '(' 时执行清算
                addToAns(ans, next, element, count);
                next = null;
                element.setLength(0);
                count = 0;
                if (s[i] >= 'A' && s[i] <= 'Z') {
                    // 遇到大写字母
                    element.append(s[i++]);
                } else {
                    // 遇到 '('
                    next = f(s, i + 1);
                    i = where + 1;
                }
            } else if (s[i] >= 'a' && s[i] <= 'z') {
                // 遇到小写字母 element 添加该字母
                element.append(s[i++]);
            } else {
                // 遇到数字 count 记录
                count = count * 10 + s[i++] - '0';
            }
        }

        // 返回前把已收集到的添加到 ans 中
        addToAns(ans, next, element, count);
        where = i;
        return ans;
    }


    /**
     * 往 ans 中添加
     *
     * @param ans     ans map
     * @param next    下一层next map
     * @param element 收集到的元素
     * @param count   元素出现次数
     */
    public static void addToAns(TreeMap<String, Integer> ans, TreeMap<String, Integer> next, StringBuilder element, int count) {

        // next 和 count 往 ans 中添加
        // element 和 count 往 ans 中添加
        // 只有一种会发生
        if (next != null || !element.isEmpty()) {
            count = count == 0 ? 1 : count;
            // 下一层收集的往 ans 中添加
            if (next != null) {
                for (String key : next.keySet()) {
                    ans.put(key, ans.getOrDefault(key, 0) + next.get(key) * count);
                }
            } else {
                String key = element.toString();
                ans.put(key, ans.getOrDefault(key, 0) + count);
            }
        }
    }

}




















