package algorithm_journey.hwod;

import java.util.ArrayList;

/**
 * @author: Agony
 * @create: 2025/4/2 21:44
 * @describe:
 */
public class Test {


    public static void main(String[] args) {


        ArrayList<Stu> stus = new ArrayList<>();

        stus.add(new Stu("x", 88));
        stus.add(new Stu("f", 88));
        stus.add(new Stu("d", 190));

        stus.sort((o1, o2) -> {
            if (o1.score != o2.score) {
                return o2.score - o1.score;
            }
            return o1.name.compareTo(o2.name);
        });

        for (Stu stu : stus) {
            System.out.print(stu.name + " ");
        }
        System.out.println();


        ArrayList<Integer> list = new ArrayList<>();


        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0, 123);

        for (Integer num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}

class Stu {

    String name;
    int score;

    public Stu(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
