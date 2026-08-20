package practice.stream.review;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/19 15:14
 * @describe:
 */
public class StreamPractice4 {

    record BorrowRecord(
            Integer id,
            String userId,
            String userName,
            String city,
            String bookId,
            String bookName,
            String category,
            String author,
            LocalDate borrowDate,
            LocalDate dueDate,
            LocalDate returnDate,
            boolean vip,
            int renewCount,
            BigDecimal fine,
            List<String> tags
    ) {
    }

    public static void main(String[] args) {

        List<BorrowRecord> records = Arrays.asList(
                new BorrowRecord(1, "U001", "张三", "上海", "B001", "Java核心技术", "编程", "Cay",
                        LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 15), LocalDate.of(2026, 6, 14),
                        true, 1, new BigDecimal("0"), List.of("Java", "后端", "经典")),

                new BorrowRecord(2, "U002", "李四", "北京", "B002", "深入理解JVM", "编程", "周志明",
                        LocalDate.of(2026, 6, 2), LocalDate.of(2026, 6, 16), LocalDate.of(2026, 6, 20),
                        false, 0, new BigDecimal("8"), List.of("Java", "JVM", "进阶")),

                new BorrowRecord(3, "U003", "王五", "广州", "B003", "算法导论", "算法", "CLRS",
                        LocalDate.of(2026, 6, 3), LocalDate.of(2026, 6, 17), null,
                        true, 2, new BigDecimal("0"), List.of("算法", "经典", "难")),

                new BorrowRecord(4, "U004", "赵六", "上海", "B004", "数据结构", "算法", "严蔚敏",
                        LocalDate.of(2026, 6, 4), LocalDate.of(2026, 6, 18), LocalDate.of(2026, 6, 18),
                        false, 0, new BigDecimal("0"), List.of("算法", "基础")),

                new BorrowRecord(5, "U001", "张三", "上海", "B005", "Spring实战", "编程", "Craig",
                        LocalDate.of(2026, 6, 5), LocalDate.of(2026, 6, 19), LocalDate.of(2026, 6, 25),
                        true, 1, new BigDecimal("12"), List.of("Spring", "后端", "Java")),

                new BorrowRecord(6, "U005", "钱七", "深圳", "B006", "百年孤独", "文学", "马尔克斯",
                        LocalDate.of(2026, 6, 6), LocalDate.of(2026, 6, 20), LocalDate.of(2026, 6, 19),
                        true, 0, new BigDecimal("0"), List.of("文学", "经典", "外国文学")),

                new BorrowRecord(7, "U006", "孙八", "北京", "B007", "活着", "文学", "余华",
                        LocalDate.of(2026, 6, 7), LocalDate.of(2026, 6, 21), LocalDate.of(2026, 6, 30),
                        false, 0, new BigDecimal("18"), List.of("文学", "中国文学")),

                new BorrowRecord(8, "U002", "李四", "北京", "B008", "MySQL必知必会", "数据库", "Ben",
                        LocalDate.of(2026, 6, 8), LocalDate.of(2026, 6, 22), LocalDate.of(2026, 6, 21),
                        false, 1, new BigDecimal("0"), List.of("数据库", "MySQL", "基础")),

                new BorrowRecord(9, "U003", "王五", "广州", "B009", "Redis设计与实现", "数据库", "黄健宏",
                        LocalDate.of(2026, 6, 9), LocalDate.of(2026, 6, 23), null,
                        true, 1, new BigDecimal("0"), List.of("Redis", "数据库", "进阶")),

                new BorrowRecord(10, "U007", "周九", "杭州", "B010", "三体", "科幻", "刘慈欣",
                        LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 24), LocalDate.of(2026, 6, 28),
                        true, 0, new BigDecimal("8"), List.of("科幻", "中国文学", "热门")),

                new BorrowRecord(11, "U008", "吴十", "深圳", "B011", "流浪地球", "科幻", "刘慈欣",
                        LocalDate.of(2026, 6, 11), LocalDate.of(2026, 6, 25), LocalDate.of(2026, 6, 24),
                        false, 0, new BigDecimal("0"), List.of("科幻", "短篇", "热门")),

                new BorrowRecord(12, "U004", "赵六", "上海", "B012", "人类简史", "社科", "尤瓦尔",
                        LocalDate.of(2026, 6, 12), LocalDate.of(2026, 6, 26), LocalDate.of(2026, 7, 1),
                        false, 0, new BigDecimal("10"), List.of("社科", "历史", "热门")),

                new BorrowRecord(13, "U005", "钱七", "深圳", "B013", "枪炮、病菌与钢铁", "社科", "戴蒙德",
                        LocalDate.of(2026, 6, 13), LocalDate.of(2026, 6, 27), null,
                        true, 2, new BigDecimal("0"), List.of("社科", "历史", "经典")),

                new BorrowRecord(14, "U006", "孙八", "北京", "B014", "高性能MySQL", "数据库", "Baron",
                        LocalDate.of(2026, 6, 14), LocalDate.of(2026, 6, 28), LocalDate.of(2026, 7, 5),
                        false, 1, new BigDecimal("14"), List.of("数据库", "MySQL", "进阶")),

                new BorrowRecord(15, "U009", "郑十一", "广州", "B015", "Effective Java", "编程", "Joshua",
                        LocalDate.of(2026, 6, 15), LocalDate.of(2026, 6, 29), LocalDate.of(2026, 6, 29),
                        true, 0, new BigDecimal("0"), List.of("Java", "编程", "进阶")),

                new BorrowRecord(16, "U010", "冯十二", "杭州", "B016", "代码整洁之道", "编程", "Robert",
                        LocalDate.of(2026, 6, 16), LocalDate.of(2026, 6, 30), LocalDate.of(2026, 7, 3),
                        false, 0, new BigDecimal("6"), List.of("编程", "代码质量")),

                new BorrowRecord(17, "U001", "张三", "上海", "B017", "机器学习", "AI", "周志华",
                        LocalDate.of(2026, 6, 17), LocalDate.of(2026, 7, 1), null,
                        true, 1, new BigDecimal("0"), List.of("AI", "机器学习", "算法")),

                new BorrowRecord(18, "U002", "李四", "北京", "B018", "深度学习", "AI", "Goodfellow",
                        LocalDate.of(2026, 6, 18), LocalDate.of(2026, 7, 2), LocalDate.of(2026, 7, 8),
                        false, 0, new BigDecimal("12"), List.of("AI", "深度学习", "进阶")),

                new BorrowRecord(19, "U003", "王五", "广州", "B019", "Python数据分析", "AI", "Wes",
                        LocalDate.of(2026, 6, 19), LocalDate.of(2026, 7, 3), LocalDate.of(2026, 7, 2),
                        true, 0, new BigDecimal("0"), List.of("Python", "数据分析", "AI")),

                new BorrowRecord(20, "U004", "赵六", "上海", "B020", "Linux命令行", "运维", "William",
                        LocalDate.of(2026, 6, 20), LocalDate.of(2026, 7, 4), null,
                        false, 1, new BigDecimal("0"), List.of("Linux", "运维", "基础"))
        );

        // 1. 查询所有 VIP 用户的借阅记录
        System.out.println(t1(records));
        // 2. 按城市统计借阅次数
        System.out.println(t2(records));
        // 3. 查询所有未归还的图书名称
        System.out.println(t3(records));
        // 4. 找出每个城市罚金最高的用户姓名，若并列返回全部
        System.out.println(t4(records));
        // 5. 按图书分类统计总罚金，并按罚金降序排序
        System.out.println(t5(records));
        // 6. 查询所有罚金大于 0 的记录，并按罚金降序排序
        System.out.println(t6(records));
        // 7. 找出每个用户借阅次数最多的图书分类，若并列返回全部分类
        System.out.println(t7(records));
        // 8. 按分类分组，收集每个分类下所有图书名称，要求去重
        System.out.println(t8(records));
        // 9. 统计每个城市最受欢迎的前 2 个图书分类
        System.out.println(t9(records));
        // 10. 判断是否存在逾期未归还记录
        System.out.println(t10(records));
        // 11. 按用户统计借阅总次数，并按次数降序排序
        System.out.println(t11(records));
        // 12. 找出每个分类借阅时长最长的一条记录
        System.out.println(t12(records));
        // 13. 统计每个标签出现次数 Top5
        System.out.println(t13(records));
        // 14. 获取所有城市名称，去重并排序
        System.out.println(t14(records));
        // 15. 按用户生成借阅书单，书名按借阅日期倒序排列
        System.out.println(t15(records));
        // 16. 按是否 VIP 分区，统计两类用户的总罚金
        System.out.println(t16(records));
        // 17. 找出每个作者被借阅次数最多的城市，若并列返回全部城市
        System.out.println(t17(records));
        // 18. 查询续借次数大于 0 的用户名，去重
        System.out.println(t18(records));
        // 19. 统计每个分类的平均借阅天数，未归还的按 2026-07-10 作为当前日期计算
        System.out.println(t19(records));
        // 20. 找出总罚金最高的前 3 个用户，并保留排序
        System.out.println(t20(records));
    }

    // 1. 查询所有 VIP 用户的借阅记录
    public static List<BorrowRecord> t1(List<BorrowRecord> records) {

        return records.stream()
                .filter(BorrowRecord::vip)
                .toList();
    }

    // 2. 按城市统计借阅次数
    public static Map<String, Long> t2(List<BorrowRecord> records) {

        // Map<String, Integer> collect = records.stream()
        //         .collect(Collectors.toMap(
        //                 BorrowRecord::city,
        //                 r -> 1,
        //                 Integer::sum
        //         ));

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::city,
                        Collectors.counting()
                ));
    }

    // 3. 查询所有未归还的图书名称
    public static List<String> t3(List<BorrowRecord> records) {

        return records.stream()
                .filter(r -> r.returnDate() == null)
                .map(BorrowRecord::bookName)
                .toList();
    }

    // 4. 找出每个城市罚金最高的用户姓名，若并列返回全部
    public static Map<String, List<String>> t4(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::city,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    BigDecimal maxFine = list.stream()
                                            .max(Comparator.comparing(BorrowRecord::fine))
                                            .map(BorrowRecord::fine)
                                            .orElse(BigDecimal.ONE);

                                    if (maxFine.compareTo(BigDecimal.ZERO) == 0) {
                                        return Collections.emptyList();
                                    }

                                    return list.stream()
                                            .filter(r -> maxFine.compareTo(r.fine()) == 0)
                                            .map(BorrowRecord::userName)
                                            .distinct()
                                            .toList();
                                }
                        )
                ));
    }

    // 5. 按图书分类统计总罚金，并按罚金降序排序
    public static Map<String, BigDecimal> t5(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::category,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                BorrowRecord::fine,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed()
                        .thenComparing(Map.Entry::getKey))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // 6. 查询所有罚金大于 0 的记录，并按罚金降序排序
    public static List<BorrowRecord> t6(List<BorrowRecord> records) {

        return records.stream()
                .filter(r -> r.fine().compareTo(BigDecimal.ZERO) > 0)
                .sorted(Comparator.comparing(BorrowRecord::fine).reversed())
                .toList();
    }

    // 7. 找出每个用户借阅次数最多的图书分类，若并列返回全部分类
    public static Map<String, List<String>> t7(List<BorrowRecord> records) {

        // return records.stream()
        //         .collect(Collectors.groupingBy(
        //                 BorrowRecord::userName,
        //                 Collectors.collectingAndThen(
        //                         Collectors.toList(),
        //                         list -> {
        //                             Map<String, Integer> map = list.stream()
        //                                     .collect(Collectors.toMap(
        //                                             BorrowRecord::category,
        //                                             r -> 1,
        //                                             Integer::sum
        //                                     ));
        //
        //                             int maxCount = map.entrySet()
        //                                     .stream()
        //                                     .max(Map.Entry.comparingByValue())
        //                                     .map(Map.Entry::getValue)
        //                                     .orElse(0);
        //
        //                             return map.entrySet()
        //                                     .stream()
        //                                     .filter(e -> e.getValue() == maxCount)
        //                                     .map(Map.Entry::getKey)
        //                                     .toList();
        //                         }
        //                 )
        //         ));

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::userName,
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        BorrowRecord::category,
                                        r -> 1,
                                        Integer::sum
                                ),
                                map -> {
                                    int maxCount = map.entrySet()
                                            .stream()
                                            .max(Map.Entry.comparingByValue())
                                            .map(Map.Entry::getValue)
                                            .orElse(0);

                                    return map.entrySet()
                                            .stream()
                                            .filter(e -> e.getValue() == maxCount)
                                            .map(Map.Entry::getKey)
                                            .toList();
                                }
                        )
                ));
    }

    // 8. 按分类分组，收集每个分类下所有图书名称，要求去重
    public static Map<String, Set<String>> t8(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::category,
                        Collectors.mapping(
                                BorrowRecord::bookName,
                                Collectors.toCollection(TreeSet::new)
                        )
                ));
    }

    // 9. 统计每个城市最受欢迎的前 2 个图书分类
    public static Map<String, List<String>> t9(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::city,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .collect(Collectors.groupingBy(
                                                BorrowRecord::category,
                                                Collectors.counting()
                                        ))
                                        .entrySet()
                                        .stream()
                                        .sorted(
                                                Map.Entry.<String, Long>comparingByValue()
                                                        .reversed()
                                                        .thenComparing(Map.Entry::getKey)
                                        )
                                        .limit(2)
                                        .map(Map.Entry::getKey)
                                        .toList()
                        )
                ));
    }

    // 10. 判断是否存在逾期未归还记录
    // returnDate == null && dueDate < 2026-07-10
    // LocalDate currentDate = LocalDate.of(2026, 7, 10);
    public static Boolean t10(List<BorrowRecord> records) {

        LocalDate currentDate = LocalDate.of(2026, 7, 10);

        return records.stream()
                .anyMatch(r -> r.returnDate() == null
                        && r.dueDate().isBefore(currentDate));
    }

    // 11. 按用户统计借阅总次数，并按次数降序排序
    // 按借阅次数降序
    // 次数相同按用户名升序
    // 保留排序后的顺序
    public static Map<String, Long> t11(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::userName,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(
                        Map.Entry.<String, Long>comparingByValue()
                                .reversed()
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // 12. 找出每个分类借阅时长最长的一条记录
    // LocalDate currentDate = LocalDate.of(2026, 7, 10);
    public static Map<String, BorrowRecord> t12(List<BorrowRecord> records) {

        LocalDate currentDate = LocalDate.of(2026, 7, 10);

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::category,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .max(Comparator.comparingLong(r ->
                                                ChronoUnit.DAYS.between(
                                                        r.borrowDate(),
                                                        r.returnDate() == null ? currentDate : r.returnDate()
                                                )
                                        ))
                                        .orElse(null)
                        )
                ));
    }

    // 13. 统计每个标签出现次数 Top5
    // 按次数降序
    // 次数相同按标签名升序
    public static Map<String, Long> t13(List<BorrowRecord> records) {

        return records.stream()
                .flatMap(
                        r -> r.tags().stream()
                )
                .collect(Collectors.toMap(
                        Function.identity(),
                        a -> 1L,
                        Long::sum
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // 14. 获取所有城市名称，去重并排序
    public static Set<String> t14(List<BorrowRecord> records) {
        return records.stream()
                .map(BorrowRecord::city)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    // 15. 按用户生成借阅书单，书名按借阅日期倒序排列
    public static Map<String, List<String>> t15(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::userName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(
                                                Comparator.comparing(BorrowRecord::borrowDate)
                                                        .reversed()
                                        )
                                        .map(BorrowRecord::bookName)
                                        .toList()
                        )
                ));
    }

    // 16. 按是否 VIP 分区，统计两类用户的总罚金
    public static Map<Boolean, BigDecimal> t16(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.partitioningBy(
                        BorrowRecord::vip,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                BorrowRecord::fine,
                                BigDecimal::add
                        )
                ));
    }

    // 17. 找出每个作者被借阅次数最多的城市，若并列返回全部城市
    public static Map<String, List<String>> t17(List<BorrowRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::author,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Map<String, Integer> map = list.stream()
                                            .collect(Collectors.toMap(
                                                    BorrowRecord::city,
                                                    t -> 1,
                                                    Integer::sum
                                            ));

                                    int maxCount = map.entrySet()
                                            .stream()
                                            .max(Map.Entry.comparingByValue())
                                            .map(Map.Entry::getValue)
                                            .orElse(0);

                                    // int maxCount = map.values()
                                    //         .stream()
                                    //         .mapToInt(Integer::intValue)
                                    //         .max()
                                    //         .orElse(0);

                                    return map.entrySet()
                                            .stream()
                                            .filter(e -> e.getValue() == maxCount)
                                            .map(Map.Entry::getKey)
                                            .collect(Collectors.toList());
                                }
                        )
                ));
    }

    // 18. 查询续借次数大于 0 的用户名，去重
    public static Set<String> t18(List<BorrowRecord> records) {

        return records.stream()
                .filter(r -> r.renewCount() > 0)
                .map(BorrowRecord::userName)
                .collect(Collectors.toSet());
    }

    // 19. 统计每个分类的平均借阅天数，未归还的按 2026-07-10 作为当前日期计算
    public static Map<String, Double> t19(List<BorrowRecord> records) {

        LocalDate currentDate = LocalDate.of(2026, 7, 10);

        // return records.stream()
        //         .collect(Collectors.groupingBy(
        //                 BorrowRecord::category,
        //                 Collectors.collectingAndThen(
        //                         Collectors.toList(),
        //                         list -> list.stream()
        //                                 .mapToLong(r -> {
        //
        //                                     LocalDate returnDate = r.returnDate() == null ? currentDate : r.returnDate();
        //
        //                                     return ChronoUnit.DAYS.between(r.borrowDate(), returnDate);
        //                                 })
        //                                 .average()
        //                                 .orElse(0.0)
        //                 )
        //         ));

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::category,
                        Collectors.averagingLong(r ->
                                ChronoUnit.DAYS.between(
                                        r.borrowDate(),
                                        r.returnDate() == null ? currentDate : r.returnDate()
                                )
                        )
                ));
    }

    // 20. 找出总罚金最高的前 3 个用户，并保留排序
    public static Map<String, BigDecimal> t20(List<BorrowRecord> records) {

        // return records.stream()
        //         .collect(Collectors.groupingBy(
        //                 BorrowRecord::userName,
        //                 Collectors.collectingAndThen(
        //                         Collectors.toList(),
        //                         list -> list.stream()
        //                                 .map(BorrowRecord::fine)
        //                                 .reduce(BigDecimal.ZERO, BigDecimal::add)
        //                 )
        //         ))
        //         .entrySet()
        //         .stream()
        //         .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
        //         .limit(3)
        //         .collect(Collectors.toMap(
        //                 Map.Entry::getKey,
        //                 Map.Entry::getValue,
        //                 (a, b) -> a,
        //                 LinkedHashMap::new
        //         ));

        return records.stream()
                .collect(Collectors.groupingBy(
                        BorrowRecord::userName,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                BorrowRecord::fine,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey())
                )
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}