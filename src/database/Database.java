package database;

import entities.Conto;
import entities.Transazione;
import entities.Utente;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database {

    private static Map<Integer, Utente> utenti = new HashMap<>();
    private static Map<Integer, Transazione> transazioni = new HashMap<>();
    private static Map<Integer, Conto> conti = new HashMap<>();

    public static void addUtente(Utente u) {
        utenti.put(u.getId(), u);
    }

    public static void addTransazione(Transazione t) { transazioni.put(t.getId(), t);}

    public static void addConto(Conto c) { conti.put(c.getId(), c); }

    public static Map<Integer, Transazione> getTransazioni() {
        return transazioni;
    }

    public static Map<Integer, Utente> getUtenti() {
        return utenti;
    }

    public static Map<Integer, Conto> getConti() {
        return conti;
    }
}
