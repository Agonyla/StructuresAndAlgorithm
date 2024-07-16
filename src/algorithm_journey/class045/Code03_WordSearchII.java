package algorithm_journey.class045;

/**
 * @author: Agony
 * @create: 2024/7/12 10:47
 * @describe:
 * @link:
 */
public class Code03_WordSearchII {


    // todo

    // 在二维字符数组中搜索可能的单词


    // 前缀树实现
    // 1.可以减少无效分支的筛选，网格中某一节点要上下左右尝试，在前缀树中，该节点如果只有到一个节点的路（假设是上），那么另外三个方向的尝试就没有必要了
    // 2.节点的end信息,可以记录来到该节点的字符串，记录到达该节点的变量，收集答案方便
    // 3.节点的pass信息，记录遇到过的字符。在返回的时候，收集到了多少字符，就用pass减去收集到字符的数量，
    // 以便于下次再递归的时候，遇到pass==0了，就直接返回，剪枝

    // int[] pass
    // String[] end

}
