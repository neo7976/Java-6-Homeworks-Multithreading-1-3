package reverse;

import calculate.Calculator;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ValueAverageCounter extends RecursiveTask<Double> {
    private int[] array;
    Calculator calc = new Calculator();

    public ValueAverageCounter(int[] array) {
        this.array = array;
    }

    @Override
    protected Double compute() {
        if (array.length <= 2) {
            return calc.average(array);
        }
        ValueAverageCounter valueAverageCounter1 = new ValueAverageCounter(Arrays.copyOfRange(array, 0, array.length / 2));
        ValueAverageCounter valueAverageCounter2 = new ValueAverageCounter(Arrays.copyOfRange(array, array.length / 2, array.length));
        valueAverageCounter1.fork();
        valueAverageCounter2.fork();
        return valueAverageCounter1.join() + valueAverageCounter2.join();
    }
}
