package algorithm_basic.class11;

/**
 * @Author Agony
 * @Create 2023/8/25 19:58
 * @Version 1.0
 */
public class Code07_PaperFolding {

    /**
     * @param N 对折次数
     */
    public static void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }

    /**
     * @param i    第几次对折
     * @param N    总对折次数
     * @param down 凹：true， 凸：false
     */
    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down ? " 凹 " : " 凸 ");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
