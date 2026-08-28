package practice.stream;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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

}