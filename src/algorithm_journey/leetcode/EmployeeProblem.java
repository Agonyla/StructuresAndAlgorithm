package algorithm_journey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/6 19:31
 * @describe:
 */


public class EmployeeProblem {


    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // write your code here......
        Employee xm = new Employee("小明", 2500);
        Employee xj = new Employee("小军", 8000);
        Employee xh = new Employee("小红", 100000);

        employees.add(xm);
        employees.add(xj);
        employees.add(xh);

        // for (Employee employee : employees) {
        //
        //     double salary = employee.getSalary();
        //     if (salary <= 3500) {
        //         String format = String.format("%.1f", 0.0);
        //         System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
        //     } else if (salary > 3500 && salary <= 4500) {
        //         double pay = (salary - 3500) * 0.1 - 105;
        //         String format = String.format("%.1f", pay);
        //         System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
        //     } else if (salary > 4500 && salary <= 9000) {
        //         double pay = (salary - 3500) * 0.2 - 555;
        //         String format = String.format("%.1f", pay);
        //         System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
        //     } else if (salary > 9000 && salary <= 35000) {
        //         double pay = (salary - 3500) * 0.25 - 1055;
        //         String format = String.format("%.1f", pay);
        //         System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
        //     } else if (salary > 35000 && salary <= 55000) {
        //         double pay = (salary - 3500) * 0.3 - 2755;
        //         String format = String.format("%.1f", pay);
        //         System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
        //     } else if (salary > 55000 && salary <= 80000) {
        //         double pay = (salary - 3500) * 0.35 - 5505;
        //         String format = String.format("%.1f", pay);
        //         System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
        //     } else if (salary > 80000) {
        //         double pay = (salary - 3500) * 0.45 - 13505;
        //         String format = String.format("%.1f", pay);
        //         System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
        //     }
        // }


        employees.forEach(
                employee -> {
                    double salary = employee.getSalary();
                    if (salary <= 3500) {
                        String format = String.format("%.1f", 0.0);
                        System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
                    } else if (salary > 3500 && salary <= 4500) {
                        double pay = (salary - 3500) * 0.1 - 105;
                        String format = String.format("%.1f", pay);
                        System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
                    } else if (salary > 4500 && salary <= 9000) {
                        double pay = (salary - 3500) * 0.2 - 555;
                        String format = String.format("%.1f", pay);
                        System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
                    } else if (salary > 9000 && salary <= 35000) {
                        double pay = (salary - 3500) * 0.25 - 1055;
                        String format = String.format("%.1f", pay);
                        System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
                    } else if (salary > 35000 && salary <= 55000) {
                        double pay = (salary - 3500) * 0.3 - 2755;
                        String format = String.format("%.1f", pay);
                        System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
                    } else if (salary > 55000 && salary <= 80000) {
                        double pay = (salary - 3500) * 0.35 - 5505;
                        String format = String.format("%.1f", pay);
                        System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
                    } else if (salary > 80000) {
                        double pay = (salary - 3500) * 0.45 - 13505;
                        String format = String.format("%.1f", pay);
                        System.out.println(employee.getName() + "应该缴纳的个人所得税是: " + format);
                    }
                }
        );

    }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

