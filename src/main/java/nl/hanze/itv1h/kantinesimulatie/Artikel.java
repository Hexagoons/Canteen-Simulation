package nl.hanze.itv1h.kantinesimulatie;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Artikel klasse
 *
 * @since 1.0
 */
@Embeddable
public class Artikel {

    protected String naam;
    protected double prijs;

    /**
     * Construeert een Artikel en initialiseert velden
     *
     * @param naam
     * @param prijs
     */
    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    /**
     * Construeert leeg Artikel
     */
    public Artikel() {

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "naam='" + naam + '\'' +
                ", prijs=" + prijs +
                '}';
    }
}