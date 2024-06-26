package algorithm_journey.class038;

import java.util.HashSet;

/**
 * 字符串的全部子序列
 *
 * @author: Agony
 * @create: 2024/6/28 11:04
 * @describe: 给定一个字符串s，长度为n，求s的所有子序列
 * 1.子序列: 指一个字符串删掉部分字符（也可以不删）形成的字符串，可以是不连续的，比如"abcde"的子序列可以有"ace","ad"等等
 * 2.将所有的子序列的结果返回为一个字符串数组
 * 3.字符串里面可能有重复字符，但是返回的子序列不能有重复的子序列，比如"aab"的子序列只有"","a","aa","aab","ab","b"，不能存在2个相同的"ab"
 * 4.返回字符串数组里面的顺序可以不唯一
 * @link: <a href="https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a">字符串的全部子序列</a>
 */
public class Code01_Subsequences {


    public static void main(String[] args) {

        String s = "ab";
        String[] strings = generatePermutation(s);
        for (String string : strings) {
            System.out.println(string);
        }


        System.out.println("===========");

        // 默认字符 \u0000 会输出乱码  
        char[] chars = new char[3];
        System.out.println(chars);
        chars[0] = 'a';
        System.out.println(new String(chars));
        System.out.println(new String(chars, 0, 1));

    }


    // 字符串的全部子序列

    // 前言：任何递归都是深度优先遍历！！！

    // 思路
    // 准备 char[] path -> 放置每个路径字符
    // hashset set -> 收集所有字符串
    // 设计 f(char[] arr, int i, int size) 函数
    // arr -> 字符串转成的字符数组
    // i -> 表示当前来到数组的位置
    // size -> 表示 path 路径中存放的字符数量
    // 当我调用 f(arr, i + 1, size+1) -> 表示来到arr的下一个位置，然后把当前字符加入到path中
    // f(arr,i+1,size) -> 表示来到arr的下一个位置，当前字符不加入到path中
    // 因为 path 是数组结构，可以直接覆盖，不需要像 stringBuilder 或 arrayList 做回溯擦除工作
    // 时间复杂度：o(n * 2^n)


    /**
     * 字符串的全部子序列
     *
     * @param s
     * @return
     */
    public static String[] generatePermutation(String s) {
        // write code here
        set = new HashSet<>();
        char[] arr = s.toCharArray();
        path = new char[arr.length];
        f(arr, 0, 0);

        return set.toArray(new String[0]);
    }


    // 收集字符串
    public static HashSet<String> set;

    // 路径，表示添加了哪些字符，最后转成字符串
    public static char[] path;


    /**
     * @param arr  字符数组
     * @param i    下标
     * @param size 路径容量
     */
    public static void f(char[] arr, int i, int size) {

        // 下标来到边界
        if (i == arr.length) {
            // 不能直接转成字符串，要把空的过滤掉
            // set.add(new String(path));
            set.add(new String(path, 0, size));
            return;
        }

        // 不添加当前字符
        f(arr, i + 1, size);

        // 添加当前字符
        path[size] = arr[i];
        f(arr, i + 1, size + 1);

    }


}


























