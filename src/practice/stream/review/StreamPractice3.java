package practice.stream.review;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/18 9:45
 * @describe:
 */
public class StreamPractice3 {

    record ScoreRecord(
            Integer id,
            String className,
            String studentId,
            String studentName,
            String subject,
            String examType,
            int score,
            int fullScore,
            LocalDate examDate,
            boolean absent,
            List<String> tags
    ) {
    }

    public static void main(String[] args) {

        List<ScoreRecord> records = Arrays.asList(

                new ScoreRecord(1, "高一1班", "S001", "张三", "语文", "FINAL", 88, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("主科", "文科")),
                new ScoreRecord(2, "高一1班", "S001", "张三", "数学", "FINAL", 96, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("主科", "理科")),
                new ScoreRecord(3, "高一1班", "S001", "张三", "英语", "FINAL", 91, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("主科", "语言")),
                new ScoreRecord(4, "高一1班", "S001", "张三", "物理", "FINAL", 84, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("理科", "实验")),

                new ScoreRecord(5, "高一1班", "S002", "李四", "语文", "FINAL", 76, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("主科", "文科")),
                new ScoreRecord(6, "高一1班", "S002", "李四", "数学", "FINAL", 81, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("主科", "理科")),
                new ScoreRecord(7, "高一1班", "S002", "李四", "英语", "FINAL", 78, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("主科", "语言")),
                new ScoreRecord(8, "高一1班", "S002", "李四", "物理", "FINAL", 59, 100,
                        LocalDate.of(2026, 7, 1), false, List.of("理科", "实验", "薄弱")),

                new ScoreRecord(9, "高一2班", "S003", "王五", "语文", "FINAL", 92, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("主科", "文科")),
                new ScoreRecord(10, "高一2班", "S003", "王五", "数学", "FINAL", 89, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("主科", "理科")),
                new ScoreRecord(11, "高一2班", "S003", "王五", "英语", "FINAL", 94, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("主科", "语言")),
                new ScoreRecord(12, "高一2班", "S003", "王五", "物理", "FINAL", 90, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("理科", "实验")),

                new ScoreRecord(13, "高一2班", "S004", "赵六", "语文", "FINAL", 85, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("主科", "文科")),
                new ScoreRecord(14, "高一2班", "S004", "赵六", "数学", "FINAL", 93, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("主科", "理科")),
                new ScoreRecord(15, "高一2班", "S004", "赵六", "英语", "FINAL", 88, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("主科", "语言")),
                new ScoreRecord(16, "高一2班", "S004", "赵六", "物理", "FINAL", 86, 100,
                        LocalDate.of(2026, 7, 2), false, List.of("理科", "实验")),

                new ScoreRecord(17, "高一3班", "S005", "钱七", "语文", "FINAL", 95, 100,
                        LocalDate.of(2026, 7, 3), false, List.of("主科", "文科", "优秀")),
                new ScoreRecord(18, "高一3班", "S005", "钱七", "数学", "FINAL", 97, 100,
                        LocalDate.of(2026, 7, 3), false, List.of("主科", "理科", "优秀")),
                new ScoreRecord(19, "高一3班", "S005", "钱七", "英语", "FINAL", 92, 100,
                        LocalDate.of(2026, 7, 3), false, List.of("主科", "语言")),
                new ScoreRecord(20, "高一3班", "S005", "钱七", "物理", "FINAL", 91, 100,
                        LocalDate.of(2026, 7, 3), false, List.of("理科", "实验")),

                new ScoreRecord(21, "高一3班", "S006", "孙八", "语文", "FINAL", 80, 100,
                        LocalDate.of(2026, 7, 3), false, List.of("主科", "文科")),
                new ScoreRecord(22, "高一3班", "S006", "孙八", "数学", "FINAL", 0, 100,
                        LocalDate.of(2026, 7, 3), true, List.of("主科", "理科", "缺考")),
                new ScoreRecord(23, "高一3班", "S006", "孙八", "英语", "FINAL", 74, 100,
                        LocalDate.of(2026, 7, 3), false, List.of("主科", "语言")),
                new ScoreRecord(24, "高一3班", "S006", "孙八", "物理", "FINAL", 67, 100,
                        LocalDate.of(2026, 7, 3), false, List.of("理科", "实验")),

                new ScoreRecord(25, "高一1班", "S001", "张三", "数学", "MID", 90, 100,
                        LocalDate.of(2026, 5, 20), false, List.of("主科", "理科", "期中")),
                new ScoreRecord(26, "高一1班", "S002", "李四", "数学", "MID", 70, 100,
                        LocalDate.of(2026, 5, 20), false, List.of("主科", "理科", "期中")),
                new ScoreRecord(27, "高一2班", "S003", "王五", "英语", "MID", 90, 100,
                        LocalDate.of(2026, 5, 21), false, List.of("主科", "语言", "期中")),
                new ScoreRecord(28, "高一2班", "S004", "赵六", "英语", "MID", 84, 100,
                        LocalDate.of(2026, 5, 21), false, List.of("主科", "语言", "期中")),
                new ScoreRecord(29, "高一3班", "S005", "钱七", "物理", "MID", 88, 100,
                        LocalDate.of(2026, 5, 22), false, List.of("理科", "实验", "期中")),
                new ScoreRecord(30, "高一3班", "S006", "孙八", "物理", "MID", 61, 100,
                        LocalDate.of(2026, 5, 22), false, List.of("理科", "实验", "期中"))
        );

        // 1. 找出每个班级期末总分最高的学生姓名
        // {高一3班=钱七, 高一2班=王五, 高一1班=张三}
        System.out.println(t1(records));
        // 2. 找出每个班级期末平均分前 2 名学生姓名
        // {高一3班=[钱七, 孙八], 高一2班=[王五, 赵六], 高一1班=[张三, 李四]}
        System.out.println(t2(records));
        // 3. 找出每个科目期末平均分最高的班级
        // {物理=高一2班, 数学=高一3班, 语文=高一2班, 英语=高一2班}
        System.out.println(t3(records));
        // 4. 找出每个班级期末不及格科目最多的学生姓名
        // {高一3班=[], 高一2班=[], 高一1班=[李四]}
        System.out.println(t4(records));
        // 5. 计算每个班级期末成绩去掉最高分和最低分后的平均分
        // {高一3班=86.4, 高一2班=89.66666666666667, 高一1班=83.0}
        // 6. 生成每个学生的期末成绩表，科目按成绩降序排列
        // {钱七={数学=97, 语文=95, 英语=92, 物理=91}, 李四={数学=81, 英语=78, 语文=76, 物理=59}, 张三={数学=96, 英语=91, 语文=88, 物理=84}, 王五={英语=94, 语文=92, 物理=90, 数学=89}, 孙八={语文=80, 英语=74, 物理=67}, 赵六={数学=93, 英语=88, 物理=86, 语文=85}}
        // 7. 找出每个科目的期末最高分学生姓名
        // {物理=[钱七], 数学=[钱七], 语文=[钱七], 英语=[王五]}
        // 8. 判断每个学生期末是否全科及格且无缺考
        // {钱七=true, 李四=false, 张三=true, 王五=true, 孙八=false, 赵六=true}
        // 9. 统计每个班级标签出现次数 Top3
        // {高一3班={理科=6, 主科=6, 实验=4}, 高一2班={主科=8, 语言=4, 理科=4}, 高一1班={主科=8, 理科=6, 期中=2}}

    }

    // 1. 找出每个班级期末总分最高的学生姓名
    public static Map<String, String> t1(List<ScoreRecord> records) {

        return records.stream()
                .filter(r -> "FINAL".equals(r.examType()))
                .filter(r -> !r.absent())
                .collect(Collectors.groupingBy(
                        ScoreRecord::className,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .collect(Collectors.groupingBy(
                                                ScoreRecord::studentName,
                                                Collectors.summingInt(ScoreRecord::score)
                                        ))
                                        .entrySet()
                                        .stream()
                                        .max(Map.Entry.comparingByValue())
                                        .map(Map.Entry::getKey)
                                        .orElse("")
                        )
                ));
    }

    // 2. 找出每个班级期末平均分前 2 名学生姓名
    public static Map<String, List<String>> t2(List<ScoreRecord> records) {

        return records.stream()
                .filter(r -> "FINAL".equals(r.examType()))
                .filter(r -> !r.absent())
                .collect(Collectors.groupingBy(
                        ScoreRecord::className,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .collect(Collectors.groupingBy(
                                                ScoreRecord::studentName,
                                                Collectors.averagingInt(ScoreRecord::score)
                                        ))
                                        .entrySet()
                                        .stream()
                                        .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                                        .map(Map.Entry::getKey)
                                        .limit(2)
                                        .toList()
                        )
                ));
    }

    // 3. 找出每个科目期末平均分最高的班级
    public static Map<String, String> t3(List<ScoreRecord> records) {

        return records.stream()
                .filter(r -> "FINAL".equals(r.examType()))
                .filter(r -> !r.absent())
                .collect(Collectors.groupingBy(
                        ScoreRecord::subject,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .collect(Collectors.groupingBy(
                                                ScoreRecord::className,
                                                Collectors.averagingInt(ScoreRecord::score)
                                        ))
                                        .entrySet()
                                        .stream()
                                        .max(Map.Entry.comparingByValue())
                                        .map(Map.Entry::getKey)
                                        .orElse("")
                        )
                ));
    }

    // 4. 找出每个班级期末不及格科目最多的学生姓名
    public static Map<String, List<String>> t4(List<ScoreRecord> records) {

        return records.stream()
                .filter(r -> "FINAL".equals(r.examType()))
                .filter(r -> !r.absent())
                .collect(Collectors.groupingBy(
                        ScoreRecord::className,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Map<String, Long> collect = list.stream()
                                            .filter(r -> r.score() < 60)
                                            .collect(Collectors.groupingBy(
                                                    ScoreRecord::studentName,
                                                    Collectors.counting()
                                            ));

                                    if (collect.isEmpty()) {
                                        return Collections.emptyList();
                                    }

                                    long maxCount = collect.entrySet()
                                            .stream()
                                            .max(Comparator.comparingLong(Map.Entry::getValue))
                                            .map(Map.Entry::getValue)
                                            .orElse(0L);

                                    return collect.entrySet()
                                            .stream()
                                            .filter(entry -> entry.getValue() == maxCount)
                                            .map(Map.Entry::getKey)
                                            .sorted()
                                            .toList();
                                }
                        )
                ));
    }

    // 5. 计算每个班级期末成绩去掉最高分和最低分后的平均分
    // 6. 生成每个学生的期末成绩表，科目按成绩降序排列
    // 7. 找出每个科目的期末最高分学生姓名
    // 8. 判断每个学生期末是否全科及格且无缺考
    // 9. 统计每个班级标签出现次数 Top3
}