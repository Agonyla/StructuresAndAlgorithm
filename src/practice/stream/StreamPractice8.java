package practice.stream;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
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
        // 2. 每个课程类别学习时间 Top 2 学生 -> Map<String, List<Long>>
        System.out.println(t2(courses, studyRecords));
        // 3. 找出学习类别最多的学生 -> List<Student>
        System.out.println(t3(courses, studyRecords, students));
        // 4. 找出连续学习至少 4 天的学生 -> Set<Long>
        System.out.println(t4(studyRecords));
        // 5. 计算每个学生最长连续学习天数 -> Map<Long, Integer>
        System.out.println(t5(studyRecords));
        // 6. 找出“共同选课最多”的学生组合 Map<StudentPair, Long>
        System.out.println(t6(enrollments));
        // 7. 找出每门课程共同选课学生组合 -> Map<StudentPair, Long>
        System.out.println(t7(enrollments));
        // 8. 分析哪些课程最经常被同一个学生一起选择 -> Map<CoursePair, Long>
        System.out.println(t8(enrollments));

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

    // !!!
    // 2. 每个课程类别学习时间 Top 2 学生 -> Map<String, List<Long>>
    // 汇总学生在该 category 下所有课程的总学习时间；
    // 每个 category 取学习时间最多的前 2 名
    // 时间相同时 studentId 小的优先
    public static Map<String, List<Long>> t2(List<Course> courses, List<StudyRecord> studyRecords) {

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        record CategoryStudent(String category, long studentId) {
        }

        Map<CategoryStudent, Integer> categoryStudentStudyMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                        studyRecord -> new CategoryStudent(
                                courseMap.get(studyRecord.courseId()).category(),
                                studyRecord.studentId()
                        ),
                        Collectors.summingInt(StudyRecord::minutes)
                ));

        return categoryStudentStudyMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().category(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(
                                                Map.Entry.<CategoryStudent, Integer>comparingByValue().reversed()
                                                        .thenComparing(e -> e.getKey().studentId())
                                        )
                                        .limit(2)
                                        .map(e -> e.getKey().studentId())
                                        .toList()
                        )
                ));
    }

    // 3. 找出学习类别最多的学生 -> List<Student>
    // 选了课但是没有 StudyRecord 不算真正学习过
    // 按 studentId ASC
    public static List<Student> t3(List<Course> courses, List<StudyRecord> studyRecords,
                                   List<Student> students) {

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<Long, Student> studentMap = students.stream()
                .collect(Collectors.toMap(
                        Student::id,
                        Function.identity()
                ));

        Map<Long, Integer> studentCategoryCount = studyRecords.stream()
                .collect(Collectors.groupingBy(
                                StudyRecord::studentId,
                                Collectors.mapping(
                                        studyRecord -> courseMap.get(studyRecord.courseId()).category(),
                                        Collectors.collectingAndThen(
                                                Collectors.toSet(),
                                                Set::size

                                        )
                                )
                        )
                );

        int maxCount = studentCategoryCount.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getValue)
                .orElse(0);

        return studentCategoryCount.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)
                .map(e -> studentMap.get(e.getKey()))
                .sorted(Comparator.comparing(Student::id))
                .toList();
    }

    // 4. 找出连续学习至少 4 天的学生 -> Set<Long>
    public static Set<Long> t4(List<StudyRecord> studyRecords) {

        Map<Long, Boolean> studentMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                        StudyRecord::studentId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    List<LocalDate> dates = list.stream()
                                            .map(StudyRecord::studyDate)
                                            .distinct()
                                            .sorted()
                                            .toList();

                                    int continueDays = 1;
                                    for (int i = 1; i < dates.size(); i++) {

                                        if (dates.get(i).equals(dates.get(i - 1).plusDays(1L))) {
                                            continueDays++;
                                        } else {
                                            continueDays = 1;
                                        }

                                        if (continueDays >= 4) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        )
                ));

        return studentMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    // 5. 计算每个学生最长连续学习天数 -> Map<Long, Integer>
    // 在题 4 上升级
    public static Map<Long, Integer> t5(List<StudyRecord> studyRecords) {

        return studyRecords.stream()
                .collect(Collectors.groupingBy(
                        StudyRecord::studentId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    List<LocalDate> dates = list.stream()
                                            .map(StudyRecord::studyDate)
                                            .distinct()
                                            .sorted()
                                            .toList();

                                    int continueDays = 1;
                                    int maxDays = 1;
                                    for (int i = 1; i < dates.size(); i++) {

                                        if (dates.get(i).equals(dates.get(i - 1).plusDays(1L))) {
                                            continueDays++;

                                            maxDays = Math.max(maxDays, continueDays);
                                        } else {
                                            continueDays = 1;
                                        }
                                    }
                                    return maxDays;
                                }
                        )
                ));
    }

    // 6. 找出“共同选课最多”的学生组合 Map<StudentPair, Long>
    // record StudentPair(long first, long second) {}
    // 共同课程数量 DESC
    // first ASC
    // second ASC
    // 保持顺序
    record StudentPair(long first, long second) {
    }

    public static Map<StudentPair, Long> t6(List<Enrollment> enrollments) {

        Map<Long, ArrayList<StudentPair>> courseMap = enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::courseId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    List<Long> students = list.stream()
                                            .map(Enrollment::studentId)
                                            .distinct()
                                            .sorted()
                                            .toList();

                                    ArrayList<StudentPair> studentPairs = new ArrayList<>();
                                    for (int i = 0; i < students.size(); i++) {
                                        for (int j = i + 1; j < students.size(); j++) {
                                            studentPairs.add(new StudentPair(students.get(i), students.get(j)));
                                        }
                                    }

                                    return studentPairs;
                                }
                        )
                ));

        return courseMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(
                        Map.Entry.<StudentPair, Long>comparingByValue()
                                .reversed()
                                .thenComparing(e -> e.getKey().first())
                                .thenComparing(e -> e.getKey().second())
                )
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        )
                );
    }

    // 7. 找出每门课程共同选课学生组合 -> Map<StudentPair, Long>
    public static Map<StudentPair, Long> t7(List<Enrollment> enrollments) {
        return t6(enrollments);
    }

    // 8. 分析哪些课程最经常被同一个学生一起选择 -> Map<CoursePair, Long>
    // 有多少不同学生同时选过这两门课
    // record CoursePair(long first, long second) {}
    // 1. 共现学生数 DESC；
    // 2. courseId1 ASC；
    // 3. courseId2 ASC。
    // 4. 保持顺序
    record CoursePair(long first, long second) {
    }

    public static Map<CoursePair, Long> t8(List<Enrollment> enrollments) {

        Map<Long, ArrayList<CoursePair>> studentCourseMap = enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::studentId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list ->
                                {
                                    List<Long> courses = list.stream()
                                            .map(Enrollment::courseId)
                                            .distinct()
                                            .sorted()
                                            .toList();

                                    ArrayList<CoursePair> coursePairs = new ArrayList<>();
                                    for (int i = 0; i < courses.size(); i++) {
                                        for (int j = i + 1; j < courses.size(); j++) {
                                            coursePairs.add(new CoursePair(courses.get(i), courses.get(j)));
                                        }
                                    }
                                    return coursePairs;
                                }
                        )
                ));

        return studentCourseMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<CoursePair, Long>comparingByValue()
                        .reversed()
                        .thenComparing(e -> e.getKey().first())
                        .thenComparing(e -> e.getKey().second())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}