package practice.stream;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/4 9:32
 * @describe:
 */
public class StreamPractice1 {

    public record Employee(
            Integer id,
            String name,
            String dept,
            String city,
            String level,
            BigDecimal salary,
            int score,
            boolean active,
            LocalDate joinDate,
            List<String> skills
    ) {
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "张三", "研发部", "上海", "P6", new BigDecimal("32000"), 91, true,
                        LocalDate.of(2021, 3, 12), List.of("Java", "Spring", "MySQL")),
                new Employee(2, "李四", "研发部", "北京", "P5", new BigDecimal("24000"), 86, true,
                        LocalDate.of(2022, 7, 1), List.of("Java", "Redis", "Docker")),
                new Employee(3, "王五", "产品部", "上海", "P6", new BigDecimal("30000"), 88, true,
                        LocalDate.of(2020, 5, 20), List.of("Axure", "SQL", "数据分析")),
                new Employee(4, "赵六", "测试部", "广州", "P4", new BigDecimal("16000"), 78, true,
                        LocalDate.of(2023, 1, 10), List.of("测试", "Selenium", "Python")),
                new Employee(5, "钱七", "研发部", "上海", "P7", new BigDecimal("45000"), 95, true,
                        LocalDate.of(2019, 11, 3), List.of("Java", "架构", "Kubernetes")),
                new Employee(6, "孙八", "产品部", "北京", "P5", new BigDecimal("22000"), 82, false,
                        LocalDate.of(2021, 9, 18), List.of("Axure", "用户研究")),
                new Employee(7, "周九", "测试部", "上海", "P5", new BigDecimal("21000"), 84, true,
                        LocalDate.of(2022, 2, 14), List.of("测试", "JMeter", "Python")),
                new Employee(8, "吴十", "运营部", "深圳", "P4", new BigDecimal("15000"), 73, true,
                        LocalDate.of(2023, 6, 8), List.of("Excel", "活动策划")),
                new Employee(9, "郑十一", "运营部", "广州", "P5", new BigDecimal("20000"), 80, true,
                        LocalDate.of(2020, 12, 25), List.of("数据分析", "SQL", "增长")),
                new Employee(10, "王十二", "研发部", "深圳", "P6", new BigDecimal("33000"), 89, false,
                        LocalDate.of(2018, 8, 6), List.of("Go", "MySQL", "微服务")),
                new Employee(11, "冯十三", "产品部", "上海", "P7", new BigDecimal("42000"), 94, true,
                        LocalDate.of(2018, 4, 16), List.of("产品规划", "数据分析", "SQL")),
                new Employee(12, "陈十四", "测试部", "北京", "P6", new BigDecimal("28000"), 90, true,
                        LocalDate.of(2019, 10, 30), List.of("测试", "自动化", "Java")),
                new Employee(13, "刘十五", "研发部", "杭州", "P5", new BigDecimal("26000"), 87, true,
                        LocalDate.of(2022, 11, 11), List.of("Java", "Spring", "Redis")),
                new Employee(14, "黄十六", "运营部", "上海", "P6", new BigDecimal("31000"), 85, true,
                        LocalDate.of(2021, 1, 5), List.of("增长", "数据分析", "SQL")),
                new Employee(15, "林十七", "产品部", "深圳", "P5", new BigDecimal("23000"), 79, true,
                        LocalDate.of(2023, 3, 22), List.of("Axure", "竞品分析", "用户研究"))
        );

        // 1. 按部门统计在职员工平均绩效分，并按平均分降序排序
        System.out.println(t1(employees));
        // 2. 找出每个部门工资最高的在职员工
        // 3. 按城市分组，把在职员工姓名按绩效降序拼接成字符串
        System.out.println(t3(employees));
        // 4. 统计每种技能有多少在职员工掌握，并按人数降序排序
        System.out.println(t4(employees));
        // 5. 按部门分组，收集每个部门所有技能，去重并排序
        // 6. 使用 toMap 统计每个职级的在职员工工资总额
        // 7. 使用 toMap 找出每个城市绩效最高的在职员工 ID
        // 8. 按部门分组，获取每个部门绩效前 2 名的在职员工姓名
        // 9. 按是否高薪分区，每个分区内按工资降序返回员工姓名
        // 10. 找出在职员工总工资最高的前 3 个部门
        System.out.println(t10(employees));

    }

    // 1. 按部门统计在职员工平均绩效分，并按平均分降序排序
    public static Map<String, Double> t1(List<Employee> employees) {

        return employees.stream()
                .filter(Employee::active)
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.averagingInt(Employee::score)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
    }

    // 2. 找出每个部门工资最高的在职员工
    public static Map<String, Employee> t2(List<Employee> employees) {

        // return employees.stream()
        //         .filter(Employee::active)
        //         .collect(Collectors.groupingBy(
        //                 Employee::dept,
        //                 Collectors.collectingAndThen(
        //                         Collectors.maxBy(Comparator.comparing(Employee::salary)),
        //                         Optional::get
        //                 )
        //         ));

        return employees.stream()
                .filter(Employee::active)
                .collect(Collectors.toMap(
                        Employee::dept,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Employee::salary))
                ));
    }

    // 3. 按城市分组，把在职员工姓名按绩效降序拼接成字符串
    public static Map<String, String> t3(List<Employee> employees) {

        // return employees.stream()
        //         .filter(Employee::active)
        //         .sorted(Comparator.comparing(Employee::score).reversed())
        //         .collect(Collectors.groupingBy(
        //                 Employee::city,
        //                 Collectors.mapping(
        //                         Employee::name,
        //                         Collectors.joining("-")
        //                 )
        //         ));

        return employees.stream()
                .filter(Employee::active)
                .sorted(Comparator.comparing(Employee::score).reversed())
                .collect(Collectors.toMap(
                        Employee::city,
                        Employee::name,
                        (oldVal, newVal) -> oldVal + "-" + newVal
                ));
    }

    // 4. 统计每种技能有多少在职员工掌握，并按人数降序排序
    public static Map<String, Long> t4(List<Employee> employees) {

        return employees.stream()
                .filter(Employee::active)
                .flatMap(e -> e.skills().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
    }

    // 5. 按部门分组，收集每个部门所有技能，去重并排序
    public static Map<String, Set<String>> t5(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.flatMapping(
                                e -> e.skills().stream(),
                                Collectors.toCollection(TreeSet::new)
                        )
                ));
    }

    // 6. 使用 toMap 统计每个职级的在职员工工资总额
    public static Map<String, BigDecimal> t6(List<Employee> employees) {

        return employees.stream()
                .filter(Employee::active)
                .collect(Collectors.toMap(
                        Employee::level,
                        Employee::salary,
                        BigDecimal::add
                ));
    }

    // 7. 使用 toMap 找出每个城市绩效最高的在职员工 ID
    public static Map<String, Integer> t7(List<Employee> employees) {
        return employees.stream()
                .filter(Employee::active)
                .collect(Collectors.toMap(
                        Employee::city,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Employee::score))
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().id()
                ));
    }

    // 8. 按部门分组，获取每个部门绩效前 2 名的在职员工姓名
    public static Map<String, List<String>> t8(List<Employee> employees) {
        return employees.stream()
                .filter(Employee::active)
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Employee::score).reversed())
                                        .map(Employee::name)
                                        .limit(2)
                                        .toList()
                        )
                ));
    }

    // 9. 按是否高薪分区，每个分区内按工资降序返回员工姓名
    // 10. 找出在职员工总工资最高的前 3 个部门
    public static List<String> t10(List<Employee> employees) {

        return employees.stream()
                .filter(Employee::active)
                .collect(Collectors.toMap(
                        Employee::dept,
                        Employee::salary,
                        BigDecimal::add
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }
}