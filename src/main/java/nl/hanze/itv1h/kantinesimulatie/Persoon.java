package nl.hanze.itv1h.kantinesimulatie;

public class Persoon {
    private String  bsn;
    private String  voornaam;
    private String  achternaam;
    private Datum   geboortedatum;
    private char    geslacht;
    private Betaalwijze betaalwijze;

    public Persoon(String bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, Betaalwijze betaalwijze) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.setGeslacht(geslacht);
        this.betaalwijze = betaalwijze;
    }

    public Persoon() {
        this.geboortedatum = new Datum(0,0,0);
        this.setGeslacht('x');
    }

    public String getBsn() {
        return bsn;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum.getDateAsString();
    }

    public String getGeslacht() {
        if (geslacht == 'M')
            return "Man";
        if (geslacht == 'V')
            return "Vrouw";
        if (geslacht == 'N')
            return "Neutraal";
        return "Onbekend";

    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    /**
     * Checkt of het meegegeven geslacht M,V of N is, zoniet wordt dit gelijkgesteld aan X. Oftewel onbekend.
     * @param geslacht
     */
    public void setGeslacht(char geslacht) {
        geslacht = Character.toUpperCase(geslacht);

        if (geslacht == 'M' || geslacht == 'V' || geslacht == 'N')
            this.geslacht = geslacht;
        else
            this.geslacht = 'X';

    }

    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    public void setBetaalwijze(Betaalwijze betaalwijze) {
        this.betaalwijze = betaalwijze;
    }

    public String getNaam() {
        return voornaam + " " + achternaam;
    }

    @Override
    public String toString() {
        return "Persoon{" +
                "bsn='" + bsn + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", geslacht=" + geslacht +
                ", betaalwijze=" + betaalwijze +
                '}';
    }
}
