package practice.stream;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/31 13:23
 * @describe:
 */
public class StreamPractice8 {

    record Student(
            long id,
            String name,
            String city,
            int age
    ) {
    }

    record Course(
            long id,
            String name,
            String category,
            String teacher,
            int difficulty
    ) {
    }

    record Enrollment(
            long studentId,
            long courseId,
            LocalDate enrollDate
    ) {
    }

    record StudyRecord(
            long studentId,
            long courseId,
            LocalDate studyDate,
            int minutes
    ) {
    }

    record ExamResult(
            long studentId,
            long courseId,
            int score,
            LocalDate examDate
    ) {
    }

    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student(1, "Alice", "Shanghai", 22),
                new Student(2, "Bob", "Beijing", 25),
                new Student(3, "Charlie", "Shanghai", 28),
                new Student(4, "David", "Shenzhen", 24),
                new Student(5, "Eve", "Beijing", 31),
                new Student(6, "Frank", "Hangzhou", 27),
                new Student(7, "Grace", "Shanghai", 26),
                new Student(8, "Henry", "Shenzhen", 30)
        );

        List<Course> courses = List.of(
                new Course(101, "Java Basics", "Java", "Tom", 2),
                new Course(102, "Java Stream", "Java", "Tom", 4),
                new Course(103, "Spring Boot", "Java", "Jerry", 4),
                new Course(104, "Redis", "Backend", "Jerry", 3),
                new Course(105, "MySQL", "Database", "Lucy", 3),
                new Course(106, "Kafka", "Backend", "Mike", 5),
                new Course(107, "Docker", "DevOps", "Mike", 3),
                new Course(108, "Kubernetes", "DevOps", "Mike", 5),
                new Course(109, "Python Basics", "Python", "Lucy", 2),
                new Course(110, "Data Analysis", "Python", "Lucy", 4)
        );

        List<Enrollment> enrollments = List.of(
                new Enrollment(1, 101, LocalDate.of(2026, 7, 1)),
                new Enrollment(1, 102, LocalDate.of(2026, 7, 5)),
                new Enrollment(1, 103, LocalDate.of(2026, 7, 10)),
                new Enrollment(1, 107, LocalDate.of(2026, 7, 15)),

                new Enrollment(2, 101, LocalDate.of(2026, 7, 2)),
                new Enrollment(2, 102, LocalDate.of(2026, 7, 6)),
                new Enrollment(2, 105, LocalDate.of(2026, 7, 11)),
                new Enrollment(2, 106, LocalDate.of(2026, 7, 20)),

                new Enrollment(3, 102, LocalDate.of(2026, 7, 3)),
                new Enrollment(3, 103, LocalDate.of(2026, 7, 8)),
                new Enrollment(3, 104, LocalDate.of(2026, 7, 13)),
                new Enrollment(3, 106, LocalDate.of(2026, 7, 18)),
                new Enrollment(3, 108, LocalDate.of(2026, 7, 22)),

                new Enrollment(4, 101, LocalDate.of(2026, 7, 1)),
                new Enrollment(4, 104, LocalDate.of(2026, 7, 7)),
                new Enrollment(4, 107, LocalDate.of(2026, 7, 14)),

                new Enrollment(5, 105, LocalDate.of(2026, 7, 4)),
                new Enrollment(5, 109, LocalDate.of(2026, 7, 9)),
                new Enrollment(5, 110, LocalDate.of(2026, 7, 16)),

                new Enrollment(6, 102, LocalDate.of(2026, 7, 5)),
                new Enrollment(6, 103, LocalDate.of(2026, 7, 10)),
                new Enrollment(6, 106, LocalDate.of(2026, 7, 17)),
                new Enrollment(6, 107, LocalDate.of(2026, 7, 21)),

                new Enrollment(7, 101, LocalDate.of(2026, 7, 2)),
                new Enrollment(7, 102, LocalDate.of(2026, 7, 7)),
                new Enrollment(7, 109, LocalDate.of(2026, 7, 12)),
                new Enrollment(7, 110, LocalDate.of(2026, 7, 19)),

                new Enrollment(8, 104, LocalDate.of(2026, 7, 3)),
                new Enrollment(8, 105, LocalDate.of(2026, 7, 8)),
                new Enrollment(8, 106, LocalDate.of(2026, 7, 15)),
                new Enrollment(8, 108, LocalDate.of(2026, 7, 23))
        );

        List<StudyRecord> studyRecords = List.of(

                new StudyRecord(1, 102, LocalDate.of(2026, 8, 1), 50),
                new StudyRecord(1, 102, LocalDate.of(2026, 8, 2), 60),
                new StudyRecord(1, 103, LocalDate.of(2026, 8, 3), 40),
                new StudyRecord(1, 103, LocalDate.of(2026, 8, 4), 70),
                new StudyRecord(1, 107, LocalDate.of(2026, 8, 5), 30),

                new StudyRecord(2, 101, LocalDate.of(2026, 8, 1), 30),
                new StudyRecord(2, 102, LocalDate.of(2026, 8, 3), 80),
                new StudyRecord(2, 105, LocalDate.of(2026, 8, 4), 50),
                new StudyRecord(2, 106, LocalDate.of(2026, 8, 5), 100),
                new StudyRecord(2, 106, LocalDate.of(2026, 8, 6), 90),

                new StudyRecord(3, 102, LocalDate.of(2026, 8, 1), 70),
                new StudyRecord(3, 103, LocalDate.of(2026, 8, 2), 80),
                new StudyRecord(3, 104, LocalDate.of(2026, 8, 3), 45),
                new StudyRecord(3, 106, LocalDate.of(2026, 8, 4), 120),
                new StudyRecord(3, 108, LocalDate.of(2026, 8, 5), 110),

                new StudyRecord(4, 101, LocalDate.of(2026, 8, 2), 35),
                new StudyRecord(4, 104, LocalDate.of(2026, 8, 4), 55),
                new StudyRecord(4, 107, LocalDate.of(2026, 8, 6), 65),

                new StudyRecord(5, 105, LocalDate.of(2026, 8, 1), 60),
                new StudyRecord(5, 109, LocalDate.of(2026, 8, 2), 40),
                new StudyRecord(5, 110, LocalDate.of(2026, 8, 3), 90),
                new StudyRecord(5, 110, LocalDate.of(2026, 8, 4), 100),

                new StudyRecord(6, 102, LocalDate.of(2026, 8, 1), 75),
                new StudyRecord(6, 103, LocalDate.of(2026, 8, 2), 65),
                new StudyRecord(6, 106, LocalDate.of(2026, 8, 3), 130),
                new StudyRecord(6, 107, LocalDate.of(2026, 8, 4), 45),

                new StudyRecord(7, 101, LocalDate.of(2026, 8, 1), 25),
                new StudyRecord(7, 102, LocalDate.of(2026, 8, 2), 55),
                new StudyRecord(7, 109, LocalDate.of(2026, 8, 3), 50),
                new StudyRecord(7, 110, LocalDate.of(2026, 8, 4), 95),

                new StudyRecord(8, 104, LocalDate.of(2026, 8, 2), 60),
                new StudyRecord(8, 105, LocalDate.of(2026, 8, 3), 70),
                new StudyRecord(8, 106, LocalDate.of(2026, 8, 4), 125),
                new StudyRecord(8, 108, LocalDate.of(2026, 8, 5), 140)
        );

        List<ExamResult> examResults = List.of(
                new ExamResult(1, 101, 78, LocalDate.of(2026, 8, 10)),
                new ExamResult(1, 102, 82, LocalDate.of(2026, 8, 12)),
                new ExamResult(1, 102, 91, LocalDate.of(2026, 8, 20)),
                new ExamResult(1, 103, 88, LocalDate.of(2026, 8, 15)),

                new ExamResult(2, 101, 85, LocalDate.of(2026, 8, 10)),
                new ExamResult(2, 102, 76, LocalDate.of(2026, 8, 12)),
                new ExamResult(2, 105, 92, LocalDate.of(2026, 8, 15)),
                new ExamResult(2, 106, 81, LocalDate.of(2026, 8, 21)),

                new ExamResult(3, 102, 94, LocalDate.of(2026, 8, 11)),
                new ExamResult(3, 103, 89, LocalDate.of(2026, 8, 14)),
                new ExamResult(3, 104, 90, LocalDate.of(2026, 8, 16)),
                new ExamResult(3, 106, 95, LocalDate.of(2026, 8, 22)),
                new ExamResult(3, 108, 87, LocalDate.of(2026, 8, 25)),

                new ExamResult(4, 101, 70, LocalDate.of(2026, 8, 10)),
                new ExamResult(4, 104, 83, LocalDate.of(2026, 8, 17)),
                new ExamResult(4, 107, 86, LocalDate.of(2026, 8, 20)),

                new ExamResult(5, 105, 96, LocalDate.of(2026, 8, 12)),
                new ExamResult(5, 109, 88, LocalDate.of(2026, 8, 15)),
                new ExamResult(5, 110, 93, LocalDate.of(2026, 8, 22)),

                new ExamResult(6, 102, 90, LocalDate.of(2026, 8, 11)),
                new ExamResult(6, 103, 84, LocalDate.of(2026, 8, 15)),
                new ExamResult(6, 106, 92, LocalDate.of(2026, 8, 23)),
                new ExamResult(6, 107, 79, LocalDate.of(2026, 8, 24)),

                new ExamResult(7, 101, 81, LocalDate.of(2026, 8, 10)),
                new ExamResult(7, 102, 87, LocalDate.of(2026, 8, 13)),
                new ExamResult(7, 109, 91, LocalDate.of(2026, 8, 16)),
                new ExamResult(7, 110, 89, LocalDate.of(2026, 8, 23)),

                new ExamResult(8, 104, 88, LocalDate.of(2026, 8, 14)),
                new ExamResult(8, 105, 90, LocalDate.of(2026, 8, 16)),
                new ExamResult(8, 106, 93, LocalDate.of(2026, 8, 22)),
                new ExamResult(8, 108, 97, LocalDate.of(2026, 8, 26))
        );

        // 1. 每个城市学习时间最多的学生 -> Map<String, Student>
        System.out.println(t1(students, studyRecords));

    }

    // 1. 每个城市学习时间最多的学生 -> Map<String, Student>
    // 总学习时间高的优先
    // 总学习时间相同时，年龄小的优先
    // 年龄也相同时，id 小的优先
    public static Map<String, Student> t1(List<Student> students, List<StudyRecord> studyRecords) {

        Map<Long, Integer> studyMap = studyRecords.stream()
                .collect(Collectors.toMap(
                        StudyRecord::studentId,
                        StudyRecord::minutes,
                        Integer::sum
                ));

        return students.stream()
                .collect(Collectors.groupingBy(
                                Student::city,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .filter(s -> studyMap.containsKey(s.id()))
                                                .max(
                                                        Comparator.comparingInt((Student s) -> studyMap.get(s.id()))
                                                                .reversed()
                                                                .thenComparing(Student::age)
                                                                .thenComparing(Student::id)
                                                )
                                                .orElse(null)
                                )
                        )
                );
    }

    // 2. 每个课程类别学习时间 Top 2 学生 -> Map<String, List<Long>>
    // 汇总学生在该 category 下所有课程的总学习时间；、
    // 每个 category 取学习时间最多的前 2 名
    // 时间相同时 studentId 小的优先
    public static Map<String, List<Long>> t2(List<Course> courses) {

        return null;
    }

}