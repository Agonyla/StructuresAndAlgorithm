package practice.stream;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/4 11:47
 * @describe:
 */
public class StreamPractice2 {

    record Ticket(
            Integer id,
            String project,
            String module,
            String owner,
            String priority,
            String status,
            int estimateHours,
            int actualHours,
            LocalDate createdAt,
            LocalDate resolvedAt,
            boolean customerImpact,
            List<String> labels
    ) {
    }

    public static void main(String[] args) {
        List<Ticket> tickets = Arrays.asList(
                new Ticket(1, "支付系统", "支付网关", "张三", "P0", "OPEN", 16, 0,
                        LocalDate.of(2026, 7, 1), null, true, List.of("线上问题", "支付", "阻塞")),
                new Ticket(2, "支付系统", "对账", "李四", "P1", "RESOLVED", 10, 14,
                        LocalDate.of(2026, 7, 2), LocalDate.of(2026, 7, 5), true, List.of("对账", "金额异常")),
                new Ticket(3, "支付系统", "支付网关", "王五", "P2", "CLOSED", 6, 5,
                        LocalDate.of(2026, 7, 3), LocalDate.of(2026, 7, 4), false, List.of("支付", "兼容性")),
                new Ticket(4, "用户中心", "登录", "赵六", "P0", "IN_PROGRESS", 12, 3,
                        LocalDate.of(2026, 7, 4), null, true, List.of("登录", "线上问题", "安全")),
                new Ticket(5, "用户中心", "注册", "钱七", "P2", "RESOLVED", 8, 6,
                        LocalDate.of(2026, 7, 5), LocalDate.of(2026, 7, 6), false, List.of("注册", "体验")),
                new Ticket(6, "用户中心", "登录", "张三", "P1", "REOPENED", 10, 8,
                        LocalDate.of(2026, 7, 6), null, true, List.of("登录", "回归失败")),
                new Ticket(7, "订单系统", "下单", "李四", "P1", "OPEN", 9, 0,
                        LocalDate.of(2026, 7, 7), null, true, List.of("下单", "阻塞")),
                new Ticket(8, "订单系统", "库存锁定", "王五", "P0", "RESOLVED", 15, 20,
                        LocalDate.of(2026, 7, 8), LocalDate.of(2026, 7, 12), true, List.of("库存", "超卖", "线上问题")),
                new Ticket(9, "订单系统", "优惠券", "赵六", "P3", "CLOSED", 4, 3,
                        LocalDate.of(2026, 7, 9), LocalDate.of(2026, 7, 10), false, List.of("优惠券", "体验")),
                new Ticket(10, "风控系统", "规则引擎", "钱七", "P0", "OPEN", 20, 0,
                        LocalDate.of(2026, 7, 10), null, true, List.of("风控", "规则", "阻塞")),
                new Ticket(11, "风控系统", "黑名单", "张三", "P2", "RESOLVED", 7, 9,
                        LocalDate.of(2026, 7, 11), LocalDate.of(2026, 7, 14), false, List.of("风控", "黑名单")),
                new Ticket(12, "风控系统", "规则引擎", "李四", "P1", "IN_PROGRESS", 11, 4,
                        LocalDate.of(2026, 7, 12), null, true, List.of("规则", "性能")),
                new Ticket(13, "数据平台", "报表", "王五", "P2", "CLOSED", 5, 7,
                        LocalDate.of(2026, 7, 13), LocalDate.of(2026, 7, 16), false, List.of("报表", "SQL")),
                new Ticket(14, "数据平台", "ETL", "赵六", "P1", "RESOLVED", 13, 16,
                        LocalDate.of(2026, 7, 14), LocalDate.of(2026, 7, 18), true, List.of("ETL", "数据延迟")),
                new Ticket(15, "数据平台", "报表", "钱七", "P3", "OPEN", 3, 0,
                        LocalDate.of(2026, 7, 15), null, false, List.of("报表", "体验")),
                new Ticket(16, "支付系统", "对账", "赵六", "P1", "IN_PROGRESS", 9, 2,
                        LocalDate.of(2026, 7, 16), null, true, List.of("对账", "金额异常", "阻塞")),
                new Ticket(17, "订单系统", "下单", "张三", "P2", "RESOLVED", 6, 8,
                        LocalDate.of(2026, 7, 17), LocalDate.of(2026, 7, 19), true, List.of("下单", "性能")),
                new Ticket(18, "用户中心", "资料页", "李四", "P3", "CLOSED", 4, 4,
                        LocalDate.of(2026, 7, 18), LocalDate.of(2026, 7, 20), false, List.of("资料页", "体验"))
        );

        Set<String> unfinishedStatus = Set.of("OPEN", "IN_PROGRESS", "REOPENED");
        Set<String> finishedStatus = Set.of("RESOLVED", "CLOSED");

        Map<String, Integer> priorityRank = Map.of(
                "P0", 0,
                "P1", 1,
                "P2", 2,
                "P3", 3
        );

        // 1. 统计每个项目未完成工单数量，并按数量降序排序
        System.out.println(t1(tickets, unfinishedStatus, finishedStatus));
        // 2. 找出每个项目最紧急的一条未完成工单
        System.out.println(t2(tickets, unfinishedStatus, finishedStatus, priorityRank));
        // 3. 按负责人统计已完成工单平均解决天数，并按天数降序排序
        System.out.println(t3(tickets, unfinishedStatus, finishedStatus));
        // 4. 按项目和模块二级分组，统计工单数量
        // 5. 按项目收集所有标签，去重并排序
        // 6. 找出每个负责人实际耗时最高的前 2 个工单 ID
        // 7. 使用 toMap 找出每个模块最近创建的客户影响工单
        // 8. 统计每个项目中 P0/P1 高优先级工单占比
        // 9. 统计标签出现次数 Top5
        // 10. 使用 teeing 统计每个项目的工时概览字符串

    }

    // 1. 统计每个项目未完成工单数量，并按数量降序排序
    public static Map<String, Long> t1(List<Ticket> tickets, Set<String> unfinishedStatus, Set<String> finishedStatus) {

        // return tickets.stream()
        //         .filter(t -> unfinishedStatus.contains(t.status()))
        //         .collect(Collectors.groupingBy(
        //                 Ticket::project,
        //                 Collectors.counting()
        //         ))
        //         .entrySet()
        //         .stream()
        //         .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        //         .collect(Collectors.toMap(
        //                 Map.Entry::getKey,
        //                 Map.Entry::getValue,
        //                 (oldVal, newVal) -> oldVal,
        //                 LinkedHashMap::new)
        //         );

        return tickets.stream()
                .filter(t -> unfinishedStatus.contains(t.status()))
                .collect(Collectors.toMap(
                        Ticket::project,
                        ticket -> 1L,
                        Long::sum,
                        LinkedHashMap::new
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new)
                );
    }

    // 2. 找出每个项目最紧急的一条未完成工单
    public static Map<String, Ticket> t2(List<Ticket> tickets,
                                         Set<String> unfinishedStatus, Set<String> finishedStatus,
                                         Map<String, Integer> priorityRank) {

        // return tickets.stream()
        //         .filter(t -> unfinishedStatus.contains(t.status()))
        //         .collect(Collectors.groupingBy(
        //                 Ticket::project,
        //                 Collectors.collectingAndThen(
        //                         Collectors.minBy(Comparator.comparing((Ticket t) -> priorityRank.get(t.priority()))
        //                                 .thenComparing(Ticket::createdAt)),
        //                         Optional::get
        //                 )
        //         ));

        return tickets.stream()
                .filter(t -> unfinishedStatus.contains(t.status()))
                .collect(Collectors.toMap(
                        Ticket::project,
                        Function.identity(),
                        BinaryOperator.minBy(Comparator.comparing((Ticket t) -> priorityRank.get(t.priority()))
                                .thenComparing(t -> t.createdAt))
                ));
    }

    // 3. 按负责人统计已完成工单平均解决天数，并按天数降序排序
    public static Map<String, Double> t3(List<Ticket> tickets, Set<String> unfinishedStatus, Set<String> finishedStatus) {

        return tickets.stream()
                .filter(t -> finishedStatus.contains(t.status()))
                .collect(Collectors.groupingBy(
                        Ticket::owner,
                        Collectors.averagingLong(
                                t -> ChronoUnit.DAYS.between(t.createdAt(), t.resolvedAt())
                        ))
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));

        // 不用改成toMap，太麻烦了
    }

    // 4. 按项目和模块二级分组，统计工单数量

    // 5. 按项目收集所有标签，去重并排序
    // 6. 找出每个负责人实际耗时最高的前 2 个工单 ID
    // 7. 使用 toMap 找出每个模块最近创建的客户影响工单
    // 8. 统计每个项目中 P0/P1 高优先级工单占比
    // 9. 统计标签出现次数 Top5
    // 10. 使用 teeing 统计每个项目的工时概览字符串
}