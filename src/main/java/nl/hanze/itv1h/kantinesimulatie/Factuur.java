package nl.hanze.itv1h.kantinesimulatie;

import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "factuur")
public class Factuur implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate datum;
    private double korting;
    private double totaal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factuur")
    private List<FactuurRegel> regels;

    public Factuur() {
        totaal=0;
        korting=0;
        regels = new ArrayList<>();
    }

    public Factuur(Dienblad klant, LocalDate datum){
        this();

        this.datum = datum;
        verwerkBestelling(klant);
    }

    /**
     *
     * Verwerk artikelen en pas kortingen toe.
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad klant){
        Iterator<Artikel> it = klant.getArtikelenIterator();
        Persoon persoon = klant.getKlant();

        while (it.hasNext()) {
            Artikel artikel = it.next();

            FactuurRegel regel = new FactuurRegel(this, artikel);

            regels.add(regel);

            totaal += artikel.getPrijs();
        }

        berekenKorting(persoon);
        totaal = totaal - korting;
    }

    /**
     *
     * @param persoon
     * @return
     */
    private void berekenKorting(Persoon persoon) {
        if(persoon instanceof KortingskaartHouder) {
            KortingskaartHouder kortingskaartHouder = (KortingskaartHouder) persoon;

            korting = totaal * kortingskaartHouder.geefKortingsPercentage();
            double max = kortingskaartHouder.geefMaximum();

            if(kortingskaartHouder.heeftMaximum() && korting > max)
                korting = max;
        }
    }


    /**
     * @return het totaalbedrag
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * @return de toegepaste korting
     */
    public double getKorting() {
        return korting;
    }

    @Override
    public String toString() {
         String returnValue = "Factuur{" +
                ", datum=" + datum +
                ", korting=" + korting +
                ", totaal=" + totaal;

         for(FactuurRegel regel: regels) {
             returnValue += regel.toString();
         }

         return returnValue;
    }
}
