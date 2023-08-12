package class06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Author Agony
 * @Create 2023/8/10 20:39
 * @Version 1.0
 */
public class Code01_Comparator {
    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {


        System.out.println((0 - 1) / 2);

        ArrayList<Integer> list = new ArrayList<>();
        TreeMap<Student, String> stringStringTreeMap = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id - o2.id;
            }
        });

        // lambda 表达式？？？
        TreeMap<Student, String> stringStringTreeMap2 = new TreeMap<>((a, b) -> a.id - b.id);

        // public TreeMap(Comparator<? super K> comparator)

        // list.sort();
        // public void sort(Comparator<? super E> c)

        // Arrays.sort()
        // public static <T> void sort(T[] a, Comparator<? super T> c)

    }
}
