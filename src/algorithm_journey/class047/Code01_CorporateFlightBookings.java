package algorithm_journey.class047;

/**
 * @author: Agony
 * @create: 2024/7/18 11:44
 * @describe:
 * @link:
 */
public class Code01_CorporateFlightBookings {

    // todo

    // 航班预订统计

    // 思路
    // 一维差分
    // 要在[L,R]位置都加上V
    // -> 在 L 位置加 V；R+1位置减 V
    // 用前缀和的方式整体计算数组
    // 那么在L～R位置都会因为L位置加了个V，导致这些位置因为前缀和的累加都会加上V
    // 但是在R+1位置是减去V，那么之后的位置都会减V，相当于R+1后面的位置不会影响
    // 只有在L～R这段位置会加V


}
