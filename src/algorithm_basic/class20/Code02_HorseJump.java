package algorithm_basic.class20;

/**
 * @Author Agony
 * @Create 2023/9/20 22:01
 * @Version 1.0
 * @describe: 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * 做完这题 去做做N皇后
 */
public class Code02_HorseJump {

    public static int jump(int a, int b, int k) {
        return process(a, b, 0, 0, k);
    }

    /**
     * @param x    目标x
     * @param y    目标y
     * @param i    当前x
     * @param j    当前y
     * @param rest 剩余步数
     * @return 返回方法数
     * @describe: 递归尝试
     */
    public static int process(int x, int y, int i, int j, int rest) {
        // base case
        if (i < 0 || i > 8 || j < 0 || j > 9) {
            return 0;
        }
        // base case
        if (rest == 0) {
            return (i == x && j == y) ? 1 : 0;
        }
        int ways = process(x, y, i + 1, j + 2, rest - 1);
        ways += process(x, y, i + 2, j + 1, rest - 1);
        ways += process(x, y, i + 2, j - 1, rest - 1);
        ways += process(x, y, i + 1, j - 2, rest - 1);
        ways += process(x, y, i - 1, j - 2, rest - 1);
        ways += process(x, y, i - 2, j - 1, rest - 1);
        ways += process(x, y, i - 2, j + 1, rest - 1);
        ways += process(x, y, i - 1, j + 2, rest - 1);
        return ways;
    }

    public static int dp(int a, int b, int k) {

        int[][][] dp = new int[9][10][k + 1];
        dp[a][b][0] = 1;

        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 10; y++) {
                    int ways = pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        return dp[x][y][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump(x, y, step)); //515813
        System.out.println(dp(x, y, step));


    }
}
