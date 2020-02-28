package nl.hanze.itv1h.kantinesimulatie;

import javax.persistence.*;

@Entity
@Table(name = "factuur_regel")
public class FactuurRegel {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Factuur factuur;

    @Embedded
    private Artikel artikel;

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    @Override
    public String toString() {
        return "FactuurRegel{" + artikel.toString() + "}";
    }
}
