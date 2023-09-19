package class19;

/**
 * @Author Agony
 * @Create 2023/9/19 15:19
 * @Version 1.0
 */
public class Code01_Knapsack {

    // 判断货物的时候 试一下能不能用下面这个
    //int possible2 = rest - w[index] < 0 ? 0 : process(w, v, index + 1, rest - w[index]) + v[index];
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);


    }
}
