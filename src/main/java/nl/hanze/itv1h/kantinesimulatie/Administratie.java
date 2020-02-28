package nl.hanze.itv1h.kantinesimulatie;

public class Administratie {

    private static final int DAYS_IN_WEEK = 7;

    private Administratie(){}

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
        return Rekenmachine.gemiddelde(aantal);
    }

    /**
     *
     * @param omzet
     * @return de dag omzet
     */
    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[DAYS_IN_WEEK];

        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;

            while ( omzet.length > i + DAYS_IN_WEEK * j ) {
                temp[i] += omzet[i + DAYS_IN_WEEK * j];
                j++;
            }

        }

        return temp;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        return Rekenmachine.gemiddelde(omzet);
    }

    /**
     *
     * @return test result
     */
    public static boolean testBerekenGemiddeldAantal() {
        int[] aantal = {45, 56, 34, 39, 40, 31};

        return (Administratie.berekenGemiddeldAantal(aantal) == 40.833333333333336);
    }

    /**
     *
     * @return test result
     */
    public static boolean testBerekenGemiddeldeOmzet() {
        double[] omzet = {567.70, 498.25, 458.90};

        return (Administratie.berekenGemiddeldeOmzet(omzet) == 508.2833333333333);
    }
}
