package practice.stream;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

        // 2. 统计每个用户购买过的不同商品 -> Map<Long, List<String>>

    }
}