package com.danssion.java.thread;


import java.io.*;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private static final int count = 10000;
    private static final int threadGroupCount = 5;
    private static final String inputFile = "conditionFile.txt";

    public static void generateFile() throws IOException {
        //用随机数生成10000个数据放入文件
        PrintWriter pw = new PrintWriter(new FileWriter(new File(inputFile)), true);
        Random random = new Random();
        for (int i = 0; i < count; ++i) {
            pw.write(Math.abs(random.nextInt()) % count + ",");
        }
        pw.flush();
        pw.close();
    }

    public static void main(String[] args) {
        try {
            generateFile();
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputFile)));
            String str = reader.readLine();
            reader.close();

            String[] strs = str.split(",");
            int index = 0;

            int countForEachFile = count / threadGroupCount;
            for (int i = 0; i < threadGroupCount; i++) {
                int records[] = new int[countForEachFile];
                for (int j = 0; j < countForEachFile; j++) {
                    records[j] = Integer.parseInt(strs[index]);
                    index++;
                }
                PrintGroup group = new PrintGroup(records, i);
                group.startPrint();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class PrintGroup {
    //这个线程组输出数字的个数
    private static volatile int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition oddLock = lock.newCondition();
    private Condition evenLock = lock.newCondition();

    //这个线程需要输出的数字数组
    private int records[];
    //这个线程组需要把数组输出到同一个文件，因此，共享一个writer
    //由于任意时刻只有一个线程写文件，因此，不需要同步
    private PrintWriter writer;
    //记录奇数组下标
    private volatile int oddIndex = 0;
    //记录偶数组下标
    private volatile int evenIndex = 0;
    //输出偶数的线程
    private OddPrintThread oddPrintThread;
    //输出奇数的线程
    private EvenPrintThread evenPrintThread;

    private volatile boolean first = true;
    private int[] result = new int[2000];
    private int index = 0;

    public PrintGroup(int[] records, int id) throws Exception {
        this.records = records;
        this.writer = new PrintWriter(new FileWriter(new File("output" + id + ".txt")), true);
    }

    public void startPrint() {
        oddPrintThread = new OddPrintThread();
        evenPrintThread = new EvenPrintThread();
        oddPrintThread.start();
        evenPrintThread.start();
    }

    private class OddPrintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    if (first) {
                        first = false;
                        evenLock.await();
                    }
                    for (int i = 0; i < 10; ) {
                        //数组中的偶数和奇数都打印完了
                        if (oddIndex >= records.length && evenIndex >= records.length) {
                            writer.flush();
                            writer.close();
                            return;
                        }

                        //如果所有奇数都打印完了，则让打印偶数的线程有机会运行
                        if (oddIndex >= records.length) {
                            break;
                        }

                        //把奇数输出到文件
                        if (records[oddIndex] % 2 == 1) {
                            i++;
                            writer.print(records[oddIndex] + " ");
                            result[index++] = records[oddIndex];
                            writer.flush();
                            addCount();
                        }
                        oddIndex++;
                    }
                    //打印完10个奇数后，通知偶线程运行
                    oddLock.signal();
                    //等待打印偶数线程结束
                    evenLock.await();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    oddLock.signal();
                    lock.unlock();
                }
            }
        }
    }

    private class EvenPrintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    //等待奇数线程先运行，如果这个先运行则调用evenlock.signal()
                    //然后打印奇数线程才运行，打印奇数线程会通过evenLock.await()
                    //进入休眠，此时打印奇数线程用户不会被唤醒
                    while (first) {
                        Thread.sleep(1);
                    }
                    lock.lock();
                    for (int i = 0; i < 10; ) {
                        if (oddIndex >= records.length && evenIndex >= records.length) {
                            String s = "";
                            for (int k = 0; k < 2000; k++) {
                                s += (result[k] + " ");
                            }
                            writer.flush();
                            return;
                        }

                        if (evenIndex >= records.length) {
                            break;
                        }

                        if (records[evenIndex] % 2 == 0) {
                            i++;
                            writer.print(records[evenIndex] + " ");
                            result[index++] = records[evenIndex];
                            writer.flush();
                            addCount();
                        }
                        evenIndex++;
                    }
                    evenLock.signal();
                    oddLock.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    evenLock.signal();
                    lock.unlock();
                }
            }
        }
    }

    private synchronized static void addCount() {
        count++;
        if (count % 1000 == 0) {
            System.out.println("已完成：" + count);
            if (count == 10000) {
                System.out.println("Done");
            }
        }
    }
}
