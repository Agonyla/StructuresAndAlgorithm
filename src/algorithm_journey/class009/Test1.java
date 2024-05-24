package algorithm_journey.class009;

/**
 * @author Agony
 * @create 2024/5/24 10:40
 */
public class Test1 {

    public static void main(String[] args) {

        int a = 10;
        String s = "name";
        f(a);
        m(s);
        System.out.println("a = " + a);
        System.out.println("s = " + s);

    }

    public static void f(int a) {
        a = 0;
    }

    public static void m(String s) {
        s = "aaa";
    }
}
