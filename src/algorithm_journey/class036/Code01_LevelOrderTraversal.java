package algorithm_journey.class036;

/**
 * @author: Agony
 * @create: 2024/6/19 09:17
 * @describe:
 * @link:
 */
public class Code01_LevelOrderTraversal {

    // todo

    // 二叉树的层序遍历 BFS (Breadth First Search)
    //
    // 思路
    // 准备一个队列
    // 头节点加入
    // 弹出，有左加左，有右加右
    // 准备一个 hashmap，key：存放节点，value：存放位于第几层
    // 每当队列添加节点时，就在 hashmap 中记录该节点的位置信息
    //
    //
    //
    // 优化
    // 用数组代替队列
    // l=r=0
    // 加入元素，元素来到r的位置，r++
    // 弹出元素，弹出l位置的元素，l--
    // 头节点加入队列       while(队列不为空) 即  l < r
    // 1. 获取队列的大小 size
    // 2. 以下步骤共重复 size 次
    // 弹出节点，有左加左，有右加右
    //
    // 在重复 size 次的过程中，每次弹出的节点，就是该层所有的节点。


}
