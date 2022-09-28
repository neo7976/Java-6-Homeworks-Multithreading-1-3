package one;

import java.util.Arrays;
import java.util.Random;

public class Main1 {

    public static void main(String[] args) {
        Calculator cl = new Calculator();
        int[] array = new int[10_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(array));

        System.out.println(cl.sum(array));
        System.out.println(cl.average(array));
    }
}
