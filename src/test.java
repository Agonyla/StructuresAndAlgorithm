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

    }
}
