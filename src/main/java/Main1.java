import calculate.Calculator;
import reverse.ValueAverageCounter;
import reverse.ValueSumCounter;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main1 {

    public static void main(String[] args) {
        Calculator cl = new Calculator();
        int[] array = new int[10_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        Runnable runSum = () -> {
            long startTime = System.currentTimeMillis();
            System.out.println("Доп. поток сумма: " + cl.sum(array));
            long endTime = System.currentTimeMillis();
            System.out.printf("Время первого потока - %d\n", endTime - startTime);
            Runnable runAverage = () -> {
                long startTime1 = System.currentTimeMillis();
                System.out.println("Доп. поток среднее: " + cl.average(array));
                long endTime1 = System.currentTimeMillis();
                System.out.printf("Время второго потока - %d\n", endTime1 - startTime1);
            };
            Thread thread1 = new Thread(runAverage);
            thread1.start();
        };

        Thread thread = new Thread(runSum);
        thread.start();

        long startTime = System.currentTimeMillis();
        System.out.println("Главный поток сумма: " + cl.sum(array));
        System.out.println("Главный поток среднее: " + cl.average(array));
        long endTime = System.currentTimeMillis();
        System.out.printf("Время основного потока - %d\n", endTime - startTime);

        //пробуем решить задачу с помощью пула суммы
        ValueSumCounter counter = new ValueSumCounter(array);
        ForkJoinPool pool1 = new ForkJoinPool();
        long startTime1 = System.currentTimeMillis();
        System.out.println("Сумма пулового потока: " + pool1.invoke(counter));
        long endTime1 = System.currentTimeMillis();
        System.out.printf("Время пулового потока - %d\n", endTime1 - startTime1);

        //пробуем решить задачу с помощью пула среднего значения
        ValueAverageCounter averageCounter = new ValueAverageCounter(array);
        ForkJoinPool pool2 = new ForkJoinPool();
        long startTime2 = System.currentTimeMillis();
        System.out.println("Среднее значение пула: " + pool2.invoke(averageCounter));
        long endTime2 = System.currentTimeMillis();
        System.out.printf("Время второго пулового потока - %d\n", endTime2 - startTime2);

    }
}
