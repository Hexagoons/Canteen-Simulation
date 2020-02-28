package nl.hanze.itv1h.kantinesimulatie;

import java.util.LinkedList;

public class KassaRij {

    private LinkedList<Dienblad> personen;

    /**
     * Constructor
     */
    public KassaRij() {
        this.personen = new LinkedList<Dienblad>();
    }

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        personen.add(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit
     * de rij verwijderen en retourneren.
     * Als er niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        return (personen.size() != 0) ? personen.remove(0) : null;
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        return (personen.size() != 0);
    }

    public LinkedList<Dienblad> getPersonen() {
        return personen;
    }
}