import java.util.Random;

public class Main1 {

    public static void main(String[] args) {
        Calculator cl = new Calculator();
        int[] array = new int[100_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        Runnable runSum = () -> {
            long startTime = System.currentTimeMillis();
            System.out.println("Доп. поток сумма: " + cl.sum(array));
            long endTime = System.currentTimeMillis();
            System.out.printf("Время первого потока - %d\n", endTime-startTime);
        };

        Runnable runAverage = () -> {
            long startTime = System.currentTimeMillis();
            System.out.println("Доп. поток среднее: " + cl.average(array));
            long endTime = System.currentTimeMillis();
            System.out.printf("Время второго потока - %d\n", endTime-startTime);
        };
        Thread thread = new Thread(runSum);
        Thread thread1 = new Thread(runAverage);

        thread.start();
        thread1.start();

        long startTime = System.currentTimeMillis();
        System.out.println("Главный поток сумма: " + cl.sum(array));
        System.out.println("Главный поток среднее: " + cl.average(array));
        long endTime = System.currentTimeMillis();
        System.out.printf("Время основного потока - %d\n", endTime-startTime);
    }
}
