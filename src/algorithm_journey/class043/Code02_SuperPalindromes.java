package algorithm_journey.class043;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * 超级回文数
 *
 * @author: Agony
 * @create: 2024/7/9 09:17
 * @describe: 如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为超级回文数。
 * <p>
 * 现在，给定两个正整数 L 和 R （以字符串形式表示），返回包含在范围 [L, R] 中的超级回文数的数目。
 * 输入：L = "4", R = "1000"
 * 输出：4
 * 解释：
 * 4，9，121，以及 484 是超级回文数。
 * 注意 676 不是一个超级回文数： 26 * 26 = 676，但是 26 不是回文数。W
 * @link: <a href="https://leetcode.cn/problems/super-palindromes/description/">超级回文数</a>
 */
public class Code02_SuperPalindromes {


    public static void main(String[] args) {
        long a = Long.MAX_VALUE;
        System.out.println(a);

        System.out.println(Integer.MAX_VALUE);

        System.out.println(evenLength(123));
        System.out.println(oddLength(123));
        System.out.println(evenLength(12));
        System.out.println(oddLength(12));
        System.out.println(evenLength(2));
        System.out.println(oddLength(2));

        System.out.println(sqrt(9));

        long num = 123321L * 123321;
        long tmp = 1;
        while (num / tmp >= 10) {
            tmp *= 10;
        }
        System.out.println(num);
        System.out.println(tmp);

        System.out.println("===========================");


        System.out.println(superpalindromesInRange("4", "1000"));

        System.out.println("========打印所有的超级回文数============");


        List<Long> lists = collectAllSuperPalindromesInRange();

        lists.forEach(n -> System.out.println(n + "L,"));

        System.out.println(superpalindromesInRangeBetter("4", "1000"));

    }

    // 超级回文数的数目

    // 思路
    // 题目给的数据量位 10^18, 如果直接遍历，数据量太大了，肯定会超时
    // 直接在原数据量上开根号，在 1-10^9 上遍历，还是有点大
    // 枚举前一半的数据状况，它是基础长度还是偶数长度的回文
    // 如，前一半的数据状况为 123，它的回文有
    // a = 12321 和 b = 123321
    // 然后a和b平方判断是否在题目给出的范围上，再判断平方后的结果是否是回文
    // -> 将规模缩小到了 10^5
    // 以奇数长度的回文不超过 根号x的限制
    // 如 题目给出范围为 x=10^10
    // 根号x=10^5
    // seed=123
    // 奇数长度 12321 -> 10^5
    // 偶数长度 123321 -> 10^6
    // 此时不能停下，还要继续
    // seed=124 -> 奇数长度 12421，仍然在 10^5 范围内

    // 生成奇数长度的回文 123 为例，奇数长度需要先除10，然后和偶数一样
    // ans=123
    // tmp = 123
    // /10 得到 tmp=12
    // %10 -> 2
    // ans = ans * 10 + 2 -> 1232
    // tmp=12
    // /10 -> tmp=1
    // %10 -> 1
    // ans = ans * 10 + 1 -> 12321

    // 生成偶数唱的回文 123 为例
    // tmp=123,ans=123
    // tmp%10 -> 3
    // ans = ans *10+3 = 1233
    // tmp=tmp/10
    // ...

    // 判断回文
    // 1. 可以直接转成字符串然后判断
    // 2.
    // 如 num=52725
    // 先得到一个和num长度一致的offset=10000
    // num/offset -> 5
    // num%10 -> 5
    // 首位一致
    // num=(num%offset)/10 = 272
    // offset=offset/100 = 100
    // num/offset -> 2
    // num%10 -> 2
    // 首位一致
    // num=(num%offset)/10 = 7
    // offset=offset/100 = 1
    // num/offset -> 7
    // num%10 -> 7
    // 一致

    // num=527725
    // offset=100000
    // num=2772
    // offset=1000
    // num=77
    // offset=10
    // num=0


    /**
     * 求左右边界内超级回文数的数目
     *
     * @param left  左边界
     * @param right 右边界
     * @return 超级回文数的数目
     */
    public static int superpalindromesInRange(String left, String right) {

        long l = Long.parseLong(left);
        long r = Long.parseLong(right);

        long limit = (long) Math.sqrt(r);

        long num = 0;
        long seed = 1;
        int ans = 0;

        do {
            num = evenLength(seed);
            if (check(num * num, l, r)) {
                ans++;
            }
            num = oddLength(seed);
            if (check(num * num, l, r)) {
                ans++;
            }
            seed++;
        } while (num < limit);


        return ans;
    }


    /**
     * 返回偶数长度的回文
     *
     * @param num
     * @return
     */
    public static long evenLength(long num) {
        long ans = num;
        long tmp = num;
        while (tmp != 0) {
            ans = ans * 10 + tmp % 10;
            tmp /= 10;
        }
        return ans;
    }


    /**
     * 返回奇数长度的回文
     *
     * @param num
     * @return
     */
    public static long oddLength(long num) {
        long ans = num;
        long tmp = num / 10;
        while (tmp != 0) {
            ans = ans * 10 + tmp % 10;
            tmp /= 10;
        }
        return ans;
    }


    /**
     * 检查num是否是回文，且在[left, right]区间内
     *
     * @param num
     * @param left
     * @param right
     * @return
     */
    public static boolean check(long num, long left, long right) {
        if (num < left || num > right) {
            return false;
        }

        // 获得一个和num长度相等的10的幂
        long offset = 1;

        while (num / offset >= 10) {
            offset *= 10;
        }

        // 判断num的第一个和最后一个数字书否相等
        while (num != 0) {
            if (num / offset != num % 10) {
                return false;
            }
            num = num % offset / 10;
            offset /= 100;
        }
        return true;
    }


    /**
     * 搜集所有的超级回文数
     *
     * @return
     */
    public static List<Long> collectAllSuperPalindromesInRange() {
        long left = 1;
        long right = Long.MAX_VALUE;
        long limit = (long) Math.sqrt(right);
        ArrayList<Long> list = new ArrayList<>();
        long num = 0;
        long seed = 1;
        do {
            num = evenLength(seed);
            if (check(num * num, left, right)) {
                list.add(num * num);
            }
            num = oddLength(seed);
            if (check(num * num, left, right)) {
                list.add(num * num);
            }
            seed++;
        } while (num < limit);
        list.sort(Long::compareTo);
        return list;

    }


    public static long[] records = {
            1L,
            4L,
            9L,
            121L,
            484L,
            10201L,
            12321L,
            14641L,
            40804L,
            44944L,
            1002001L,
            1234321L,
            4008004L,
            100020001L,
            102030201L,
            104060401L,
            121242121L,
            123454321L,
            125686521L,
            400080004L,
            404090404L,
            10000200001L,
            10221412201L,
            12102420121L,
            12345654321L,
            40000800004L,
            1000002000001L,
            1002003002001L,
            1004006004001L,
            1020304030201L,
            1022325232201L,
            1024348434201L,
            1210024200121L,
            1212225222121L,
            1214428244121L,
            1232346432321L,
            1234567654321L,
            4000008000004L,
            4004009004004L,
            100000020000001L,
            100220141022001L,
            102012040210201L,
            102234363432201L,
            121000242000121L,
            121242363242121L,
            123212464212321L,
            123456787654321L,
            400000080000004L,
            10000000200000001L,
            10002000300020001L,
            10004000600040001L,
            10020210401202001L,
            10022212521222001L,
            10024214841242001L,
            10201020402010201L,
            10203040504030201L,
            10205060806050201L,
            10221432623412201L,
            10223454745432201L,
            12100002420000121L,
            12102202520220121L,
            12104402820440121L,
            12122232623222121L,
            12124434743442121L,
            12321024642012321L,
            12323244744232321L,
            12343456865434321L,
            12345678987654321L,
            40000000800000004L,
            40004000900040004L,
            1000000002000000001L,
            1000220014100220001L,
            1002003004003002001L,
            1002223236323222001L,
            1020100204020010201L,
            1020322416142230201L,
            1022123226223212201L,
            1022345658565432201L,
            1210000024200000121L,
            1210242036302420121L,
            1212203226223022121L,
            1212445458545442121L,
            1232100246420012321L,
            1232344458544432321L,
            1234323468643234321L,
            4000000008000000004L
    };


    /**
     * 优化：直接把所有的超级回文数记录在表里
     * 返回在区间内的个数
     *
     * @param left
     * @param right
     * @return
     */
    public static int superpalindromesInRangeBetter(String left, String right) {
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        int i = 0;
        for (; i < records.length; i++) {
            if (records[i] >= l) {
                break;
            }
        }
        int j = records.length - 1;
        for (; j >= 0; j--) {
            if (records[j] <= r) {
                break;
            }
        }
        return j - i + 1;
    }


}


























