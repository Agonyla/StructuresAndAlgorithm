package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Agony
 * @Create 2023/8/13 21:04
 * @Version 1.0
 * 最大线段问题
 */
public class Code01_CoverMax {

    // 挫方法
    public static int coverMax1(int[][] lines) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int res = 0;

        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }

        for (double j = min + 0.5; j < max; j += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (j > lines[i][0] && j < lines[i][1]) {
                    cur++;
                }
            }
            res = Math.max(res, cur);
        }
        return res;
    }

    public static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static int coverMax2(int[][] lines) {

        // 1. 先把线段按照起始位置从小打到排序
        // 创建一个Lines[]数组对象里面存放line

        Line[] linesArr = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            linesArr[i] = new Line(lines[i][0], lines[i][1]);
        }
        Arrays.sort(linesArr, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });
        // for (Line line : linesArr) {
        //     System.out.println(line);
        // }
        // 2. 在小根堆中添加line的end值，并用res记录heap的大小
        int res = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < linesArr.length; i++) {
            if (!heap.isEmpty() && heap.peek() <= linesArr[i].start) {
                heap.poll();
            }
            heap.add(linesArr[i].end);
            res = Math.max(res, heap.size());
        }
        return res;
    }

    /**
     * @param len 线段个数
     * @param L   线段起始位置最小值
     * @param R   线段重点最大值
     * @return 线段数组
     */
    public static int[][] generateLines(int len, int L, int R) {
        int size = (int) (Math.random() * len) + 1;
        int[][] lines = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = (int) (Math.random() * (R - L + 1));
            int b = (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            lines[i][0] = Math.min(a, b);
            lines[i][1] = Math.max(a, b);
        }
        return lines;
    }

    public static void main(String[] args) {
        int len = 10;
        int L = 0;
        int R = 100;

        int testTimes = 10000;
        System.out.println("begin");
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(len, L, R);
            int i1 = coverMax1(lines);
            int i2 = coverMax2(lines);
            if (i1 != i2) {
                System.out.println("Oops");
            }
        }
        System.out.println("finish");


        // int[][] lines = new int[][]{
        //         {1, 3},
        //         {2, 4},
        //         {2, 5}
        // };
        //
        // int i1 = coverMax1(lines);
        // int i2 = coverMax2(lines);
        // System.out.println(i1);
        // System.out.println(i2);
    }
}
