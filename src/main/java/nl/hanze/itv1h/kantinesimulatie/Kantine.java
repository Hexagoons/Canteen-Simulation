package nl.hanze.itv1h.kantinesimulatie;

import javax.persistence.EntityManager;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineAanbod;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kantine(EntityManager manager) {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij, manager);
        this.manager = manager;
    }

//    Week 2 opdracht 1.
//    /**
//     * In deze methode wordt een Persoon en Dienblad gemaakt
//     * en aan elkaar gekoppeld. Maak twee Artikelen aan
//     * en plaats deze op het dienblad. Tenslotte sluit de
//     * Persoon zich aan bij de rij voor de kassa.
//     */
//    public void loopPakSluitAan() {
//        Persoon persoon = new Persoon("9992193239", "Pietje", "Bel", new Datum(1, 1, 1998), 'M');
//        Dienblad dienblad = new Dienblad(persoon);
//
//        dienblad.voegToe(new Artikel("Appel", 1.00));
//        dienblad.voegToe(new Artikel("Banaan", 0.50));
//
//        kassarij.sluitAchteraan(dienblad);
//    }

     /**
      * In deze methode kiest een Persoon met een dienblad
      * de artikelen in artikelnamen.
      *
      * @param persoon
      * @param artikelnamen
      */
     public void loopPakSluitAan(Persoon persoon, String[] artikelnamen) {
         Dienblad dienblad = new Dienblad(persoon);

         for (String artikelnaam: artikelnamen) {
             Artikel artikel = kantineAanbod.getArtikel(artikelnaam);

             if(artikel != null)
                dienblad.voegToe(artikel);
         }

         kassarij.sluitAchteraan(dienblad);
     }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while(kassarij.erIsEenRij()) {
            Dienblad persoon = kassarij.eerstePersoonInRij();

            kassa.rekenAf(persoon);
        }
    }

    public Kassa getKassa() {
        return kassa;
    }

    public KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }

    public void setKantineAanbod(KantineAanbod kantineAanbod) {
        this.kantineAanbod = kantineAanbod;
    }
}