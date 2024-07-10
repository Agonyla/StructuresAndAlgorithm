package algorithm_journey.class044;

/**
 * @author: Agony
 * @create: 2024/7/10 09:05
 * @describe:
 * @link:
 */
public class Code01_TrieTree {

    // todo

    public static void main(String[] args) {

        System.out.println('A' - 'a');
        System.out.println('Z' - 'a');

        int a = 1;
        if (a-- == 0) {
            System.out.println("asadf");
        }
    }


    // 用类描述实现前缀树

    // 每个样本 都从头节点开始 根据 前缀字符或者前缀数字 建出来的一棵大树，就是前缀树
    // 没有路就新建节点；已经有路了，就复用节点。
    // 根据"abc"每一个字符新建一条路。
    // o --a-- o --b-- o --c-- o
    // 注意！每个字符表示的是路，不是节点
    // 可以通过给节点添加信息
    // pass -> 表示经过的字符串个数
    // end -> 表示有几个字符串是在该节点处结束的
}
