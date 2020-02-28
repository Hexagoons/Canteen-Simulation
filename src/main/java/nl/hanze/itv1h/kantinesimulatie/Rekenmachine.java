package nl.hanze.itv1h.kantinesimulatie;

import java.util.Arrays;

public class Rekenmachine {

    private Rekenmachine(){}

    /**
     * Berekend het rekenkundig gemiddelde van de param array.
     *
     * @param array
     * @return
     */
    public static double gemiddelde(double[] array) {
        double sum = 0;

        for (int i = 0; i < array.length; i++)
            sum += array[i];

        return sum / array.length;
    }

    /**
     * Berekend het rekenkundig gemiddelde van de param array.
     *
     * @param array
     * @return
     */
    public static double gemiddelde(int[] array) {
        double[] doubles = Arrays.stream(array).asDoubleStream().toArray();

        return Rekenmachine.gemiddelde(doubles);
    }
}
