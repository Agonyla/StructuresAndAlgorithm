package algorithm_journey.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author: Agony
 * @create: 2025/4/15 19:20
 * @describe:
 */
public class Main {


    private static class NumberGenerator {


        private int secretNumber;
        private int guess;
        private String threadName;
        private String feedback = "";
        private boolean gameOver = false;
        private boolean newGuess = false;

        public void generateNumber() {
            Random random = new Random();
            secretNumber = random.nextInt(101); // 0-100之间的随机数
            System.out.println("线程A生成的随机数是: " + secretNumber);
        }

        public synchronized void makeGuess(int guess, String threadName) {
            
            if (gameOver) {
                return;
            }
            // 设置猜测值和线程名
            this.guess = guess;
            this.threadName = threadName;
            this.newGuess = true;

            // 通知线程A有新的猜测
            notify();

            // 等待反馈
            try {
                while (feedback.isEmpty() && newGuess) {
                    wait();
                }
                feedback = ""; // 清除反馈，准备下一轮
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized void waitForGuess() {
            // 等待有新的猜测
            try {
                while (!newGuess) {
                    wait();
                }

                // 处理猜测结果
                if (guess == secretNumber) {
                    feedback = "猜对了";
                    gameOver = true;
                } else if (guess < secretNumber) {
                    feedback = "猜小了";
                } else {
                    feedback = "猜大了";
                }

                System.out.println(threadName + " 猜测: " + guess + " - " + feedback);
                newGuess = false;

                // 通知猜测线程结果
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized boolean isGameOver() {
            return gameOver;
        }

        public synchronized String getWinner() {
            return threadName;
        }
    }

    public static void main(String[] args) {
        final NumberGenerator numberGenerator = new NumberGenerator();
        final CountDownLatch startSignal = new CountDownLatch(1);

        // 线程A - 生成随机数并评估猜测结果
        Thread threadA = new Thread(() -> {
            numberGenerator.generateNumber();
            startSignal.countDown();

            while (!numberGenerator.isGameOver()) {
                numberGenerator.waitForGuess();
            }

            System.out.println("游戏结束！" + numberGenerator.getWinner() + " 猜中了数字 " +
                    "（答案是: " + numberGenerator.secretNumber + "）");
        }, "线程A");

        // 线程B - 猜数字
        Thread threadB = new Thread(() -> {
            try {
                startSignal.await(); // 等待线程A生成随机数
                Random random = new Random();
                int min = 0;
                int max = 100;

                while (!numberGenerator.isGameOver()) {
                    int guess = min + random.nextInt(max - min + 1);
                    System.out.println("线程B 猜: " + guess);
                    numberGenerator.makeGuess(guess, "线程B");

                    // 根据反馈调整猜测范围
                    synchronized (numberGenerator) {
                        if (numberGenerator.feedback.equals("猜小了")) {
                            min = guess + 1;
                        } else if (numberGenerator.feedback.equals("猜大了")) {
                            max = guess - 1;
                        }
                    }

                    // 稍微暂停，让其他线程有机会
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "线程B");

        // 线程C - 猜数字
        Thread threadC = new Thread(() -> {
            try {
                startSignal.await(); // 等待线程A生成随机数
                Random random = new Random();
                int min = 0;
                int max = 100;

                while (!numberGenerator.isGameOver()) {
                    int guess = min + random.nextInt(max - min + 1);
                    System.out.println("线程C 猜: " + guess);
                    numberGenerator.makeGuess(guess, "线程C");

                    // 根据反馈调整猜测范围
                    synchronized (numberGenerator) {
                        if (numberGenerator.feedback.equals("猜小了")) {
                            min = guess + 1;
                        } else if (numberGenerator.feedback.equals("猜大了")) {
                            max = guess - 1;
                        }
                    }

                    // 稍微暂停，让其他线程有机会
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "线程C");

        // 启动所有线程
        threadA.start();
        threadB.start();
        threadC.start();

        // 等待所有线程结束
        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
