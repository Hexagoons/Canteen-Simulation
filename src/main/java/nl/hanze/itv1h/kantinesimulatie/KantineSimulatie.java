package nl.hanze.itv1h.kantinesimulatie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class KantineSimulatie {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("KantineSimulatie");

    private EntityManager manager;

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen = new String[]
        {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[]{1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    public static final int MIN_ARTIKELEN_PER_SOORT = 10;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     */
    public KantineSimulatie() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        kantine = new Kantine(manager);
        random = new Random();
        int[] hoeveelheden = getRandomArray(
            AANTAL_ARTIKELEN,
            MIN_ARTIKELEN_PER_SOORT,
            MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(
            artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen
     * min en max van de gegeven lengte te genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for(int i = 0; i < lengte ;i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl)
     * en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array
     * artikelnamen de bijhorende array van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for(int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];
        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen, boolean random) {
        int[] aantal = new int[dagen];
        double[] omzet = new double[dagen];

        for(int i = 0; i < dagen; i++) {

            ArrayList<Persoon> personen = random ? randomVerdelingVanPersonen() : vasteVerdelingVanPersonen();

            for (Persoon persoon : personen) {
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON,MAX_ARTIKELEN_PER_PERSOON);
                int[] artikelNummers = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN-1);
                String[] artikelen = geefArtikelNamen(artikelNummers);

                kantine.loopPakSluitAan(persoon, artikelen);
            }

            kantine.verwerkRijVoorKassa();

            Kassa kassa = kantine.getKassa();

            printDayDetails(personen, kassa, i+1);

            aantal[i] = kassa.aantalArtikelen();
            omzet[i] = kassa.hoeveelheidGeldInKassa();

            kassa.resetKassa();
        }

        printWeekOverzicht(dagen, aantal, omzet);

        printValuesInDatabase();

        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     *
     * @return
     */
    private ArrayList<Persoon> vasteVerdelingVanPersonen() {
        ArrayList<Persoon> personen = new ArrayList<Persoon>();

        for(int i=0; i < 89; i++)
            personen.add(new Student("12312312", "Wouter", "Boersma", new Datum(6,8,1998), 'M',123456,"Architectuur", getRandomBetaalWijze()));

        for(int i=0; i < 10; i++)
            personen.add(new Docent("12312313", "Roy", "Voetman", new Datum(6,8,1998), 'M',"VOER","ABCD", getRandomBetaalWijze()));

        personen.add(new KantineMedewerker("12312314", "Keta", "Kees", new Datum(6,8,1998), 'M',21324,true, getRandomBetaalWijze()));

        return personen;
    }

    /**
     *
     * @return
     */
    private ArrayList<Persoon> randomVerdelingVanPersonen() {
        ArrayList<Persoon> personen = new ArrayList<Persoon>();

        int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

        for(int i=0; i < aantalpersonen; i++) {
            int rand = random.nextInt(100);

            if (rand < 89)
                personen.add(new Student("12312312", "Wouter", "Boersma", new Datum(6, 8, 1998), 'M', 123456, "Architectuur", getRandomBetaalWijze()));
            else if (rand < 99)
                personen.add(new Docent("12312313", "Roy", "Voetman", new Datum(6, 8, 1998), 'M', "VOER", "ABCD", getRandomBetaalWijze()));
            else
                personen.add(new KantineMedewerker("12312314", "Keta", "Kees", new Datum(6, 8, 1998), 'M', 21324, true, getRandomBetaalWijze()));

        }

        return personen;
    }

    /**
     *
     * @return
     */
    private Betaalwijze getRandomBetaalWijze() {
        Betaalwijze betaalwijze;

        if(random.nextInt(2) == 0){
            betaalwijze = new Contant();
            betaalwijze.setSaldo(((double) random.nextInt(60)));
        } else {
            Pinpas pinpas = new Pinpas();
            pinpas.setKredietLimiet(((double) random.nextInt(60)));
            betaalwijze = pinpas;
        }
        betaalwijze.setSaldo(((double) random.nextInt(60)));

        return betaalwijze;
    }

    /**
     *
     * @param personen
     * @param kassa
     * @param dag
     */
    private void printDayDetails(ArrayList<Persoon> personen, Kassa kassa, int dag) {
        int studenten = 0;
        int docenten = 0;
        int kantinemedewerkers = 0;

        System.out.printf("Dag: %d; Artikelen: %d; Geld: \u20ac%.2f\n", dag, kassa.aantalArtikelen(), kassa.hoeveelheidGeldInKassa());

        for (Persoon persoon : personen) {
            System.out.print(persoon + ", ");

            if(persoon instanceof Student)
                studenten++;
            else if(persoon instanceof Docent)
                docenten++;
            else if(persoon instanceof KantineMedewerker)
                kantinemedewerkers++;
        }

        System.out.printf("\nAantal studenten: %d, Aantal docenten: %d, Aantal kantinemedewerkers: %d \n\n", studenten, docenten, kantinemedewerkers);
    }

    /**
     *
     * @param dagen
     * @param aantal
     * @param omzet
     */
    private void printWeekOverzicht(int dagen, int[] aantal, double[] omzet) {
        System.out.print("\n");
        System.out.printf("Gemiddelde aantal artikelen: %.0f\n", Administratie.berekenGemiddeldAantal(aantal));
        System.out.printf("Gemiddelde omzet: \u20ac%.2f\n", Administratie.berekenGemiddeldeOmzet(omzet));

        System.out.print("\n");
        System.out.println("Dag omzetten:");
        double[] dag = Administratie.berekenDagOmzet(omzet);
        for(int i=0; i < dagen; i++)
            System.out.printf("%d: \u20ac%.2f\n", i+1, dag[i]);
    }

    private void printValuesInDatabase() {
        System.out.print("\n");
        System.out.print("Queries:");
        System.out.print("\n");

        Object[] totals = (Object[]) (manager.createQuery(
                "SELECT SUM(totaal), SUM(korting) FROM Factuur"
        )).getSingleResult();

        System.out.printf("Totale omzet: \u20ac%.2f\n", (double) totals[0]);
        System.out.printf("Totale gegeven korting: \u20ac%.2f\n", (double) totals[1]);

        Object[] avgs = (Object[]) (manager.createQuery(
                "SELECT AVG(totaal), AVG(korting) FROM Factuur"
        )).getSingleResult();

        System.out.printf("Gemiddelde omzet: \u20ac%.2f\n", (double) avgs[0]);
        System.out.printf("Gemiddelde gegeven korting: \u20ac%.2f\n", (double) avgs[1]);

        List invoiceTop3 = (manager.createQuery(
                "SELECT totaal FROM Factuur ORDER BY totaal DESC"
        ).setMaxResults(3)).getResultList();

        System.out.print("\n");
        System.out.println("Top 3:");
        for (Object item : invoiceTop3) {
            System.out.printf("\u20ac%.2f\n", (double) item);
        }
    }
}