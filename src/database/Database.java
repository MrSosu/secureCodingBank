package database;

import entities.Utente;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database {

    private static Map<Integer, Utente> utenti = new HashMap<>();

    public static void addUtente(Utente u) {
        utenti.put(u.getId(), u);
    }

}
