package nl.hanze.itv1h.kantinesimulatie;

import java.util.Iterator;
import java.util.Stack;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;

    /**
     * Constructor
     */
    public Dienblad(Persoon persoon) {
        this.artikelen = new Stack<>();
        this.klant = persoon;
    }

    public Dienblad() {
        this.artikelen = new Stack<>();
    }

    public Persoon getKlant(){
        return klant;
    }

    public void setKlant(Persoon persoon){
        this.klant = persoon;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.push(artikel);
    }

    public Iterator<Artikel> getArtikelenIterator() {
        return artikelen.iterator();
    }

    public int getAmountOfArticles() {
        return artikelen.size();
    }
}

