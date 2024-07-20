package algorithm_journey.class047;

/**
 * @author: Agony
 * @create: 2024/7/19 10:25
 * @describe:
 * @link:
 */
public class Code03_WaterHeight {


    // todo

    public static void main(String[] args) {

        
    }


    // 一群人落水后求每个位置的水位高度

    // 思路
    // 转化成四段等差数列 公差为1
    // 设计一个 OFFSET 偏移量
    // 如果落水点 x 离 0 位置比较近，那么其左边界 x-3v 很有可能越界
    // 右边界同理
    // 为了让整个过程减少 if 判断，通过添加 OFFSET 偏移量
    // 让返回数组置于大数组中间部分，使得过程计算更加流畅
    // 只需要在返回的时候加上这个 OFFSET 即可
}
