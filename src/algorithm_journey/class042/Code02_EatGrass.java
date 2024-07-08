package algorithm_journey.class042;

/**
 * @author: Agony
 * @create: 2024/7/8 11:21
 * @describe:
 * @link:
 */
public class Code02_EatGrass {

    // todo

    // 草一共有n的重量，两只牛轮流吃草，A牛先吃，B牛后吃
    // 每只牛在自己的回合，吃草的重量必须是4的幂，1、4、16、64....
    // 谁在自己的回合正好把草吃完谁赢，根据输入的n，返回谁赢

    // 思路
    // 设计 String f(int rest, String cur) 函数
    // return -> 获胜选手的名字
    // rest -> 剩下的草
    // cur -> 当前选手的名字
    // 当 rest <= 5 时
    // 可以直接判断谁赢
    // 当 rest > 5 时
    // 递归不断尝试吃的份数尽可能让自己赢
}
