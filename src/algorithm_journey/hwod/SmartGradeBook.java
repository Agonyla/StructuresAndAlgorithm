package algorithm_journey.hwod;

import java.util.*;

/**
 * @author: Agony
 * @create: 2025/4/2 21:00
 * @describe: 智能成绩表
 */
public class SmartGradeBook {

    // 记录需要按照哪门课程排序的id
    static int classId = 0;
    static HashMap<String, Integer> map = new HashMap<>();
    static ArrayList<Student> students = new ArrayList<>();

    static class StuCom implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            // 分数不一样从高到低排序
            if (!Objects.equals(o1.score.get(classId), o2.score.get(classId))) {
                return o2.score.get(classId) - o1.score.get(classId);
            }
            // 分数一样按名字 字典序排序
            return o1.name.compareTo(o2.name);
        }
    }


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        // 学生人数
        int n = scanner.nextInt();

        // 科目数
        int m = scanner.nextInt();

        // 读取科目名称行
        for (int i = 0; i < m; i++) {
            String className = scanner.next();
            map.put(className, i + 1);
        }

        for (int i = 0; i < n; i++) {
            Student student = new Student();
            student.name = scanner.next();
            int sum = 0;
            student.score.add(sum);
            for (int j = 0; j < m; j++) {
                int score = scanner.nextInt();
                student.score.add(score);
                sum += score;
            }
            student.score.set(0, sum);
            students.add(student);
        }

        String last = scanner.next();
        classId = map.getOrDefault(last, 0);
        students.sort(new StuCom());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < students.size(); i++) {
            result.append(students.get(i).name);
            if (i < students.size() - 1) {
                result.append(" ");
            }
        }
        System.out.println(result);


    }


}


class Student {


    String name;

    ArrayList<Integer> score;

    public Student() {
        score = new ArrayList<>();
    }

}



