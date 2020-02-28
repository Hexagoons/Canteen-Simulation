package nl.hanze.itv1h.kantinesimulatie;

public class KantineMedewerker extends Persoon implements KortingskaartHouder {
    private int medewerkerNummer;
    private boolean magWerkenAchterKassa;

    public KantineMedewerker(String bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkerNummer, boolean magWerkenAchterKassa, Betaalwijze betaalwijze) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht, betaalwijze);

        this.medewerkerNummer = medewerkerNummer;
        this.magWerkenAchterKassa = magWerkenAchterKassa;
    }

    public KantineMedewerker(int medewerkerNummer, boolean magWerkenAchterKassa) {
        super();

        this.medewerkerNummer = medewerkerNummer;
        this.magWerkenAchterKassa = magWerkenAchterKassa;
    }

    public int getMedewerkerNummer() {
        return medewerkerNummer;
    }

    public void setMedewerkerNummer(int medewerkerNummer) {
        this.medewerkerNummer = medewerkerNummer;
    }

    public boolean isMagWerkenAchterKassa() {
        return magWerkenAchterKassa;
    }

    public void setMagWerkenAchterKassa(boolean magWerkenAchterKassa) {
        this.magWerkenAchterKassa = magWerkenAchterKassa;
    }

    @Override
    public double geefKortingsPercentage() {
        return 0.35;
    }

    @Override
    public boolean heeftMaximum() {
        return false;
    }

    @Override
    public double geefMaximum() {
        return 0;
    }

    @Override
    public String toString() {
        return "KantineMedewerker";
    }
}
