package algorithm_journey.class049;

/**
 * @author: Agony
 * @create: 2024/7/22 18:07
 * @describe:
 * @link:
 */
public class Code02_LongestSubstringWithoutRepeatingCharacters {

    // todo

    // 无重复字符的最长子串

    // 思路：
    // 大体思路和上题一致
    // 左边界更新注意事项：
    // 每次遇到一个字母时！！！
    // 左边界 = max(左边界，上次遇到该字母的位置+1)
    // 如：s = "abdcdadfe"
    // 左边界 l=0
    // 右边界 r=4，遇到d了
    // 此时左边界直接来到 i=3
    // 这时右边界遇到 a 了
    // 但是之前 a 的位置+1 小于 现在左边界的位置，所以左边界不更新
    // 怎么实现？
    // -> int[] last = new int[256] ： 初始值为 -1 表示上次出现的位置为-1
    // 字符串可以装成 char 数组 每一个字符可以用 0～255 中的数字表示
    // last[cha] 就表示 cha 字符 上次出现的位置
    // 如：last['a'] = 3，表示字符 'a' 上次出现的位置为 3
    // 记得更新最新位置
    // todo 感觉用 map 记录位置也行，可以试一试
}
