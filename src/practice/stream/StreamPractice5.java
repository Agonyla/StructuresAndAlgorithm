package practice.stream;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/6 13:23
 * @describe:
 */
public class StreamPractice5 {

    record DeliveryOrder(
            Integer id,
            String userId,
            String userName,
            String city,
            String merchantId,
            String merchantName,
            String category,
            String riderId,
            String riderName,
            LocalDateTime orderTime,
            LocalDateTime deliveredTime,
            String status,
            BigDecimal amount,
            BigDecimal deliveryFee,
            BigDecimal coupon,
            int rating,
            boolean delayed,
            List<String> tags
    ) {
    }

    public static void main(String[] args) {

        List<DeliveryOrder> orders = Arrays.asList(
                new DeliveryOrder(1, "U001", "张三", "上海", "M001", "川味小馆", "川菜",
                        "R001", "骑手A",
                        LocalDateTime.of(2026, 7, 1, 11, 20),
                        LocalDateTime.of(2026, 7, 1, 11, 55),
                        "COMPLETED", new BigDecimal("86.50"), new BigDecimal("5.00"), new BigDecimal("10.00"),
                        5, false, List.of("午餐", "川菜", "辣")),

                new DeliveryOrder(2, "U002", "李四", "北京", "M002", "麦香堡", "快餐",
                        "R002", "骑手B",
                        LocalDateTime.of(2026, 7, 1, 12, 10),
                        LocalDateTime.of(2026, 7, 1, 12, 50),
                        "COMPLETED", new BigDecimal("42.00"), new BigDecimal("4.00"), new BigDecimal("5.00"),
                        4, true, List.of("午餐", "快餐", "汉堡")),

                new DeliveryOrder(3, "U003", "王五", "广州", "M003", "粥粉面馆", "粤菜",
                        "R003", "骑手C",
                        LocalDateTime.of(2026, 7, 1, 8, 5),
                        LocalDateTime.of(2026, 7, 1, 8, 28),
                        "COMPLETED", new BigDecimal("28.00"), new BigDecimal("3.00"), new BigDecimal("0.00"),
                        5, false, List.of("早餐", "粤菜", "清淡")),

                new DeliveryOrder(4, "U004", "赵六", "深圳", "M004", "轻食主义", "轻食",
                        "R004", "骑手D",
                        LocalDateTime.of(2026, 7, 1, 18, 30),
                        LocalDateTime.of(2026, 7, 1, 19, 5),
                        "COMPLETED", new BigDecimal("58.00"), new BigDecimal("6.00"), new BigDecimal("8.00"),
                        5, false, List.of("晚餐", "轻食", "健康")),

                new DeliveryOrder(5, "U001", "张三", "上海", "M005", "东北饺子馆", "面食",
                        "R001", "骑手A",
                        LocalDateTime.of(2026, 7, 2, 19, 10),
                        LocalDateTime.of(2026, 7, 2, 19, 58),
                        "COMPLETED", new BigDecimal("65.00"), new BigDecimal("5.00"), new BigDecimal("0.00"),
                        3, true, List.of("晚餐", "面食", "饺子")),

                new DeliveryOrder(6, "U005", "钱七", "杭州", "M006", "日式便当", "日料",
                        "R005", "骑手E",
                        LocalDateTime.of(2026, 7, 2, 12, 0),
                        LocalDateTime.of(2026, 7, 2, 12, 32),
                        "COMPLETED", new BigDecimal("72.00"), new BigDecimal("5.00"), new BigDecimal("12.00"),
                        5, false, List.of("午餐", "日料", "便当")),

                new DeliveryOrder(7, "U006", "孙八", "北京", "M007", "麻辣香锅", "川菜",
                        "R006", "骑手F",
                        LocalDateTime.of(2026, 7, 2, 18, 15),
                        LocalDateTime.of(2026, 7, 2, 19, 10),
                        "COMPLETED", new BigDecimal("96.00"), new BigDecimal("6.00"), new BigDecimal("15.00"),
                        2, true, List.of("晚餐", "川菜", "辣", "多人餐")),

                new DeliveryOrder(8, "U007", "周九", "广州", "M008", "椰子鸡", "粤菜",
                        "R003", "骑手C",
                        LocalDateTime.of(2026, 7, 2, 20, 5),
                        null,
                        "CANCELED", new BigDecimal("138.00"), new BigDecimal("8.00"), new BigDecimal("20.00"),
                        0, false, List.of("晚餐", "粤菜", "取消")),

                new DeliveryOrder(9, "U008", "吴十", "深圳", "M009", "奶茶星球", "饮品",
                        "R004", "骑手D",
                        LocalDateTime.of(2026, 7, 3, 15, 20),
                        LocalDateTime.of(2026, 7, 3, 15, 45),
                        "COMPLETED", new BigDecimal("36.00"), new BigDecimal("3.00"), new BigDecimal("4.00"),
                        4, false, List.of("下午茶", "饮品", "奶茶")),

                new DeliveryOrder(10, "U002", "李四", "北京", "M010", "咖啡工坊", "饮品",
                        "R002", "骑手B",
                        LocalDateTime.of(2026, 7, 3, 9, 15),
                        LocalDateTime.of(2026, 7, 3, 9, 42),
                        "COMPLETED", new BigDecimal("32.00"), new BigDecimal("4.00"), new BigDecimal("0.00"),
                        5, false, List.of("早餐", "饮品", "咖啡")),

                new DeliveryOrder(11, "U009", "郑十一", "上海", "M011", "韩式炸鸡", "韩餐",
                        "R007", "骑手G",
                        LocalDateTime.of(2026, 7, 3, 21, 10),
                        LocalDateTime.of(2026, 7, 3, 22, 5),
                        "COMPLETED", new BigDecimal("118.00"), new BigDecimal("7.00"), new BigDecimal("18.00"),
                        4, true, List.of("夜宵", "韩餐", "炸鸡")),

                new DeliveryOrder(12, "U010", "冯十二", "杭州", "M012", "沙县小吃", "快餐",
                        "R005", "骑手E",
                        LocalDateTime.of(2026, 7, 4, 11, 40),
                        LocalDateTime.of(2026, 7, 4, 12, 5),
                        "COMPLETED", new BigDecimal("24.00"), new BigDecimal("3.00"), new BigDecimal("0.00"),
                        5, false, List.of("午餐", "快餐", "实惠")),

                new DeliveryOrder(13, "U003", "王五", "广州", "M013", "披萨之家", "西餐",
                        "R008", "骑手H",
                        LocalDateTime.of(2026, 7, 4, 18, 0),
                        LocalDateTime.of(2026, 7, 4, 18, 50),
                        "COMPLETED", new BigDecimal("108.00"), new BigDecimal("6.00"), new BigDecimal("10.00"),
                        4, true, List.of("晚餐", "西餐", "披萨")),

                new DeliveryOrder(14, "U004", "赵六", "深圳", "M014", "湘味厨房", "湘菜",
                        "R009", "骑手I",
                        LocalDateTime.of(2026, 7, 4, 19, 20),
                        LocalDateTime.of(2026, 7, 4, 20, 0),
                        "REFUNDED", new BigDecimal("88.00"), new BigDecimal("5.00"), new BigDecimal("8.00"),
                        1, true, List.of("晚餐", "湘菜", "退款")),

                new DeliveryOrder(15, "U005", "钱七", "杭州", "M015", "水果捞", "甜品",
                        "R005", "骑手E",
                        LocalDateTime.of(2026, 7, 5, 14, 30),
                        LocalDateTime.of(2026, 7, 5, 14, 55),
                        "COMPLETED", new BigDecimal("46.00"), new BigDecimal("3.00"), new BigDecimal("6.00"),
                        5, false, List.of("下午茶", "甜品", "水果")),

                new DeliveryOrder(16, "U006", "孙八", "北京", "M002", "麦香堡", "快餐",
                        "R006", "骑手F",
                        LocalDateTime.of(2026, 7, 5, 12, 5),
                        LocalDateTime.of(2026, 7, 5, 12, 35),
                        "COMPLETED", new BigDecimal("39.00"), new BigDecimal("4.00"), new BigDecimal("5.00"),
                        4, false, List.of("午餐", "快餐", "汉堡")),

                new DeliveryOrder(17, "U007", "周九", "广州", "M016", "螺蛳粉", "粉面",
                        "R008", "骑手H",
                        LocalDateTime.of(2026, 7, 5, 20, 30),
                        LocalDateTime.of(2026, 7, 5, 21, 8),
                        "COMPLETED", new BigDecimal("34.00"), new BigDecimal("4.00"), new BigDecimal("0.00"),
                        3, false, List.of("晚餐", "粉面", "重口味")),

                new DeliveryOrder(18, "U008", "吴十", "深圳", "M017", "烧烤摊", "烧烤",
                        "R009", "骑手I",
                        LocalDateTime.of(2026, 7, 5, 22, 15),
                        LocalDateTime.of(2026, 7, 5, 23, 5),
                        "COMPLETED", new BigDecimal("126.00"), new BigDecimal("8.00"), new BigDecimal("12.00"),
                        4, true, List.of("夜宵", "烧烤", "多人餐")),

                new DeliveryOrder(19, "U009", "郑十一", "上海", "M018", "鲜榨果汁", "饮品",
                        "R007", "骑手G",
                        LocalDateTime.of(2026, 7, 6, 16, 0),
                        LocalDateTime.of(2026, 7, 6, 16, 22),
                        "COMPLETED", new BigDecimal("29.00"), new BigDecimal("3.00"), new BigDecimal("0.00"),
                        5, false, List.of("下午茶", "饮品", "果汁")),

                new DeliveryOrder(20, "U010", "冯十二", "杭州", "M019", "兰州拉面", "面食",
                        "R010", "骑手J",
                        LocalDateTime.of(2026, 7, 6, 11, 50),
                        LocalDateTime.of(2026, 7, 6, 12, 25),
                        "COMPLETED", new BigDecimal("31.00"), new BigDecimal("3.00"), new BigDecimal("0.00"),
                        4, false, List.of("午餐", "面食", "拉面")),

                new DeliveryOrder(21, "U001", "张三", "上海", "M020", "精品寿司", "日料",
                        "R001", "骑手A",
                        LocalDateTime.of(2026, 7, 6, 18, 20),
                        LocalDateTime.of(2026, 7, 6, 18, 58),
                        "COMPLETED", new BigDecimal("156.00"), new BigDecimal("8.00"), new BigDecimal("20.00"),
                        5, false, List.of("晚餐", "日料", "寿司")),

                new DeliveryOrder(22, "U002", "李四", "北京", "M021", "凉皮肉夹馍", "小吃",
                        "R002", "骑手B",
                        LocalDateTime.of(2026, 7, 6, 18, 45),
                        null,
                        "DELIVERING", new BigDecimal("38.00"), new BigDecimal("4.00"), new BigDecimal("0.00"),
                        0, false, List.of("晚餐", "小吃", "配送中")),

                new DeliveryOrder(23, "U003", "王五", "广州", "M022", "甜品站", "甜品",
                        "R008", "骑手H",
                        LocalDateTime.of(2026, 7, 6, 15, 15),
                        LocalDateTime.of(2026, 7, 6, 15, 48),
                        "COMPLETED", new BigDecimal("52.00"), new BigDecimal("3.00"), new BigDecimal("5.00"),
                        5, false, List.of("下午茶", "甜品", "蛋糕")),

                new DeliveryOrder(24, "U004", "赵六", "深圳", "M023", "潮汕牛肉火锅", "火锅",
                        "R009", "骑手I",
                        LocalDateTime.of(2026, 7, 6, 19, 0),
                        LocalDateTime.of(2026, 7, 6, 20, 5),
                        "COMPLETED", new BigDecimal("188.00"), new BigDecimal("10.00"), new BigDecimal("30.00"),
                        5, true, List.of("晚餐", "火锅", "多人餐"))
        );

        // 查询所有已完成订单
        // 按城市统计订单数量
        // 获取所有订单金额大于 100 的商家名称，要求去重
        // 找出每个城市订单金额最高的用户姓名，若并列返回全部
        // 按餐饮分类统计已完成订单总金额，并按金额降序排序
        // 判断是否存在差评订单，评分小于等于 2 视为差评
        // 找出每个用户消费金额最高的餐饮分类，若并列返回全部分类
        // 按城市分组，收集每个城市出现过的商家名称，要求去重并排序
        // 统计每个骑手配送完成订单的平均配送时长
        // 查询所有延迟订单的订单编号
        // 按商家统计完成订单数量，并按数量降序排序
        // 找出每个餐饮分类评分最高的订单，若评分相同取金额更高的订单
        // 统计所有订单标签出现次数 Top5
        // 获取所有城市名称，去重并按自然顺序排序
        // 按用户生成已完成订单商家列表，按下单时间倒序排列
        // 按是否延迟分区，统计两类订单的总配送费
        // 找出每个商家订单最多的城市，若并列返回全部城市
        // 查询使用优惠券金额大于 0 的用户名，去重
        // 统计每个城市已完成订单平均评分，按平均评分降序排序
        // 找出总消费金额最高的前 3 个用户，并保留排序
    }

    // 查询所有已完成订单
    public static List<DeliveryOrder> t1(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> "COMPLETED".equals(o.status()))
                .toList();
    }

    // 按城市统计订单数量
    public static Map<String, Long> t2(List<DeliveryOrder> orders) {

        return orders.stream()
                .collect(Collectors.groupingBy(
                        DeliveryOrder::city,
                        Collectors.counting()
                ));
    }

    // 获取所有订单金额大于 100 的商家名称，要求去重
    public static Set<String> t3(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> o.amount().compareTo(BigDecimal.valueOf(100)) > 0)
                .map(DeliveryOrder::merchantName)
                .collect(Collectors.toSet());
    }

    // 找出每个城市订单金额最高的用户姓名，若并列返回全部
    public static Map<String, List<String>> t4(List<DeliveryOrder> orders) {

        return orders.stream()
                .collect(Collectors.groupingBy(
                                DeliveryOrder::city,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> {
                                            BigDecimal maxAmount = list.stream()
                                                    .max(Comparator.comparing(DeliveryOrder::amount))
                                                    .map(DeliveryOrder::amount)
                                                    .orElse(BigDecimal.ZERO);

                                            return list.stream()
                                                    .filter(r -> r.amount().compareTo(maxAmount) == 0)
                                                    .map(DeliveryOrder::userName)
                                                    .distinct()
                                                    .toList();
                                        }
                                )
                        )
                );
    }

    // 按餐饮分类统计已完成订单总金额，并按金额降序排序
    public static Map<String, BigDecimal> t5(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> "COMPLETED".equals(o.status()))
                .collect(Collectors.groupingBy(
                        DeliveryOrder::category,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                DeliveryOrder::amount,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        )
                );
    }

    // 判断是否存在差评订单，评分小于等于 2 视为差评
    public static boolean t6(List<DeliveryOrder> orders) {

        return orders.stream()
                .anyMatch(o -> o.rating() <= 2);
    }

    // 找出每个用户消费金额最高的餐饮分类，若并列返回全部分类
    public static Map<String, List<String>> t7(List<DeliveryOrder> orders) {

        // 单笔消费最高的餐饮分类
        // return orders.stream()
        //         .collect(Collectors.groupingBy(
        //                 DeliveryOrder::userName,
        //                 Collectors.collectingAndThen(
        //                         Collectors.toList(),
        //                         list -> {
        //                             BigDecimal maxAmount = list.stream()
        //                                     .max(Comparator.comparing(DeliveryOrder::amount))
        //                                     .map(DeliveryOrder::amount)
        //                                     .orElse(BigDecimal.ZERO);
        //
        //                             return list.stream()
        //                                     .filter(o -> o.amount().compareTo(maxAmount) == 0)
        //                                     .map(DeliveryOrder::category)
        //                                     .distinct()
        //                                     .toList();
        //                         }
        //                 )
        //         ));

        // 总消费最高的餐饮分类
        return orders.stream()
                .collect(Collectors.groupingBy(
                        DeliveryOrder::userName,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        DeliveryOrder::category,
                                        Collectors.reducing(
                                                BigDecimal.ZERO,
                                                DeliveryOrder::amount,
                                                BigDecimal::add
                                        )
                                ),
                                map -> {
                                    BigDecimal maxAmount = map.entrySet()
                                            .stream()
                                            .max(Map.Entry.comparingByValue())
                                            .map(Map.Entry::getValue)
                                            .orElse(BigDecimal.ZERO);

                                    return map.entrySet()
                                            .stream()
                                            .filter(e -> e.getValue().compareTo(BigDecimal.ZERO) == 0)
                                            .map(Map.Entry::getKey)
                                            .toList();

                                }
                        )
                ));
    }

    // 按城市分组，收集每个城市出现过的商家名称，要求去重并排序
    public static Map<String, Set<String>> t8(List<DeliveryOrder> orders) {

        // return orders.stream()
        //         .collect(Collectors.groupingBy(
        //                 DeliveryOrder::city,
        //                 Collectors.collectingAndThen(
        //                         Collectors.toList(),
        //                         list -> list.stream()
        //                                 .map(DeliveryOrder::merchantName)
        //                                 .collect(Collectors.toSet())
        //                 )
        //         ));

        return orders.stream()
                .collect(Collectors.groupingBy(
                        DeliveryOrder::city,
                        Collectors.mapping(
                                DeliveryOrder::merchantName,
                                Collectors.toCollection(TreeSet::new)
                        )
                ));
    }

    // 统计每个骑手配送完成订单的平均配送时长
    public static Map<String, Double> t9(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> "COMPLETED".equals(o.status()))
                .collect(Collectors.groupingBy(
                        DeliveryOrder::riderName,
                        Collectors.averagingLong(
                                o -> ChronoUnit.MINUTES.between(
                                        o.orderTime(),
                                        o.deliveredTime()
                                )

                        )
                ));
    }

    // 查询所有延迟订单的订单编号
    public static List<Integer> t10(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(DeliveryOrder::delayed)
                .map(DeliveryOrder::id)
                .toList();
    }

    // 按商家统计完成订单数量，并按数量降序排序
    public static Map<String, Long> t11(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> "COMPLETED".equals(o.status()))
                .collect(Collectors.groupingBy(
                        DeliveryOrder::merchantName,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

    }

    // 找出每个餐饮分类评分最高的订单，若评分相同取金额更高的订单
    public static Map<String, DeliveryOrder> t12(List<DeliveryOrder> orders) {

        // return orders.stream()
        //         .collect(Collectors.groupingBy(
        //                 DeliveryOrder::category,
        //                 Collectors.collectingAndThen(
        //                         Collectors.maxBy(Comparator.comparing(DeliveryOrder::rating)
        //                                 .thenComparing(DeliveryOrder::amount)),
        //                         Optional::get
        //                 )
        //         ));

        return orders.stream()
                .collect(Collectors.toMap(
                        DeliveryOrder::category,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(DeliveryOrder::rating)
                                .thenComparing(DeliveryOrder::amount))
                ));
    }

    // 统计所有订单标签出现次数 Top5
    public static Map<String, Integer> t13(List<DeliveryOrder> orders) {

        return orders.stream()
                .flatMap(o -> o.tags().stream())
                .collect(Collectors.toMap(
                        Function.identity(),
                        s -> 1,
                        Integer::sum
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        // return orders.stream()
        //         .flatMap(o -> o.tags().stream())
        //         .collect(Collectors.groupingBy(
        //                 Function.identity(),
        //                 Collectors.counting()
        //         ))
        //         .entrySet()
        //         .stream()
        //         .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        //         .limit(5)
        //         .collect(Collectors.toMap(
        //                 Map.Entry::getKey,
        //                 Map.Entry::getValue,
        //                 (a, b) -> a,
        //                 LinkedHashMap::new
        //         ));

    }

    // 获取所有城市名称，去重并按自然顺序排序
    public static Set<String> t14(List<DeliveryOrder> orders) {

        return orders.stream()
                .map(DeliveryOrder::city)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    // 按用户生成已完成订单商家列表，按下单时间倒序排列
    public static Map<String, List<String>> t15(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> "COMPLETED".equals(o.status()))
                .collect(Collectors.groupingBy(
                        DeliveryOrder::userName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(DeliveryOrder::orderTime).reversed())
                                        .map(DeliveryOrder::merchantName)
                                        .toList()
                        )
                ));
    }

    // 按是否延迟分区，统计两类订单的总配送费
    public static Map<Boolean, BigDecimal> t16(List<DeliveryOrder> orders) {

        return orders.stream()
                .collect(Collectors.partitioningBy(
                        DeliveryOrder::delayed,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                DeliveryOrder::deliveryFee,
                                BigDecimal::add
                        )
                ));
    }

    // 找出每个商家订单最多的城市，若并列返回全部城市
    public static Map<String, List<String>> t17(List<DeliveryOrder> orders) {

        return orders.stream()
                .collect(Collectors.groupingBy(
                        DeliveryOrder::merchantName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Map<String, Integer> map = list.stream()
                                            .collect(Collectors.toMap(
                                                    DeliveryOrder::city,
                                                    t -> 1,
                                                    Integer::sum
                                            ));

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

    // 查询使用优惠券金额大于 0 的用户名，去重
    public static List<String> t18(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> o.coupon().compareTo(BigDecimal.ZERO) > 0)
                .map(DeliveryOrder::userName)
                .distinct()
                .toList();
    }

    // 统计每个城市已完成订单平均评分，按平均评分降序排序
    public static Map<String, Double> t19(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> "COMPLETED".equals(o.status()))
                .collect(Collectors.groupingBy(
                        DeliveryOrder::city,
                        Collectors.averagingInt(DeliveryOrder::rating)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // 找出总消费金额最高的前 3 个用户，并保留排序
    public static Map<String, BigDecimal> t20(List<DeliveryOrder> orders) {

        return orders.stream()
                .filter(o -> "COMPLETED".equals(o.status()))
                .collect(Collectors.groupingBy(
                        DeliveryOrder::userName,
                        Collectors.reducing(BigDecimal.ZERO, DeliveryOrder::amount, BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByValue()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}