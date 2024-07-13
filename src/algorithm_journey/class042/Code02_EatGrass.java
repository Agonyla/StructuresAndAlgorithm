package algorithm_journey.class042;

import java.util.Objects;

/**
 * @author: Agony
 * @create: 2024/7/8 11:21
 * @describe:
 * @link:
 */
public class Code02_EatGrass {


    public static void main(String[] args) {
        for (int i = 0; i < 60; i++) {
            System.out.println(i + " : " + win(i));
        }

        System.out.println("test begin");
        for (int i = 0; i < 80; i++) {
            if (!Objects.equals(win(i), winBetter(i))) {
                System.out.println("oops");
            }
        }
        System.out.println("test end");
    }

    // 草一共有n的重量，两只牛轮流吃草，A牛先吃，B牛后吃
    // 每只牛在自己的回合，吃草的重量必须是4的幂，1、4、16、64....
    // 谁在自己的回合正好把草吃完谁赢，根据输入的n，返回谁赢

    // 思路
    // 设计 String f(int rest, String cur) 函数
    // return -> 获胜选手的名字
    // rest -> 剩下的草
    // cur -> 当前选手的名字
    // 当 rest < 5 时
    // 可以直接判断谁赢
    // 当 rest >= 5 时
    // 递归不断尝试吃的份数尽可能让自己赢

    // n = 0 -> B win
    // n = 1 -> A win
    // n = 2 -> B win
    // n = 3 -> A win
    // n = 4 -> A win


    public static String win(int n) {
        return f(n, "A");
    }

    /**
     * 返回获胜者的名字
     *
     * @param rest 剩下的草
     * @param cur  当前是谁的回合
     * @return
     */
    public static String f(int rest, String cur) {
        String enemy = "A".equals(cur) ? "B" : "A";
        if (rest < 5) {
            if (rest == 0 || rest == 2) {
                return enemy;
            }
            return cur;
        }
        int eat = 1;
        while (eat <= rest) {
            if (f(rest - eat, enemy).equals(cur)) {
                return cur;
            }
            eat *= 4;
        }
        return enemy;
    }


    /**
     * 观察结果：BABAA
     *
     * @param n
     * @return
     */
    public static String winBetter(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "B";
        }
        return "A";
    }

}





