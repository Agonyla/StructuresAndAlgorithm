package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 12:18
 * @describe: 响应报文时间
 */
public class MessageResponseTime {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取查询报文个数
        int C = scanner.nextInt();

        // 初始化回应时间为最大值
        long responseTime = Long.MAX_VALUE;

        // 处理每个查询报文
        for (int i = 0; i < C; i++) {
            // 读取主机收到报文的时间T和最大响应时间字段值M
            long T = scanner.nextLong();
            int M = scanner.nextInt();

            // 计算实际的最大响应时间
            long maxResponseTime = calculateMaxResponseTime(M);

            // 计算当前报文对应的响应截止时间
            long currentResponseDeadline = T + maxResponseTime;

            // 选择较小的响应时间
            responseTime = Math.min(responseTime, currentResponseDeadline);
        }

        // 输出结果
        System.out.println(responseTime);

        scanner.close();
    }

    // 计算最大响应时间
    private static long calculateMaxResponseTime(int maxRespCode) {
        if (maxRespCode < 128) {
            return maxRespCode;
        } else {
            // 提取exp和mant
            int exp = (maxRespCode & 0x70) >> 4;  // 取第5-7位，右移4位
            int mant = maxRespCode & 0x0F;        // 取第0-3位

            // 计算最大响应时间
            return ((mant | 0x10) << (exp + 3));
        }
    }


}
