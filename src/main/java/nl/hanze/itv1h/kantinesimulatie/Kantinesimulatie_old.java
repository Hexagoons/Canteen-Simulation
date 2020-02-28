//package nl.hanze.itv1h.kantinesimulatie;
//
///**
// * Begin punt van de applicatie
// *
// * @since 1.0
// */
//public class Kantinesimulatie_old {
//
//    private Kantine kantine;
//
//    /**
//     * Construeert Kantinesimulatie_old
//     */
//    public Kantinesimulatie_old() {
//        kantine = new Kantine();
//    }
//
//    /**
//     * Deze methode simuleert het aantal dagen in de loop van de kantine
//     *
//     * @param dagen
//     */
//    public void simulate(int dagen) {
//
//        for (int i = 0; i < dagen; i++) {
//
//            // for customers
//            for(int j = 0; j < 10 + i; j++){
//                //kantine.loopPakSluitAan();
//            }
//
//            Kassa kassa = kantine.getKassa();
//
//            kantine.verwerkRijVoorKassa();
//
//            System.out.printf("Dag: %d; Artikelen: %d; Geld: \u20ac%.2f\n", i, kassa.aantalArtikelen(), kassa.hoeveelheidGeldInKassa());
//
//            kassa.resetKassa();
//        }
//    }
//}