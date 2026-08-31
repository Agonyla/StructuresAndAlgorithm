package practice.stream;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/27 10:32
 * @describe:
 */
public class StreamPractice7 {

    record Employee(
            long id,
            String name,
            String department,
            int age,
            BigDecimal salary
    ) {
    }

    record OrderItem(
            String productId,
            String productName,
            String category,
            int quantity,
            BigDecimal unitPrice
    ) {
        BigDecimal amount() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    record Order(
            String orderId,
            long userId,
            LocalDateTime createdAt,
            List<OrderItem> items,
            List<String> tags
    ) {
        BigDecimal amount() {
            return items.stream()
                    .map(OrderItem::amount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    record LoginRecord(
            long userId,
            LocalDate loginDate
    ) {
    }

    record UserProfile(
            long userId,
            String name,
            String city,
            LocalDateTime updatedAt
    ) {
    }

    record ProjectMember(
            String project,
            long employeeId
    ) {
    }

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "Alice", "Engineering", 29, new BigDecimal("18000")),
                new Employee(2, "Bob", "Engineering", 35, new BigDecimal("25000")),
                new Employee(3, "Charlie", "Engineering", 31, new BigDecimal("22000")),
                new Employee(4, "David", "Engineering", 40, new BigDecimal("25000")),
                new Employee(5, "Eve", "Sales", 28, new BigDecimal("16000")),
                new Employee(6, "Frank", "Sales", 33, new BigDecimal("21000")),
                new Employee(7, "Grace", "Sales", 30, new BigDecimal("19000")),
                new Employee(8, "Henry", "HR", 38, new BigDecimal("17000")),
                new Employee(9, "Ivy", "HR", 26, new BigDecimal("15000")),
                new Employee(10, "Jack", "Finance", 36, new BigDecimal("23000")),
                new Employee(11, "Kate", "Finance", 32, new BigDecimal("23000")),
                new Employee(12, "Leo", "Finance", 27, new BigDecimal("18000"))
        );

        List<Order> orders = List.of(
                new Order(
                        "O001",
                        101,
                        LocalDateTime.of(2026, 8, 1, 10, 0),
                        List.of(
                                new OrderItem("P01", "MacBook", "Computer", 1, new BigDecimal("12000")),
                                new OrderItem("P03", "Mouse", "Accessory", 2, new BigDecimal("300"))
                        ),
                        List.of("NEW", "ONLINE")
                ),

                new Order(
                        "O002",
                        102,
                        LocalDateTime.of(2026, 8, 2, 11, 0),
                        List.of(
                                new OrderItem("P02", "ThinkPad", "Computer", 1, new BigDecimal("9000")),
                                new OrderItem("P03", "Mouse", "Accessory", 1, new BigDecimal("300")),
                                new OrderItem("P04", "Keyboard", "Accessory", 1, new BigDecimal("500"))
                        ),
                        List.of("ONLINE", "VIP")
                ),

                new Order(
                        "O003",
                        101,
                        LocalDateTime.of(2026, 8, 3, 14, 0),
                        List.of(
                                new OrderItem("P04", "Keyboard", "Accessory", 2, new BigDecimal("500")),
                                new OrderItem("P05", "Monitor", "Display", 1, new BigDecimal("2400"))
                        ),
                        List.of("VIP")
                ),

                new Order(
                        "O004",
                        103,
                        LocalDateTime.of(2026, 8, 4, 16, 0),
                        List.of(
                                new OrderItem("P01", "MacBook", "Computer", 1, new BigDecimal("12000")),
                                new OrderItem("P05", "Monitor", "Display", 2, new BigDecimal("2400"))
                        ),
                        List.of("OFFLINE")
                ),

                new Order(
                        "O005",
                        102,
                        LocalDateTime.of(2026, 8, 5, 9, 0),
                        List.of(
                                new OrderItem("P03", "Mouse", "Accessory", 3, new BigDecimal("300")),
                                new OrderItem("P04", "Keyboard", "Accessory", 1, new BigDecimal("500")),
                                new OrderItem("P05", "Monitor", "Display", 1, new BigDecimal("2400"))
                        ),
                        List.of("ONLINE")
                ),

                new Order(
                        "O006",
                        104,
                        LocalDateTime.of(2026, 8, 6, 18, 0),
                        List.of(
                                new OrderItem("P02", "ThinkPad", "Computer", 2, new BigDecimal("9000")),
                                new OrderItem("P03", "Mouse", "Accessory", 1, new BigDecimal("300"))
                        ),
                        List.of("VIP", "ONLINE")
                )
        );

        List<LoginRecord> loginRecords = List.of(
                new LoginRecord(101, LocalDate.of(2026, 8, 1)),
                new LoginRecord(101, LocalDate.of(2026, 8, 2)),
                new LoginRecord(101, LocalDate.of(2026, 8, 3)),
                new LoginRecord(101, LocalDate.of(2026, 8, 5)),
                new LoginRecord(102, LocalDate.of(2026, 8, 1)),
                new LoginRecord(102, LocalDate.of(2026, 8, 3)),
                new LoginRecord(102, LocalDate.of(2026, 8, 4)),
                new LoginRecord(102, LocalDate.of(2026, 8, 5)),
                new LoginRecord(102, LocalDate.of(2026, 8, 6)),
                new LoginRecord(103, LocalDate.of(2026, 8, 1)),
                new LoginRecord(103, LocalDate.of(2026, 8, 2))
        );

        List<UserProfile> profiles = List.of(
                new UserProfile(
                        101,
                        "Alice",
                        "Shanghai",
                        LocalDateTime.of(2026, 8, 1, 10, 0)
                ),

                new UserProfile(
                        102,
                        "Bob",
                        "Beijing",
                        LocalDateTime.of(2026, 8, 2, 10, 0)
                ),

                new UserProfile(
                        101,
                        "Alice",
                        "Hangzhou",
                        LocalDateTime.of(2026, 8, 5, 10, 0)
                ),

                new UserProfile(
                        103,
                        "Charlie",
                        "Shenzhen",
                        LocalDateTime.of(2026, 8, 3, 10, 0)
                ),

                new UserProfile(
                        102,
                        "Bob",
                        "Shanghai",
                        LocalDateTime.of(2026, 8, 6, 10, 0)
                )
        );

        List<ProjectMember> projectMembers = List.of(
                new ProjectMember("Payment", 1),
                new ProjectMember("Payment", 2),
                new ProjectMember("Payment", 10),

                new ProjectMember("Search", 2),
                new ProjectMember("Search", 3),
                new ProjectMember("Search", 4),

                new ProjectMember("CRM", 5),
                new ProjectMember("CRM", 6),
                new ProjectMember("CRM", 1),

                new ProjectMember("Risk", 10),
                new ProjectMember("Risk", 11),
                new ProjectMember("Risk", 4)
        );

        // 1. 统计每个部门薪资最高的前 2 名员工 -> Map<String, List<Employee>>
        System.out.println(t1(employees));
        // 2. 统计每个用户购买过的不同商品 -> Map<Long, List<String>>
        System.out.println(t2(orders));
        // 3. 统计商品销售额，并找出销售额最高的前 3 个商品 -> List<Map.Entry<String, BigDecimal>>
        System.out.println(t3(orders));
        // 4. 找出至少连续登录 3 天的用户 -> Set<Long>
        System.out.println(t4(loginRecords));
        // 5. 找出每个部门工资最高的员工 -> Map<String, Employee>
        System.out.println(t5(employees));
        // 6. 构建订单标签的倒排索引 -> Map<String, Set<String>>
        System.out.println(t6(orders));
        // 7. 按照用户总消费金额划分客户等级 -> Map<String, List<Long>>
        System.out.println(t7(orders));
        // 8. 统计“经常一起购买”的商品组合 -> Map<ProductPair, Long>
        System.out.println(t8(orders));
        // 9. 用户资料去重，只保留最新的一条 -> Map<Long, UserProfile>
        System.out.println(t9(profiles));
        // 10. 构建“部门 → 项目 → 员工列表”矩阵 -> Map<String, Map<String, List<String>>>
        System.out.println(t10(projectMembers, employees));

    }

    // 1. 统计每个部门薪资最高的前 2 名员工 -> Map<String, List<Employee>>
    // - 按部门分组；
    // - 每个部门按照工资降序；
    // - 工资相同时按照年龄升序；
    // - 每个部门只保留前 2 名。
    public static Map<String, List<Employee>> t1(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Employee::salary)
                                                .reversed()
                                                .thenComparing(Employee::age)
                                        )
                                        .limit(2)
                                        .toList()
                        )
                ));
    }

    // 2. 统计每个用户购买过的不同商品 -> Map<Long, List<String>>
    // - Key：用户 ID；
    // - Value：用户买过的商品名称；
    // - 商品不能重复；
    // - 最终商品名按字典序排序
    public static Map<Long, List<String>> t2(List<Order> orders) {

        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::userId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .flatMap(o -> o.items().stream())
                                        .map(OrderItem::productName)
                                        .distinct()
                                        .sorted()
                                        .toList()
                        )
                ));
    }

    // 3. 统计商品销售额，并找出销售额最高的前 3 个商品 -> List<Map.Entry<String, BigDecimal>>
    public static List<Map.Entry<String, BigDecimal>> t3(List<Order> orders) {

        return orders.stream()
                .flatMap(o -> o.items().stream())
                .collect(Collectors.toMap(
                        OrderItem::productName,
                        OrderItem::amount,
                        BigDecimal::add
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(3)
                .toList();
    }

    // 4. 找出至少连续登录 3 天的用户 -> Set<Long>
    public static Set<Long> t4(List<LoginRecord> records) {

        return records.stream()
                .collect(Collectors.groupingBy(
                        LoginRecord::userId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    List<LocalDate> dates = list.stream()
                                            .sorted(Comparator.comparing(LoginRecord::loginDate))
                                            .map(LoginRecord::loginDate)
                                            .toList();

                                    int consecutive = 1;

                                    for (int i = 1; i < list.size(); i++) {

                                        if (dates.get(i - 1).plusDays(1).equals(dates.get(i))) {
                                            consecutive++;
                                        } else {
                                            consecutive = 0;
                                        }

                                        if (consecutive >= 3) {
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

    // 5. 找出每个部门工资最高的员工 -> Map<String, Employee>
    // 年龄小的优先
    public static Map<String, Employee> t5(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .max(Comparator.comparing(Employee::salary)
                                                .thenComparing(Employee::age,
                                                        Comparator.reverseOrder())
                                        )
                                        .orElse(null)
                        )
                ));
    }

    // 6. 构建订单标签的倒排索引 -> Map<String, Set<String>>
    // 0001 -> [ONLINE, NEW]
    // 0002 -> [ONLINE, VIP]
    // 0003 -> [VIP]
    // 0005 -> [ONLINE]
    // 0006 -> [ONLINE, VIP]
    // ----------->
    // ONLINE -> [O001, O002, O005, O006]
    // VIP    -> [O002, O003, O006]
    // NEW    -> [O001]
    public static Map<String, Set<String>> t6(List<Order> orders) {

        return orders.stream()
                .flatMap(o -> o.tags().stream()
                        .map(tag -> Map.entry(tag, o.orderId()))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .map(Map.Entry::getValue)
                                        .sorted()
                                        .collect(Collectors.toCollection(TreeSet::new))
                        )
                ));
    }

    // 7. 按照用户总消费金额划分客户等级 -> Map<String, List<Long>>
    // >= 20000     -> S
    // >= 10000     -> A
    // >= 5000      -> B
    // < 5000       -> C
    public static Map<String, List<Long>> t7(List<Order> orders) {

        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::userId,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Order::amount,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        e -> {
                            if (e.getValue().compareTo(BigDecimal.valueOf(5000L)) < 0) {
                                return "C";
                            } else if (e.getValue().compareTo(BigDecimal.valueOf(10000L)) < 0) {
                                return "B";
                            } else if (e.getValue().compareTo(BigDecimal.valueOf(20000L)) < 0) {
                                return "A";
                            } else {
                                return "S";
                            }
                        },
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .map(Map.Entry::getKey)
                                        .toList()
                        )
                ));
    }

    // !!! Important
    // 8. 统计“经常一起购买”的商品组合 -> Map<ProductPair, Long>
    // 如果一个订单里包含：
    //
    // Mouse
    // Keyboard
    // Monitor
    //
    // 则商品 Pair 有：
    //
    // Keyboard + Monitor
    // Keyboard + Mouse
    // Monitor + Mouse
    //
    // 要求：
    //
    // 商品组合不考虑顺序；
    // (Mouse, Keyboard) 和 (Keyboard, Mouse) 算同一个组合；
    // 统计每个组合共同出现了多少个订单；
    // 按出现次数倒序；
    // 次数相同时按照组合名称排序
    record ProductPair(String first, String second) {

        static ProductPair of(String a, String b) {

            if (a.compareTo(b) <= 0) {
                return new ProductPair(a, b);
            }
            return new ProductPair(b, a);
        }
    }

    public static Map<ProductPair, Long> t8(List<Order> orders) {

        // 这里groupBy有点冗余，可以改成下面那种写法
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::orderId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    List<String> productList = list.stream()
                                            .flatMap(o -> o.items().stream())
                                            .map(OrderItem::productName)
                                            .distinct()
                                            .toList();

                                    List<ProductPair> pairList = new ArrayList<>();

                                    for (int i = 0; i < productList.size(); i++) {
                                        for (int j = i + 1; j < productList.size(); j++) {
                                            pairList.add(ProductPair.of(productList.get(i), productList.get(j)));
                                        }
                                    }
                                    // ab, ac, ad, bc, bd, cd

                                    return pairList;
                                }
                        )
                ))
                .entrySet()
                .stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<ProductPair, Long>comparingByValue()
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

        // List<Map.Entry<ProductPair, Long>>
        // return orders.stream()
        //         .flatMap(o -> {
        //             List<String> productList = o.items().stream()
        //                     .map(OrderItem::productName)
        //                     .distinct()
        //                     .toList();
        //
        //             List<ProductPair> pairList = new ArrayList<>();
        //
        //             for (int i = 0; i < productList.size(); i++) {
        //                 for (int j = i + 1; j < productList.size(); j++) {
        //                     pairList.add(ProductPair.of(productList.get(i), productList.get(j)));
        //                 }
        //             }
        //
        //             return pairList.stream();
        //
        //         })
        //         .collect(Collectors.groupingBy(
        //                 Function.identity(),
        //                 Collectors.counting()
        //         ))
        //         .entrySet()
        //         .stream()
        //         .sorted(Map.Entry.<ProductPair, Long>comparingByValue()
        //                 .reversed()
        //                 .thenComparing(e -> e.getKey().first())
        //                 .thenComparing(e -> e.getKey().second())
        //         )
        //         .toList();

    }

    // 9. 用户资料去重，只保留最新的一条 -> Map<Long, UserProfile>
    // 保留 updatedAt 最大的记录。
    public static Map<Long, UserProfile> t9(List<UserProfile> profiles) {

        return profiles.stream()
                .collect(Collectors.toMap(
                                UserProfile::userId,
                                Function.identity(),
                                BinaryOperator.maxBy(Comparator.comparing(UserProfile::updatedAt))
                        )
                );
    }

    // 10. 构建“部门 → 项目 → 员工列表”矩阵 -> Map<String, Map<String, List<String>>>
    // Engineering
    //     Payment -> [Alice, Bob]
    //     Search  -> [Bob, Charlie, David]
    //     CRM     -> [Alice]
    //     Risk    -> [David]
    //
    // Finance
    //     Payment -> [Jack]
    //     Risk    -> [Jack, Kate]

    // 员工名称按照字母排序。
    public static Map<String, Map<String, List<String>>> t10(List<ProjectMember> projectMembers,
                                                             List<Employee> employees) {

        Map<Long, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(
                        Employee::id,
                        Function.identity()
                ));

        return projectMembers.stream()
                .collect(Collectors.groupingBy(
                        projectMember -> employeeMap.get(projectMember.employeeId()).department(),
                        Collectors.groupingBy(
                                ProjectMember::project,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list2 -> list2.stream()
                                                .map(projectMember -> employeeMap.get(projectMember.employeeId()).name())
                                                .sorted()
                                                .toList()
                                )
                        ))
                );
    }

}