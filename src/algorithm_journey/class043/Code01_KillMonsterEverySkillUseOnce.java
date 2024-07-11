package algorithm_journey.class043;

import java.io.*;

/**
 * 消灭怪物
 *
 * @author: Agony
 * @create: 2024/7/8 18:04
 * @describe: 现在有一个打怪类型的游戏，这个游戏是这样的，你有n个技能，每一个技能会有一个伤害，
 * 同时若怪物低于一定的血量，则该技能可能造成双倍伤害，
 * 每一个技能最多只能释放一次，已知怪物有m点血量，现在想问你最少用几个技能能消灭掉他（血量小于等于0）。
 * @link: <a href="https://www.nowcoder.com/practice/d88ef50f8dab4850be8cd4b95514bbbd">消灭怪物</a>
 */
public class Code01_KillMonsterEverySkillUseOnce {


    // 最优的技能释放顺序
    // 现在有一个打怪类型的游戏，这个游戏是这样的，你有n个技能 每一个技能会有一个伤害；
    // 同时，若怪物小于等于一定的血量，则该技能可能造成双倍伤害；
    // 每一个技能最多只能释放一次，已知怪物有m点血量 现在想问你最少用几个技能能消灭掉他(血量小于等于0) 技能的数量是n，怪物的血量是m；
    // i号技能的伤害是x[i]，i号技能触发双倍伤害的血量最小值是y[i]
    // 1 <= n <= 10; 1 <= m、x[i]、y[i] <= 10^6

    // 思路
    // 题目数据量信息，1<=n<=10，n!没有超过 10^7, 可以直接用全排列实现
    // 。。。递归实现全排列


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        // 调用nextToken读取下一个输入
        // 测试次数
        in.nextToken();
        int cases = (int) in.nval;

        for (int i = 0; i < cases; i++) {

            // 技能数
            in.nextToken();
            int n = (int) in.nval;

            // 血量
            in.nextToken();
            int m = (int) in.nval;

            for (int j = 0; j < n; j++) {
                // 技能伤害
                in.nextToken();
                kill[j] = (int) in.nval;

                // 触发血量
                in.nextToken();
                blood[j] = (int) in.nval;
            }

            int ans = f(m, 0, n);
            out.println(ans == Integer.MAX_VALUE ? -1 : ans);

        }


        out.flush();
        br.close();
        out.close();
    }

    public static int MAXN = 11;

    public static int[] kill = new int[MAXN];

    public static int[] blood = new int[MAXN];


    /**
     * 求最少用几个技能
     *
     * @param rest 剩余血量
     * @param i    技能数组下标
     * @param n    技能总数
     * @return 最少使用的技能数量
     */
    public static int f(int rest, int i, int n) {
        if (rest <= 0) {
            return i;
        }
        if (i == n) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        // 全排列实现最少技能数量
        for (int j = i; j < n; j++) {
            swap(i, j);
            // 这里使用i不是j，交换之后，i任然是技能的下标
            int restBlood = rest - (rest <= blood[i] ? kill[i] * 2 : kill[i]);
            ans = Math.min(ans, f(restBlood, i + 1, n));
            swap(i, j);
        }
        return ans;


    }

    /**
     * 交换技能和触发血量
     *
     * @param i
     * @param j
     */
    public static void swap(int i, int j) {
        int tmp = kill[i];
        kill[i] = kill[j];
        kill[j] = tmp;

        tmp = blood[i];
        blood[i] = blood[j];
        blood[j] = tmp;
    }

}















