package practice.stream;

import java.util.Arrays;
import java.util.List;

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

    }
}