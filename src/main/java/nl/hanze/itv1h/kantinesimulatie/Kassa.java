package nl.hanze.itv1h.kantinesimulatie;

import nl.hanze.itv1h.kantinesimulatie.exceptions.TeWeinigGeldException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.Iterator;

public class Kassa {

    private KassaRij kassaRij;
    private EntityManager manager;

    private int aantalArtikelen = 0;
    private double omzet = 00.00;
    private double gegevenKorting = 00.00;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager manager) {
        this.kassaRij = kassarij;
        this.manager = manager;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op.
     * Tel deze gegevens op bij de controletotalen die voor
     * de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Iterator<Artikel> it = klant.getArtikelenIterator();
        Persoon persoon = klant.getKlant();
        Betaalwijze betaalwijze = persoon.getBetaalwijze();

        aantalArtikelen += klant.getAmountOfArticles();

        Factuur factuur = new Factuur(klant, LocalDate.now());

        double total = factuur.getTotaal();
        gegevenKorting += factuur.getKorting();

        EntityTransaction transaction = null;
        try {
            betaalwijze.betaal(total);
            omzet += total;

            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(factuur);
            transaction.commit();
        } catch (TeWeinigGeldException e) {
            System.out.println("Deze persoon (" + persoon.getNaam() + ") kan niet betalen.");

            if (transaction != null)
                transaction.rollback();
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd,
     * vanaf het moment dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return aantalArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass
     * zijn gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return omzet;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en
     * de totale hoeveelheid geld in de kassa.
     */
    public void resetKassa() {
        aantalArtikelen = 0;
        omzet = 00.00;
    }
}