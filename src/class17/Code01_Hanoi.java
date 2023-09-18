package class17;

/**
 * @Author Agony
 * @Create 2023/9/18 19:46
 * @Version 1.0
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 */
public class Code01_Hanoi {

    // 最明白的版本
    public static void hanoi1(int N) {
        leftToRight(N);
    }

    // 从左边移到右边
    public static void leftToRight(int N) {
        // base case
        if (N == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        // 先把 1 ~ N -1 个从左边移到中间
        leftToMiddle(N - 1);
        // 把 N 个从左边移到右边
        System.out.println("move " + N + " from left to right");
        // 把　1 ~ N - 1 的从中间移到右边
        middleToRight(N - 1);
    }

    public static void leftToMiddle(int N) {
        // base case
        if (N == 1) {
            System.out.println("move 1 from left to middle");
            return;
        }
        // 先把 1 ~ N -1 个从左边移到右边
        leftToRight(N - 1);
        // 把 N 个从左边移到中间
        System.out.println("move " + N + " from left to middle");
        // 把　1 ~ N - 1 的从右边移到中间
        rightToMiddle(N - 1);
    }

    public static void middleToRight(int N) {
        // base case
        if (N == 1) {
            System.out.println("move 1 from middle to right");
            return;
        }
        // 先把 1 ~ N -1 个从中间移到左边
        middleToLeft(N - 1);
        // 把 N 个从中间移到右边
        System.out.println("move " + N + " from middle to right");
        // 把　1 ~ N - 1 的从左边移到右边
        leftToRight(N - 1);

    }

    public static void rightToMiddle(int N) {
        // base case
        if (N == 1) {
            System.out.println("move 1 from right to middle");
            return;
        }
        // 先把 1 ~ N -1 个从右边移到左边
        rightToLeft(N - 1);
        // 把 N 个从右边移到中间
        System.out.println("move " + N + " from right to middle");
        // 把　1 ~ N - 1 的从左边移到中间
        leftToRight(N - 1);
    }

    public static void middleToLeft(int N) {
        // base case
        if (N == 1) {
            System.out.println("move 1 from middle to left");
            return;
        }
        // 先把 1 ~ N -1 个从中间移到右边
        middleToRight(N - 1);
        // 把 N 个从中间移到左边
        System.out.println("move " + N + " from middle to left");
        // 把　1 ~ N - 1 的从右边移到左边
        rightToLeft(N - 1);
    }

    public static void rightToLeft(int N) {
        // base case
        if (N == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        // 先把 1 ~ N -1 个从右边移到中间
        rightToMiddle(N - 1);
        // 把 N 个从右边移到左边
        System.out.println("move " + N + " from right to left");
        // 把　1 ~ N - 1 的从中间移到左边
        middleToLeft(N - 1);
    }


    public static void hanoi2(int N) {
        move(N, "left", "right", "middle");
    }

    /**
     * @param N      移动 N 个数字
     * @param from   从 from 开始移动
     * @param target 移动到target
     * @param other  中间的地方
     */
    public static void move(int N, String from, String target, String other) {
        // base case
        if (N == 1) {
            System.out.println("move " + N + " from " + from + " to " + target);
            return;
        }
        // 把 1~N-1的从 form 移到 other
        move(N - 1, from, other, target);
        // 把 N 从 from 移到 target
        System.out.println("move " + N + " from " + from + " to " + target);
        // 把 1~N-1 从 other 移到 target
        move(N - 1, other, target, from);
    }


    public static void main(String[] args) {
        hanoi1(3);
        System.out.println("=====================");
        hanoi2(3);
    }
}
