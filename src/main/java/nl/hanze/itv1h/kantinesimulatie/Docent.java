package nl.hanze.itv1h.kantinesimulatie;

public class Docent extends Persoon implements KortingskaartHouder {
    private String afkorting;
    private String afdeling;

    public Docent(String bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String afkorting, String afdeling, Betaalwijze betaalwijze) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht, betaalwijze);

        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    public Docent(String afkorting, String afdeling) {
        super();

        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    public String getAfkorting() {
        return afkorting;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    @Override
    public double geefKortingsPercentage() {
        return 0.25;
    }

    @Override
    public boolean heeftMaximum() {
        return true;
    }

    @Override
    public double geefMaximum() {
        return 1;
    }

    @Override
    public String toString() {
        return "Docent";
    }
}
