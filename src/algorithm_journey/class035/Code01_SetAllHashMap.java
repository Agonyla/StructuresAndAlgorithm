package algorithm_journey.class035;

import java.io.*;
import java.util.HashMap;

/**
 * 设计有setAll功能的哈希表
 *
 * @author: Agony
 * @create: 2024/6/14 10:16
 * @describe: 哈希表常见的三个操作时put、get和containsKey，而且这三个操作的时间复杂度为O(1)。
 * 现在想加一个setAll功能，就是把所有记录value都设成统一的值。请设计并实现这种有setAll功能的哈希表，
 * 并且put、get、containsKey和setAll四个操作的时间复杂度都为O(1)。
 * [友情提示]: C++选手若有需要可以使用unordered_map替换map来将复杂度从O(log n)降为O(1)
 * @link: <a href="https://www.nowcoder.com/practice/7c4559f138e74ceb9ba57d76fd169967">设计有setAll功能的哈希表</a>
 */
public class Code01_SetAllHashMap {


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


    public static int n, op, a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            map.clear();
            setAllValue = 0;
            setAllTime = -1;
            cnt = 0;
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                op = (int) in.nval;
                if (op == 1) {
                    in.nextToken();
                    a = (int) in.nval;
                    in.nextToken();
                    b = (int) in.nval;
                    put(a, b);
                } else if (op == 2) {
                    in.nextToken();
                    a = (int) in.nval;
                    out.println(get(a));
                } else {
                    in.nextToken();
                    a = (int) in.nval;
                    setAll(a);
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }


    public static HashMap<Integer, int[]> map = new HashMap<>();
    public static int cnt;
    public static int setAllValue;
    public static int setAllTime;

    /**
     * get操作
     *
     * @param key
     * @return
     */
    public static int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int[] value = map.get(key);
        if (value[1] <= setAllTime) {
            return setAllValue;
        }
        return value[0];
    }

    /**
     * put 操作
     *
     * @param key
     * @param val
     */
    public static void put(int key, int val) {
        if (map.containsKey(key)) {
            int[] value = map.get(key);
            value[0] = val;
            value[1] = cnt++;

        } else {
            map.put(key, new int[]{val, cnt++});
        }
    }

    /**
     * 设置全部操作
     *
     * @param val
     */
    public static void setAll(int val) {
        setAllValue = val;
        setAllTime = cnt++;
    }


}





