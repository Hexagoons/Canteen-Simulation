package nl.hanze.itv1h.kantinesimulatie.runner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import nl.hanze.itv1h.kantinesimulatie.KantineSimulatie;

import java.util.Arrays;
import javax.persistence.TypedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;

/**
 * Instituut voor Communicatie, Media & IT, Hanzehogeschool Groningen 2018 - 2019
 * Project Data-analyse & Visualisation 1.4
 * Kantine Simulatie
 *
 * @author Roy Voetman
 * @author Wouter Buursma
 *
 * @version 1.0
 */
public class KantinesimulatieRunner {
    public static final int DAGEN = 7;

    /**
     * Start simulation
     *
     * @param args
     */
    public static void main(String[] args) {
        int dagen = (args.length == 0) ? DAGEN : Integer.parseInt(args[0]);

        new KantineSimulatie().simuleer(dagen, true);
    }
}
