package algorithm_journey.class048;

/**
 * @author: Agony
 * @create: 2024/7/20 11:42
 * @describe:
 * @link:
 */
public class Code01_PrefixSumMatrix {

    // todo

    // 利用二维前缀和信息迅速得到二维区域和

    // 思路
    // 实现数组 int[][] sum
    // sum[i][j] 表示从 nums[0][0]~nums[i][j]的累加和
    // sum 从左往右，从上往下开始加工
    // sum[i][j] = 左边 + 上边 - 左上 + 自己
    // -> sum[i][j] += sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1]
    // 左边 -> nums[0][0]~nums[i][j-1]的累加和
    // 上边 -> nums[0][0]~nums[i-1][j]的累加和
    // 左上 -> nums[0][0]~nums[i-1][j-1]的累加和
    // 左上是左边+上边重复累加的部分，需要减去，再加上自己就是nums[0][0]~nums[i][j]的累加和


    // 怎么计算(1,1)到(2,2)的累加和
    // -> 通过画图，有整体的累加和，需要把哪部分减掉，然后把重复减的部分加上
    // 如求 (a,b) 到 (c,d) 的累加和
    // ans = sum[c][d] - sum[c][b-1] - sum[a-1][d] + sum[a-1][b-1]

    // 注意：实际处理在处理sum的第0行、第0列的时候会有很多条件判断
    // 可以额外加第0行，第0列把sum包裹起来，再处理
    // 0   0   0   0   0
    // 0   10  12  4   1
    // 0   20  20  54  14
    // 0   15  3   5   8
    //
}
