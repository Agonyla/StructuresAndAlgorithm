package practice.stream.review;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/10/9 13:50
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
        System.out.println(t3(students, courses, studyRecords));
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
        // 9. 每个老师最受欢迎的课程(选课学生人数最多) -> Map<String, Course>
        System.out.println(t9_1(courses, enrollments));
        System.out.println(t9_2(courses, enrollments));
        // 10. 构建城市到课程类别到学生姓名矩阵 -> Map<String, Map<String, List<String>>>
        System.out.println(t10_1(students, courses, enrollments));
        System.out.println(t10_2(students, courses, enrollments));
        // 11. 构建老师到城市到学生人数矩阵 -> Map<String, Map<String, Long>>
        System.out.println(t11(students, courses, enrollments));
        // 12. 每个课程类别平均最高考试成绩 -> Map<String, Double>
        System.out.println(t12(courses, examResults));
        // 13. 找出每个学生提升最大的课程 -> Map<Long, Long>
        System.out.println(t13(courses, examResults));
        // 14. 学习效率最高的学生 Top 3 -> List<Long>
        System.out.println(t14(examResults, studyRecords));
        // 15. 找出偏科最严重的学生 -> List<Long>
        System.out.println(t15(examResults));
        // 16. 找出每个城市最喜欢的课程类别 -> Map<String, String>
        System.out.println(t16(students, courses, studyRecords));
        // 17. 找出学习时间和考试成绩不匹配的学生课程 -> List<StudentCourse>
        System.out.println(t17(studyRecords, examResults));
        // 18. 基于相似学生的课程推荐 -> Map<Long, List<Long>>
        System.out.println(t18(courses, enrollments));

    }

    // 1. 每个城市学习时间最多的学生 -> Map<String, Student>
    // 总学习时间高的优先
    // 总学习时间相同时，年龄小的优先
    // 年龄也相同时，id 小的优先

    public static Map<String, Student> t1(List<Student> students, List<StudyRecord> studyRecords) {

        Map<Long, Integer> studentStudyMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                                StudyRecord::studentId,
                                Collectors.summingInt(StudyRecord::minutes)
                        )
                );

        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::city,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .filter(stu -> studentStudyMap.containsKey(stu.id()))
                                        .max(Comparator.comparing((Student stu) -> studentStudyMap.getOrDefault(stu.id(), 0))
                                                .thenComparing(Student::age, Comparator.reverseOrder())
                                                .thenComparing(Student::id, Comparator.reverseOrder())
                                        )
                                        .orElse(null)
                        )
                ));
    }

    // 2. 每个课程类别学习时间 Top 2 学生 -> Map<String, List<Long>>
    // 汇总学生在该 category 下所有课程的总学习时间；
    // 每个 category 取学习时间最多的前 2 名
    // 时间相同时 studentId 小的优先

    record CategoryStudent(
            String category,
            long studentId

    ) {
    }

    public static Map<String, List<Long>> t2(List<Course> courses,
                                             List<StudyRecord> studyRecords) {

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<CategoryStudent, Integer> categoryStudentStudyMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                        rec -> new CategoryStudent(
                                courseMap.get(rec.courseId()).category(),
                                rec.studentId()
                        ),
                        Collectors.summingInt(StudyRecord::minutes)
                ));

        return categoryStudentStudyMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().category(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Map.Entry.<CategoryStudent, Integer>comparingByValue().reversed()
                                                .thenComparing(e -> e.getKey().studentId())
                                        )
                                        .map(e -> e.getKey().studentId())
                                        .limit(2)
                                        .toList()
                        )
                ));
    }

    // 3. 找出学习类别最多的学生 -> List<Student>
    // 选了课但是没有 StudyRecord 不算真正学习过
    // 按 studentId ASC

    public static List<Student> t3(List<Student> students,
                                   List<Course> courses,
                                   List<StudyRecord> studyRecords) {

        Map<Long, Student> studentMap = students.stream()
                .collect(Collectors.toMap(
                        Student::id,
                        Function.identity()
                ));

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<Long, Integer> studentCategoryCountMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                                StudyRecord::studentId,
                                Collectors.mapping(rec -> courseMap.get(rec.courseId()).category(),
                                        Collectors.collectingAndThen(
                                                Collectors.toSet(),
                                                Set::size
                                        ))
                        )
                );

        int maxCount = studentCategoryCountMap.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getValue)
                .orElse(0);

        return studentCategoryCountMap.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)
                .sorted(Map.Entry.comparingByKey())
                .map(e -> studentMap.get(e.getKey()))
                .toList();
    }

    // 4. 找出连续学习至少 4 天的学生 -> Set<Long>
    public static Set<Long> t4(List<StudyRecord> studyRecords) {

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

                                    int continuousDay = 1;

                                    for (int i = 1; i < dates.size(); i++) {
                                        if (dates.get(i).equals(dates.get(i - 1).plusDays(1))) {
                                            continuousDay++;
                                        } else {
                                            continuousDay = 1;
                                        }

                                        if (continuousDay >= 4) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        )
                ))
                .entrySet()
                .stream()
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

                                    int continuousDay = 1;
                                    int longestDay = 1;

                                    for (int i = 1; i < dates.size(); i++) {
                                        if (dates.get(i).equals(dates.get(i - 1).plusDays(1))) {
                                            continuousDay++;
                                            longestDay = Math.max(continuousDay, longestDay);
                                        } else {
                                            continuousDay = 1;
                                        }
                                    }
                                    return longestDay;
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

    record StudentPair(
            long first,
            long second
    ) {
    }

    public static Map<StudentPair, Long> t6(List<Enrollment> enrollments) {

        Map<Long, List<StudentPair>> courseStudentPairsMap = enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::courseId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    List<Long> studentId = list.stream()
                                            .map(Enrollment::studentId)
                                            .distinct()
                                            .sorted()
                                            .toList();

                                    List<StudentPair> studentPairs = new ArrayList<>();

                                    for (int i = 0; i < studentId.size(); i++) {
                                        for (int j = i + 1; j < studentId.size(); j++) {
                                            studentPairs.add(new StudentPair(studentId.get(i), studentId.get(j)));
                                        }
                                    }

                                    return studentPairs;
                                }
                        )
                ));

        return courseStudentPairsMap.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<StudentPair, Long>comparingByValue().reversed()
                        .thenComparing(e -> e.getKey().first())
                        .thenComparing(e -> e.getKey().second())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
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
    record CoursePair(
            long first,
            long second) {
    }

    public static Map<CoursePair, Long> t8(List<Enrollment> enrollments) {

        Map<Long, ArrayList<CoursePair>> studentCoursePairMap = enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::studentId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    List<Long> courseId = list.stream()
                                            .map(Enrollment::courseId)
                                            .distinct()
                                            .sorted()
                                            .toList();

                                    ArrayList<CoursePair> coursePairs = new ArrayList<>();

                                    for (int i = 0; i < courseId.size(); i++) {
                                        for (int j = i + 1; j < courseId.size(); j++) {
                                            coursePairs.add(new CoursePair(courseId.get(i), courseId.get(j)));
                                        }
                                    }
                                    return coursePairs;
                                }
                        )
                ));

        return studentCoursePairMap.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<CoursePair, Long>comparingByValue().reversed()
                        .thenComparing(e -> e.getKey().first())
                        .thenComparing(e -> e.getKey().second())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // 9. 每个老师最受欢迎的课程(选课学生人数最多) -> Map<String, Course>
    // 如果选课人数相同：
    // 1. difficulty 高的优先；
    // 2. 仍相同，courseId 小的优先

    record TeacherCourse(
            String teacher,
            long courseId
    ) {
    }

    public static Map<String, Course> t9_1(List<Course> courses, List<Enrollment> enrollments) {

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<TeacherCourse, Integer> teacherCourseCountMap = enrollments.stream()
                .collect(Collectors.groupingBy(
                        e -> new TeacherCourse(
                                courseMap.get(e.courseId()).teacher(),
                                e.courseId()
                        ),
                        Collectors.collectingAndThen(
                                Collectors.mapping(
                                        Enrollment::studentId,
                                        Collectors.toSet()
                                ),
                                Set::size
                        )
                ));

        return teacherCourseCountMap.entrySet().stream()
                .sorted(Map.Entry.<TeacherCourse, Integer>comparingByValue().reversed()
                        .thenComparing(
                                e -> courseMap.get(e.getKey().courseId()).difficulty(),
                                Comparator.reverseOrder()
                        )
                        .thenComparing(e -> e.getKey().courseId())
                )
                .collect(Collectors.toMap(
                        e -> e.getKey().teacher(),
                        e -> courseMap.get(e.getKey().courseId()),
                        (c1, c2) -> c1,
                        LinkedHashMap::new
                ));
    }

    public static Map<String, Course> t9_2(List<Course> courses, List<Enrollment> enrollments) {

        Map<Long, Integer> courseCountMap = enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::courseId,
                        Collectors.collectingAndThen(
                                Collectors.mapping(
                                        Enrollment::studentId,
                                        Collectors.toSet()
                                ),
                                Set::size
                        )
                ));

        return courses.stream()
                .collect(Collectors.groupingBy(
                        Course::teacher,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .max(Comparator.comparing((Course c) -> courseCountMap.getOrDefault(c.id(), 0))
                                                .thenComparing(Course::difficulty)
                                                .thenComparing(Course::id,
                                                        Comparator.reverseOrder()
                                                )
                                        )
                                        .orElse(null)
                        )
                ));
    }

    // 10. 构建城市到课程类别到学生姓名矩阵 -> Map<String, Map<String, List<String>>>
    // Shanghai
    //     Java
    //         Alice
    //         Charlie
    //         Grace
    //
    //     DevOps
    //         Alice
    //         Charlie
    // 一个人在同一类别下只能出现一次
    // 字典序 ASC

    record CityCategoryStudent(
            String city,
            String category,
            String studentName
    ) {
    }

    public static Map<String, Map<String, List<String>>> t10_1(List<Student> students,
                                                               List<Course> courses,
                                                               List<Enrollment> enrollments) {

        Map<Long, Student> studentMap = students.stream()
                .collect(Collectors.toMap(
                        Student::id,
                        Function.identity()
                ));

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        return enrollments.stream()
                .map(e -> new CityCategoryStudent(
                        studentMap.get(e.studentId()).city(),
                        courseMap.get(e.courseId()).category(),
                        studentMap.get(e.studentId()).name()
                ))
                .collect(Collectors.groupingBy(
                        CityCategoryStudent::city,
                        Collectors.groupingBy(
                                CityCategoryStudent::category,
                                Collectors.mapping(
                                        CityCategoryStudent::studentName,
                                        Collectors.collectingAndThen(
                                                Collectors.toCollection(TreeSet::new),
                                                ArrayList::new
                                        )
                                )
                        )
                ));
    }

    /**
     * 全部按字典序排序
     *
     * @param students
     * @param courses
     * @param enrollments
     * @return
     */
    public static Map<String, Map<String, List<String>>> t10_2(List<Student> students,
                                                               List<Course> courses,
                                                               List<Enrollment> enrollments) {

        Map<Long, Student> studentMap = students.stream()
                .collect(Collectors.toMap(
                        Student::id,
                        Function.identity()
                ));

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        return enrollments.stream()
                .map(e -> new CityCategoryStudent(
                        studentMap.get(e.studentId()).city(),
                        courseMap.get(e.courseId()).category(),
                        studentMap.get(e.studentId()).name()
                ))
                .collect(Collectors.groupingBy(
                        CityCategoryStudent::city,
                        TreeMap::new,
                        Collectors.groupingBy(
                                CityCategoryStudent::category,
                                TreeMap::new,
                                Collectors.mapping(
                                        CityCategoryStudent::studentName,
                                        Collectors.collectingAndThen(
                                                Collectors.toCollection(TreeSet::new),
                                                ArrayList::new
                                        )
                                )
                        )
                ));
    }

    // 11. 构建老师到城市到学生人数矩阵 -> Map<String, Map<String, Long>>
    // Tom
    //     Shanghai -> 3
    //     Beijing  -> 2
    //
    // Mike
    //     Shenzhen -> 2
    // 学生只要选过某老师的一门或多门课程，就算该老师的一个学生，不能重复统计学生

    record TeacherCity(
            String teacher,
            String city
    ) {
    }

    public static Map<String, Map<String, Long>> t11(List<Student> students,
                                                     List<Course> courses,
                                                     List<Enrollment> enrollments) {

        Map<Long, Student> studentMap = students.stream()
                .collect(Collectors.toMap(
                        Student::id,
                        Function.identity()
                ));

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        return enrollments.stream()
                .collect(Collectors.groupingBy(
                        e -> courseMap.get(e.courseId()).teacher(),
                        Collectors.groupingBy(
                                e -> studentMap.get(e.studentId()).city(),
                                Collectors.mapping(
                                        Enrollment::studentId,
                                        Collectors.collectingAndThen(
                                                Collectors.toSet(),
                                                set -> (long) set.size()
                                        )
                                )
                        )
                ));

        // return enrollments.stream()
        //         .collect(Collectors.groupingBy(
        //                 e -> courseMap.get(e.courseId()).teacher(),
        //                 Collectors.groupingBy(
        //                         e -> studentMap.get(e.studentId()).city(),
        //                         Collectors.collectingAndThen(
        //                                 Collectors.toList(),
        //                                 list -> list.stream()
        //                                         .map(Enrollment::studentId)
        //                                         .distinct()
        //                                         .count()
        //                         )
        //                 )
        //         ));
    }

    // 12. 每个课程类别平均最高考试成绩 -> Map<String, Double>
    // 同一学生参加了同一课程的多次考试，取最高成绩
    // 某一课程下，需要记录所有学生的最高成绩，而不是该课程的最高成绩

    /**
     * 以下是 记录课程的最高成绩，然后再平均
     */
    // record CategoryCourse(
    //         String category,
    //         long courseId
    // ) {
    //
    // }
    //
    // public static Map<String, Double> t12(List<Course> courses, List<ExamResult> examResults) {
    //
    //     Map<Long, Course> courseMap = courses.stream()
    //             .collect(Collectors.toMap(
    //                     Course::id,
    //                     Function.identity()
    //             ));
    //
    //     Map<CategoryCourse, Integer> categCourseScoreMap = examResults.stream()
    //             .collect(Collectors.toMap(
    //                     e -> new CategoryCourse(
    //                             courseMap.get(e.courseId()).category(),
    //                             e.courseId()
    //                     ),
    //                     ExamResult::score,
    //                     Integer::max
    //             ));
    //
    //     return categCourseScoreMap.entrySet().stream()
    //             .collect(Collectors.groupingBy(
    //                             ccs -> ccs.getKey().category(),
    //                             Collectors.averagingInt(Map.Entry::getValue)
    //                     )
    //             );
    // }

    record CourseStudent(
            long courseId,
            long studentId
    ) {

    }

    public static Map<String, Double> t12(List<Course> courses, List<ExamResult> examResults) {

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<CourseStudent, Integer> courseStudentScoreMap = examResults.stream()
                .collect(Collectors.toMap(
                        e -> new CourseStudent(
                                e.courseId(),
                                e.studentId()
                        ),
                        ExamResult::score,
                        Integer::max
                ));

        return courseStudentScoreMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        css -> courseMap.get(css.getKey().courseId()).category(),
                        Collectors.averagingInt(Map.Entry::getValue)
                ));
    }

    // 13. 找出每个学生提升最大的课程 -> Map<Long, Long>
    // studentId -> improvement 最大的 courseId
    // 第一次成绩 = examDate 最早的一次
    // 最后一次成绩 = examDate 最晚的一次
    // 并列时：
    // 1. difficulty 高的课程优先；
    // 2. 仍相同，courseId 小的优先

    public static Map<Long, Long> t13(List<Course> courses, List<ExamResult> examResults) {

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<CourseStudent, Integer> courseStudentScoreMap = examResults.stream()
                .collect(Collectors.groupingBy(
                        e -> new CourseStudent(
                                e.courseId(),
                                e.studentId()
                        ),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    // 这个不需要，因为必然有一条数据，size==1也不需要，此时getLast和getFirst是同一条
                                    // if (list.isEmpty()) {
                                    //     return 0;
                                    // }

                                    List<ExamResult> sorted = list.stream()
                                            .sorted(Comparator.comparing(ExamResult::examDate))
                                            .toList();

                                    return sorted.getLast().score() - sorted.getFirst().score();
                                }
                        )
                ));

        return courseStudentScoreMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        css -> css.getKey().studentId(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .max(Map.Entry.<CourseStudent, Integer>comparingByValue()
                                                .thenComparing(e -> courseMap.get(e.getKey().courseId()).difficulty())
                                                .thenComparing(
                                                        e -> e.getKey().courseId(),
                                                        Comparator.reverseOrder()
                                                )
                                        )
                                        .map(e -> e.getKey().courseId())
                                        .orElse(null)
                        )
                ));
    }

    // 14. 学习效率最高的学生 Top 3 -> List<Long>
    // 该学生所有课程的最高考试成绩总和 / 总学习小时数
    // 排序规则
    // 1. 效率 DESC
    // 2. 总学习时间 DESC
    // 3. studentId ASC

    public static List<Long> t14(List<ExamResult> examResults, List<StudyRecord> studyRecords) {

        Map<CourseStudent, Integer> courseStudentScoreMap = examResults.stream()
                .collect(Collectors.toMap(
                        e -> new CourseStudent(
                                e.courseId(),
                                e.studentId()
                        ),
                        ExamResult::score,
                        Integer::max
                ));

        // 每个学生的最高考试成绩之和
        Map<Long, Integer> studentScoreMap = courseStudentScoreMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().studentId(),
                        Collectors.summingInt(Map.Entry::getValue)
                ));

        // Map<CourseStudent, Integer> courseStudentStudyMap = studyRecords.stream()
        //         .collect(Collectors.toMap(
        //                 e -> new CourseStudent(
        //                         e.courseId(),
        //                         e.studentId()
        //                 ),
        //                 StudyRecord::minutes,
        //                 Integer::sum
        //         ));
        //
        // Map<Long, Integer> studentStudyMap = courseStudentStudyMap.entrySet().stream()
        //         .collect(Collectors.groupingBy(
        //                 e -> e.getKey().studentId(),
        //                 Collectors.summingInt(Map.Entry::getValue)
        //         ));

        // 可以直接简化成一步
        // 每个学生的学习时长
        Map<Long, Integer> studentStudyMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                        StudyRecord::studentId,
                        Collectors.summingInt(StudyRecord::minutes)
                ));

        return studentStudyMap.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted(Comparator.comparing(
                                        (Map.Entry<Long, Integer> e) -> {
                                            int score = studentScoreMap.getOrDefault(e.getKey(), 0);
                                            int time = e.getValue();
                                            return (double) score / time;
                                        },
                                        Comparator.reverseOrder()
                                )
                                .thenComparing(
                                        Map.Entry::getValue,
                                        Comparator.reverseOrder()
                                )
                                .thenComparing(Map.Entry::getKey)
                )
                .map(Map.Entry::getKey)
                .limit(3)
                .toList();
    }

    // 15. 找出偏科最严重的学生 -> List<Long>
    // 对每个学生：
    // 1. 先取得每门课程的最高考试成绩；
    // 2. 找出其中最大成绩；
    // 3. 找出其中最小成绩；
    // 4. 定义偏科程度：maxScore - minScore
    // 返回偏科程度最大的全部学生
    // studentId ASC
    public static List<Long> t15(List<ExamResult> examResults) {

        Map<CourseStudent, Integer> courseStudentScoreMap = examResults.stream()
                .collect(Collectors.toMap(
                        e -> new CourseStudent(
                                e.courseId(),
                                e.studentId()
                        ),
                        ExamResult::score,
                        Integer::max
                ));

        Map<Long, IntSummaryStatistics> statisticsMap = courseStudentScoreMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().studentId(),
                        Collectors.summarizingInt(Map.Entry::getValue)
                ));

        int maxDiff = statisticsMap.values().stream()
                .mapToInt(intSummaryStatistics -> intSummaryStatistics.getMax() - intSummaryStatistics.getMin())
                .max()
                .orElse(0);

        return statisticsMap.entrySet().stream()
                .filter(e -> e.getValue().getMax() - e.getValue().getMin() == maxDiff)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    // 16. 找出每个城市最喜欢的课程类别 -> Map<String, String>
    // 最喜欢定义：这个城市的学生，在该 category 上的总学习分钟数最高
    // 如果总学习分钟相同：
    // 1. 参与学习的不同学生数量更多者优先；
    // 2. 如果仍相同，category 字典序小者优先

    record CityCategory(
            String city,
            String category
    ) {
    }

    public static Map<String, String> t16(List<Student> students,
                                          List<Course> courses,
                                          List<StudyRecord> studyRecords) {

        Map<Long, Student> studentMap = students.stream()
                .collect(Collectors.toMap(
                        Student::id,
                        Function.identity()
                ));

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<CityCategory, Integer> cityCategoryStudyMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                        sr -> new CityCategory(
                                studentMap.get(sr.studentId()).city(),
                                courseMap.get(sr.courseId()).category()
                        ),
                        Collectors.summingInt(StudyRecord::minutes)
                ));

        Map<CityCategory, Long> studentCount = studyRecords.stream()
                .collect(Collectors.groupingBy(
                        sr -> new CityCategory(
                                studentMap.get(sr.studentId()).city(),
                                courseMap.get(sr.courseId()).category()
                        ),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .map(StudyRecord::studentId)
                                        .distinct()
                                        .count()
                        )
                ));

        return cityCategoryStudyMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().city(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .max(Map.Entry.<CityCategory, Integer>comparingByValue()
                                                .thenComparing(e -> studentCount.get(e.getKey()))
                                                .thenComparing(
                                                        e -> e.getKey().category(),
                                                        Comparator.reverseOrder())
                                        )
                                        .map(e -> e.getKey().category())
                                        .orElse(null)
                        )
                ));
    }

    // 17. 找出学习时间和考试成绩不匹配的学生课程 -> List<StudentCourse>
    // 总学习时间
    // 最高考试成绩
    // 总学习时间 >= 100 分钟
    // 最高成绩 < 85
    // 排序
    // 1. 学习时间 DESC；
    // 2. 成绩 ASC；
    // 3. studentId ASC；
    // 4. courseId ASC

    record StudentCourse(
            long studentId,
            long courseId
    ) {

    }

    public static List<StudentCourse> t17(List<StudyRecord> studyRecords, List<ExamResult> examResults) {

        Map<StudentCourse, Integer> studyMap = studyRecords.stream()
                .collect(Collectors.groupingBy(
                        sr -> new StudentCourse(
                                sr.studentId(),
                                sr.courseId()
                        ),
                        Collectors.summingInt(StudyRecord::minutes)
                ));

        Map<StudentCourse, Integer> scoreMap = examResults.stream()
                .collect(Collectors.toMap(
                        er -> new StudentCourse(
                                er.studentId(),
                                er.courseId()
                        ),
                        ExamResult::score,
                        Integer::max

                ));

        return studyMap.entrySet().stream()
                .filter(e -> e.getValue() >= 100)
                .filter(e -> scoreMap.getOrDefault(e.getKey(), 0) < 85)
                .sorted(Map.Entry.<StudentCourse, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(e -> scoreMap.getOrDefault(e.getKey(), 0))
                        .thenComparing(e -> e.getKey().studentId())
                        .thenComparing(e -> e.getKey().courseId())
                )
                .map(Map.Entry::getKey)
                .toList();
    }

    // 18. 基于相似学生的课程推荐 -> Map<Long, List<Long>>
    // studentId -> recommendedCourseIds
    // 对每个学生，寻找和他“选课最相似”的另一个学生
    // 相似度定义：共同课程数量
    //
    // Alice:
    // A B C
    // Bob:
    // A B D E
    // 两人共同课程：A B
    // 然后给 Alice 推荐Bob 选过但 Alice 没选过的课程：D E
    //
    // 选择最相似学生规则
    // 1. 共同课程数最多；
    // 2. 如果多人并列，选择 studentId 最小的。
    // 推荐课程排序
    // 1. difficulty DESC；
    // 2. courseId ASC。

    public static Map<Long, List<Long>> t18(List<Course> courses, List<Enrollment> enrollments) {

        Map<Long, Course> courseMap = courses.stream()
                .collect(Collectors.toMap(
                        Course::id,
                        Function.identity()
                ));

        Map<Long, Set<Long>> studentCourseMap = enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::studentId,
                        Collectors.mapping(Enrollment::courseId, Collectors.toSet())
                ));

        return studentCourseMap.keySet().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        studentId -> {

                            Set<Long> courseIds = studentCourseMap.get(studentId);

                            Long similarStudentId = studentCourseMap.keySet().stream()
                                    .filter(id -> !id.equals(studentId))
                                    .max(Comparator.comparing(
                                                            (Long otherStudentId) -> courseIds.stream()
                                                                    .filter(courseId -> studentCourseMap.get(otherStudentId).contains(courseId))
                                                                    .count()
                                                    )
                                                    .thenComparing(
                                                            Function.identity(),
                                                            Comparator.reverseOrder()
                                                    )
                                    )
                                    .orElse(null);

                            if (similarStudentId == null) {
                                return List.of();
                            }

                            return studentCourseMap.get(similarStudentId)
                                    .stream()
                                    .filter(courseId -> !courseIds.contains(courseId))
                                    .sorted(Comparator.comparing((Long courseId) -> courseMap.get(courseId).difficulty()).reversed()
                                            .thenComparing(Function.identity())
                                    )
                                    .toList();

                        }
                ));
    }

}