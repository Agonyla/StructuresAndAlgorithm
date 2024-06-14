package algorithm_journey.class035;

/**
 * @author: Agony
 * @create: 2024/6/14 10:16
 * @describe:
 * @link:
 */
public class Code01_SetAllHashMap {


    // todo

    // setAll功能的哈希表
    // 可以一键把hashmap内的所有值设置成同一个值，而不去遍历
    //
    // 实现思路
    // HashMap<Integer,int[]>
    // key 存放键，value 存放 int[2]的数组，arr[0] 存放值，arr[1] 存放放入hashmap的时间
    // 额外准备 cnt、setAllValue、setAllTime
    // 每次存放值时，cnt++
    // 使用 setAll 时，设置 setAllValue 的值，并记录 setAllTime 的时间
    // 从hashmap取值时，比较 value 中存放的时间与 setAllTime 的时间。
    // 谁后设置返回谁


}
