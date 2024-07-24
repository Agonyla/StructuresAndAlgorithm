package algorithm_journey.class049;

/**
 * @author: Agony
 * @create: 2024/7/24 19:05
 * @describe:
 * @link:
 */
public class Code07_LongestSubstringWithAtLeastKRepeating {

    // todo

    // 至少有K个重复字符的最长子串

    // 思路：
    // 转换 -> 因为每一种字符都是小写字符
    // 收集字符种数在1~26种字符遍历
    // 求不同字符种数下满足条件的最大值，然后比较求最大值
    //
    // 变量， require -> 要求的字符种类
    // satisfy -> 满足出现次数 > k次的字符种类
    // cnts -> 词频表 数组实现
    // 做窗口从0开始，右窗口向右滑动
    // 把右窗口的值加入词频表，更新
    // require更新 (只在该词第一次出现时更新)
    // satisfy更新 (只在词频数==k次时更新)
    // 如果 require > satisfy时，左窗口向右移动  (代码中使用 while 实现的)
    // cnts更新，每次都要
    // require更新，(该词出现次数==0时)
    // satisfy更新，(出现此数<k)
    // 只有当 require==satisfy时
    // 更新答案
}
