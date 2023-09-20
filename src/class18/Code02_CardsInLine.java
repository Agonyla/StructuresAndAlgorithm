package class18;

/**
 * @Author Agony
 * @Create 2023/9/19 20:48
 * @Version 1.0
 * @describe: 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 */
public class Code02_CardsInLine {

    public static int win(int[] arr) {
        // base case
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;
        int first = firstDraw(arr, 0, N - 1);
        int second = secondDraw(arr, 0, N - 1);
        return Math.max(first, second);
    }

    /**
     * @param arr 纸牌数组
     * @param L   数组最左端
     * @param R   数组最右端
     * @return 返回分数
     * @describe: 先手抽牌
     */
    public static int firstDraw(int[] arr, int L, int R) {
        // base case
        if (L == R) {
            return arr[L];
        }
        // 抽最左边
        int p1 = arr[L] + secondDraw(arr, L + 1, R);
        // 抽最右端
        int p2 = arr[R] + secondDraw(arr, L, R - 1);
        return Math.max(p1, p2);
    }


    /**
     * @param arr 纸牌数组
     * @param L   数组最左端
     * @param R   数组最右端
     * @return 返回分数
     * @describe: 后手抽牌
     */
    public static int secondDraw(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        // 对手拿走了最左边的位置
        int p1 = firstDraw(arr, L + 1, R);
        // 对手拿走了最右边的位置
        int p2 = firstDraw(arr, L, R - 1);
        return Math.min(p1, p2);
    }


    /**
     * @describe: 缓存
     */
    public static int win2(int[] arr) {
        // base case
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // L 的取值范围 0~N-1  R的取值范围 0~N-1
        int[][] firstMap = new int[arr.length][arr.length];
        int[][] secondMap = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                firstMap[i][j] = -1;
                secondMap[i][j] = -1;
            }
        }
        int first = firstDraw2(arr, 0, arr.length - 1, firstMap, secondMap);
        int second = secondDraw2(arr, 0, arr.length - 1, firstMap, secondMap);

        return Math.max(first, second);
    }


    public static int firstDraw2(int[] arr, int L, int R, int[][] firstMap, int[][] secondMap) {
        // base case
        if (firstMap[L][R] != -1) {
            return firstMap[L][R];
        }
        if (L == R) {
            firstMap[L][R] = arr[L];
            return arr[L];
        } else {
            // 抽最左边
            int p1 = arr[L] + secondDraw2(arr, L + 1, R, firstMap, secondMap);
            // 抽最右端
            int p2 = arr[R] + secondDraw2(arr, L, R - 1, firstMap, secondMap);
            int res = Math.max(p1, p2);
            firstMap[L][R] = res;
            return res;
        }

    }


    public static int secondDraw2(int[] arr, int L, int R, int[][] firstMap, int[][] secondMap) {
        if (secondMap[L][R] != -1) {
            return secondMap[L][R];
        }
        if (L == R) {
            secondMap[L][R] = 0;
            return 0;
        } else {
            // 对手拿走了最左边的位置
            int p1 = firstDraw2(arr, L + 1, R, firstMap, secondMap);
            // 对手拿走了最右边的位置
            int p2 = firstDraw2(arr, L, R - 1, firstMap, secondMap);
            int res = Math.min(p1, p2);
            secondMap[L][R] = res;
            return res;
        }

    }


    /**
     * @describe: 动态规划
     */
    public static int win3(int[] arr) {
        // base case

        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        // L 的取值范围 0~N-1  R的取值范围 0~N-1
        int[][] firstMap = new int[N][N];
        int[][] secondMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            firstMap[i][i] = arr[i];
        }

        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                firstMap[L][R] = Math.max(arr[L] + secondMap[L + 1][R], arr[R] + secondMap[L][R - 1]);
                secondMap[L][R] = Math.min(firstMap[L + 1][R], firstMap[L][R - 1]);
                L++;
                R++;
            }
        }

        return Math.max(firstMap[0][N - 1], secondMap[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }


}
