import class16.Node;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Agony
 * @Create 2023/8/9 13:48
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);


        int[][] lines = new int[][]{
                {1, 2, 3},
                {3, 4, 5}
        };
        System.out.println(lines.length);

        System.out.println("================");
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(7);
        heap.add(4);
        heap.add(3);

        for (Object o : heap.toArray()) {

            System.out.println(o);
        }

        System.out.println("================================");
        String n1 = null;
        String n2 = null;
        System.out.println(n1 == n2);

        System.out.println("========================================");
        String a = "ab";
        String b = "bb";
        System.out.println(a.compareTo(b));

        System.out.println("============================");


        int[] arr = {4, 2, 6, 8, 44, 8, 5};
        Arrays.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("============for循环hi--测试============");

        int hi = 3;
        for (hi--; hi >= 0; hi--) {
            System.out.println(hi);
        }

        System.out.println("===============Node 空 nexts 列表测试===============");
        Node node = new Node(10);
        for (Node next : node.nexts) {
            System.out.println("a");
        }
        System.out.println("node = " + node);

        System.out.println("=========Integer.compare()测试==========");

        int num1 = 1;
        int num2 = 10;
        System.out.println(Integer.compare(num1, num2));

        System.out.println("==========ASCII测试===========");

        char ch = 'a';
        char ch2 = 'b';
        System.out.println(ch + 0); // 97
        System.out.println(ch > 96); // true
        System.out.println(ch * 2);  // 194
        System.out.println(ch + ch2); // 195
        System.out.println(ch + "" + ch2); // ab

        char ch3 = '1';  // '1'对应ASCII码49   '0'对应ASCII码48
        // 字符在运算的过程中，会先转成ASCII的数字，然后再进行运算
        System.out.println(ch3 * 10); // 490
        System.out.println(ch3 + 0); // 49
        System.out.println(ch3 - 0); // 49

        System.out.println(ch3 + '0'); // 97
        System.out.println(ch3 - '0'); // 1


        System.out.println("======下半角打印测试========");

        int N = 8;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(" A ");
            }
            System.out.println();
        }


        System.out.println("======上半角打印测试========");

        int M = 8;
        for (int i = 0; i < M; i++) {
            for (int j = M - i; j > 0; j--) {
                System.out.print(" A ");
            }
            System.out.println();
        }

    }
}
