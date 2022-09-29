package reverse;

import calculate.Calculator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ValueSumCounter extends RecursiveTask<Long> {

    private int[] array;
    Calculator calc = new Calculator();

    public ValueSumCounter(int[] array) {
        this.array = array;
    }

    @Override
    protected Long compute() {
        if (array.length <= 2) {
//            try {
//                Thread.sleep(1);
//                System.out.printf("Task %s execute in thread %s%n", this, Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return Arrays.stream(array).asLongStream().sum();
            return calc.sum(array);
        }
        ValueSumCounter firsHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, 0, array.length / 2));
        ValueSumCounter secondHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, array.length / 2, array.length));
        firsHalfArrayValueSumCounter.fork();
        secondHalfArrayValueSumCounter.fork();
        return firsHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join();
    }
}
