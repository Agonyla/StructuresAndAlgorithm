package class23;

/**
 * @Author Agony
 * @Create 2023/10/6 16:14
 * @Version 1.0
 * @describe: N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class Code03_NQueens {

    /**
     * @describe: 使用数组记录每一行的位置信息
     */
    public static int num1(int n) {
        if (n < 0) {
            return 0;
        }
        int[] record = new int[n];
        return process(n, record, 0);
    }

    /**
     * @param n      n个整数
     * @param record 一维数组来记录每行皇后放的位置  下边记录行，record[i] 记录列
     * @param index  目前位于index行
     * @return 返回摆法
     * 当前来到i行，一共是0~N-1行
     * 在i行上放皇后，所有列都尝试
     * 必须要保证跟之前所有的皇后不打架
     */
    public static int process(int n, int[] record, int index) {
        // base case
        // 当 index 来到 record.length的时候说明棋盘被全部摆满
        if (index == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < record.length; j++) {
            if (isValid(record, index, j)) {
                record[index] = j;
                res += process(n, record, index + 1);
            }
        }
        return res;
    }

    /**
     * @param record 一维数组来记录每行皇后放的位置
     * @param index  当前所在行
     * @param j      当前所在列
     * @return 是否在同一列或者同一斜线上
     * @describe: 保证跟之前所有的皇后不打架
     */
    public static boolean isValid(int[] record, int index, int j) {
        for (int i = 0; i < index; i++) {
            if (record[i] == j || Math.abs(i - index) == Math.abs(record[i] - j)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 使用位运算来记录 只能处理最多32X32的棋盘
     */
    public static int num2(int n) {
        if (n < 0 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * @param limit       记录棋盘，8X8的棋盘 => 0...11111111
     * @param colLim      列限制.  假设某一行的第三列放棋  => 0...00000100
     * @param leftDiaLim  左列限制. 假设某一行的第三列放棋 => 0...00001000
     * @param rightDiaLim 右列限制. 假设某一行的第三列放棋 => 0...00000010
     * @return 返回摆法
     */
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        // base case
        // 当列限制全部摆满 => 有一种可行的方法
        if (colLim == limit) {
            return 1;
        }
        // 可以摆放的位置 => 可摆位置为1
        int position = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (position != 0) {
            // 获取position最右侧的1，从最右侧开始尝试放棋
            mostRightOne = position & (~position + 1);
            position -= mostRightOne;
            // 每次放棋之后，leftDiaLim需要与mostRightOne合并然后左移
            // rightDiaLim需要与mostRightOne合并然后右移
            // => 就是棋盘放置棋子之后其下面的所有列和斜线上的位置均不能放置棋
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(num1(8));

        System.out.println(num2(8));
    }


}
