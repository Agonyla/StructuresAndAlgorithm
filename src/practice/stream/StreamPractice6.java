package practice.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Agony
 * @create: 2026/8/20 9:37
 * @describe:
 */
public class StreamPractice6 {

    record Employee(
            Integer id,
            String name,
            String department,
            Integer age,
            Double salary,
            List<String> skills
    ) {
        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    ", skills=" + skills +
                    '}';
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(
                        1, "张三", "研发部", 28, 15000.0,
                        Arrays.asList("Java", "Spring", "MySQL")
                ),
                new Employee(
                        2, "李四", "研发部", 32, 22000.0,
                        Arrays.asList("Java", "SpringBoot", "Redis")
                ),
                new Employee(
                        3, "王五", "测试部", 26, 12000.0,
                        Arrays.asList("Java", "Selenium", "MySQL")
                ),
                new Employee(
                        4, "赵六", "研发部", 35, 28000.0,
                        Arrays.asList("Java", "Spring", "Redis", "Kafka")
                ),
                new Employee(
                        5, "钱七", "产品部", 30, 18000.0,
                        Arrays.asList("Axure", "SQL", "Excel")
                ),
                new Employee(
                        6, "孙八", "测试部", 29, 14000.0,
                        Arrays.asList("Python", "Selenium", "Linux")
                ),
                new Employee(
                        7, "周九", "产品部", 33, 21000.0,
                        Arrays.asList("Axure", "SQL", "Java")
                ),
                new Employee(
                        8, "吴十", "研发部", 25, 13000.0,
                        Arrays.asList("Java", "Vue", "MySQL")
                ),
                new Employee(
                        9, "郑十一", "运维部", 36, 24000.0,
                        Arrays.asList("Linux", "Docker", "Kubernetes")
                ),
                new Employee(
                        10, "王十二", "运维部", 31, 19000.0,
                        Arrays.asList("Linux", "Docker", "Redis")
                ),
                new Employee(
                        11, "冯十三", "研发部", 29, 22000.0,
                        Arrays.asList("Java", "SpringBoot", "Kafka")
                ),
                new Employee(
                        12, "陈十四", "测试部", 34, 17000.0,
                        Arrays.asList("Python", "JMeter", "MySQL")
                )
        );

        // 1. 找出：研发部员工，工资大于 15000，按工资从高到低排序，最终只返回员工姓名
        System.out.println(t1(employees));
        // 2. 从所有员工中找出工资最高的 3 名员工。工资降序，如果工资相同，年龄小的排前面
        System.out.println(t2(employees));
        // 3. 将员工按照部门分组，并统计每个部门有多少人。
        System.out.println(t3(employees));
        // 4. 按照部门统计平均工资。
        System.out.println(t4(employees));
        // 5. 按照部门分组，然后找出每个部门工资最高的员工
        System.out.println(t5(employees));
        // 6. 定义三个年龄段：30岁以下、30~34岁、35岁及以上 按照年龄段对员工分组。
        System.out.println(t6(employees));
        // 7. 统计全部员工的技能出现次数。
        System.out.println(t7(employees));
        // 8. 找出出现次数最多的技能，在第 7 题基础上，进一步找出：被最多员工掌握的技能
        System.out.println(t8(employees));
        // 9. 找出掌握 Java 但不会 Spring/SpringBoot 的员工
        System.out.println(t9(employees));
        // 10. 部门工资总额排名
        System.out.println(t10(employees));
        // 11. 判断部门是否存在“高薪员工” （工资 >= 20000 属于高新员工）
        System.out.println(t11(employees));
        // 12. 按部门统计员工姓名
        System.out.println(t12(employees));
        // 13. 找出工资重复的员工
        System.out.println(t13(employees));
        // 14. 每个部门工资前两名
        System.out.println(t14(employees));
        // 15. 找出平均工资最高的部门
        System.out.println(t15(employees));
        // 16. 生成员工信息 Map （员工姓名 -> 工资）
        System.out.println(t16(employees));
        // 17. 技能 → 员工姓名（Java -> [张三, 李四, 王五, 赵六, 周九, 吴十, 冯十三]）
        System.out.println(t17(employees));
        // 18. 统计各部门工资分布。将工资分成：低薪：< 15000，中薪：15000 ~ 19999，高薪：>= 20000 要求形成二级 Map：Map<String, Map<String, Long>>
        // 19. 计算部门工资极差 定义工资极差：最高工资 - 最低工资
        // 20. 每个部门中，掌握技能数量最多的员工。Map<String, Employee>。按部门分组，比较 skills.size()，技能数量相同时，工资高的人获胜，工资再相同时，年龄小的人获胜

    }

    // 1. 找出：研发部员工，工资大于 15000，按工资从高到低排序，最终只返回员工姓名
    public static List<String> t1(List<Employee> employees) {

        return employees.stream()
                .filter(employee -> employee.salary() > 15000)
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .map(Employee::name)
                .toList();
    }

    // 2. 从所有员工中找出工资最高的 3 名员工。工资降序，如果工资相同，年龄小的排前面
    public static List<Employee> t2(List<Employee> employees) {

        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary).reversed()
                        .thenComparing(Employee::age))
                .limit(3)
                .toList();
    }

    // 3. 将员工按照部门分组，并统计每个部门有多少人。
    public static Map<String, Long> t3(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.counting()
                ));
    }

    // 4. 按照部门统计平均工资。
    public static Map<String, Double> t4(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                ));
    }

    // 5. 按照部门分组，然后找出每个部门工资最高的员工
    public static Map<String, Employee> t5(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .max(Comparator.comparingDouble(Employee::salary))
                                        .orElse(null)
                        )
                ));
    }

    // 6. 定义三个年龄段：30岁以下、30~34岁、35岁及以上 按照年龄段对员工分组。
    public static Map<String, List<Employee>> t6(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                                r -> {
                                    if (r.age() < 30) {
                                        return "30岁以下";
                                    } else if (r.age() >= 35) {
                                        return "35岁以上";
                                    } else {
                                        return "30~34岁";
                                    }
                                },
                                Collectors.toList()
                        )
                );
    }

    // 7. 统计全部员工的技能出现次数。
    public static Map<String, Integer> t7(List<Employee> employees) {

        return employees.stream()
                .flatMap(e -> e.skills().stream())
                .collect(Collectors.toMap(
                        Function.identity(),
                        s -> 1,
                        Integer::sum
                ));
    }

    // 8. 找出出现次数最多的技能，在第 7 题基础上，进一步找出：被最多员工掌握的技能
    public static List<String> t8(List<Employee> employees) {

        Map<String, Integer> map = t7(employees);

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

    // 9. 找出掌握 Java 但不会 Spring/SpringBoot 的员工
    public static List<Employee> t9(List<Employee> employees) {

        return employees.stream()
                .filter(e -> e.skills().contains("Java"))
                .filter(e -> !e.skills().contains("Spring"))
                .filter(e -> !e.skills().contains("SpringBoot"))
                .toList();
    }

    // 10. 部门工资总额排名
    public static Map<String, Double> t10(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingDouble(Employee::salary)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // 11. 判断部门是否存在“高薪员工” （工资 >= 20000 属于高新员工）
    public static Map<String, Boolean> t11(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .anyMatch(e -> e.salary() >= 20000)
                        )
                ));
    }

    // 12. 按部门统计员工姓名
    public static Map<String, List<String>> t12(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name, Collectors.toList())
                ));
    }

    // 13. 找出工资重复的员工
    public static Map<Double, List<String>> t13(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::salary,
                        Collectors.mapping(Employee::name, Collectors.toList())
                ));
    }

    // 14. 每个部门工资前两名
    public static Map<String, List<Employee>> t14(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                                        .limit(2)
                                        .toList()
                        )
                ));
    }

    // 15. 找出平均工资最高的部门
    public static String t15(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    // 16. 生成员工信息 Map （员工姓名 -> 工资）
    public static Map<String, Double> t16(List<Employee> employees) {

        return employees.stream()
                .collect(Collectors.toMap(
                        Employee::name,
                        Employee::salary,
                        (a, b) -> a
                ));
    }

    // 17. 技能 → 员工姓名（Java -> [张三, 李四, 王五, 赵六, 周九, 吴十, 冯十三]）
    public static Map<String, List<String>> t17(List<Employee> employees) {

        return employees.stream()
                .flatMap(e -> e.skills().stream()
                        .map(skill -> new AbstractMap.SimpleEntry<>(
                                skill,
                                e.name())
                        )
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(
                                Map.Entry::getValue,
                                Collectors.toList())
                ));
    }

    // 18. 统计各部门工资分布。将工资分成：低薪：< 15000，中薪：15000 ~ 19999，高薪：>= 20000 要求形成二级 Map：Map<String, Map<String, Long>>
    // 19. 计算部门工资极差 定义工资极差：最高工资 - 最低工资
    // 20. 每个部门中，掌握技能数量最多的员工。Map<String, Employee>。按部门分组，比较 skills.size()，技能数量相同时，工资高的人获胜，工资再相同时，年龄小的人获胜

}