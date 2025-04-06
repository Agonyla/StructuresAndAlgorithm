package algorithm_journey.leetcode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/6 20:01
 * @describe:
 */
public class Time {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        String[] times = str.split("\\s");

        int year = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int day = Integer.parseInt(times[2]);
        int hour = Integer.parseInt(times[3]);
        int minutes = Integer.parseInt(times[4]);
        int second = Integer.parseInt(times[5]);

        LocalDateTime beijing = LocalDateTime.of(year, 1, 1, 0, 0, 0)
                .plusMonths(month - 1)
                .plusDays(day - 1)
                .plusHours(hour)
                .plusMinutes(minutes)
                .plusSeconds(second);

        // ZonedDateTime beijingTime = beijing.atZone(ZoneId.of("Asia/Shanghai"));

        // ZonedDateTime newYorkTime = beijingTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalDateTime newYork = beijing.minusHours(12);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("北京时间为：" + beijing.format(formatter));
        System.out.println("纽约时间为：" + newYork.format(formatter));
    }
}
