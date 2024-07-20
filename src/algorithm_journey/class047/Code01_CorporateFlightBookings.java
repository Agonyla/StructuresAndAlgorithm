package algorithm_journey.class047;

import algorithm_journey.utils.MathUtils;

/**
 * 航班预订统计
 *
 * @author: Agony
 * @create: 2024/7/18 11:44
 * @describe: 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * <p>
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * <p>
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 * <p>
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 * @link: <a href="https://leetcode.cn/problems/corporate-flight-bookings/description/">航班预订统计</a>
 */
public class Code01_CorporateFlightBookings {


    public static void main(String[] args) {

        int[][] bookings = {
                {1, 2, 10},
                {2, 3, 20},
                {2, 5, 25}
        };
        int n = 5;
        int[] ans = corpFlightBookings(bookings, 5);
        MathUtils.printArr(ans);


    }

    // 航班预订统计

    // 思路
    // 一维差分
    // 要在[L,R]位置都加上V
    // -> 在 L 位置加 V；R+1位置减 V
    // 用前缀和的方式整体计算数组
    // 那么在L～R位置都会因为L位置加了个V，导致这些位置因为前缀和的累加都会加上V
    // 但是在R+1位置是减去V，那么之后的位置都会减V，相当于R+1后面的位置不会影响
    // 只有在L～R这段位置会加V


    /**
     * 返回航班预定数组
     *
     * @param bookings 航班信息
     * @param n        航班个数
     * @return 航班预定数组，里面的元素是每个航班预定的座位总数
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {

        // 为什么是n+2
        // -> 首先，航班编号是从1到n的，所以长度为n+1
        // -> 其次，差分的时候是在firsti位置+seatsi，在lasti+1位置-seatsi
        // -> 所以长度为n+2
        int[] cnt = new int[n + 2];

        // set
        for (int i = 0; i < bookings.length; i++) {
            cnt[bookings[i][0]] += bookings[i][2];
            cnt[bookings[i][1] + 1] -= bookings[i][2];
        }

        // build
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = cnt[i + 1];
        }
        return ans;
    }


}
















