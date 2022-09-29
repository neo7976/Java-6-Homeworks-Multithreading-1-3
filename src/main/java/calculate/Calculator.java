package calculate;

public class Calculator {

    public long sum(int[] array) {
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum;
    }

    public double average(int[] array) {
        long sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum / array.length;
    }

}
