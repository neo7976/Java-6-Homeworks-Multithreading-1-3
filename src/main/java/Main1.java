import java.util.Random;

public class Main1 {

    public static void main(String[] args) {
        Calculator cl = new Calculator();
        int[] array = new int[100_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        Runnable runSum = () -> System.out.println("Доп. поток сумму: " + cl.sum(array));
        Runnable runAverage = () -> System.out.println("Доп. поток сумму: " + cl.average(array));
        Thread thread = new Thread(runSum);
        Thread thread1 = new Thread(runAverage);

        thread.start();
        thread1.start();

        System.out.println("Главный поток: " + cl.sum(array));
        System.out.println("Главный поток: " + cl.average(array));
    }
}
