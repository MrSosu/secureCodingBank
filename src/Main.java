import database.Database;
import entities.Conto;
import entities.Transazione;
import entities.Utente;
import exceptions.ContoMismatchException;
import exceptions.UtenteMismatchException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, UtenteMismatchException, ContoMismatchException {
        /*System.out.println(Validator.validateComune("Roma"));
        System.out.println(Validator.validateComune("Pippo"));
        System.out.println(Validator.validateCodiceFiscale("PCGAAA87SD"));
        System.out.println(Validator.validateCodiceFiscale("ABCDEFGHIA12A"));
        System.out.println(Validator.validateCodiceFiscale("ABCDEFGhIA12345A"));
        System.out.println(Validator.validateCodiceFiscale("ABC.EFGHIA12345A")); */
        Utente u = new Utente("Marco", "Adriani", LocalDate.of(1993, 9, 17), "via Fasulla 123", "Roma", "ABCDFG45A73P883B", "marco@gmail.com", "369852147");
        Utente u2 = new Utente("Anna", "Rossi", LocalDate.of(1985, 5, 2), "via Evergreen Terrace 19", "Milano", "ABCDFG45A73P883B", "anna@gmail.com", "321456879");
        Database.addUtente(u);
        Database.addUtente(u2);
        List<Integer> utenti_c1 = new ArrayList<>();
        List<Integer> utenti_c2 = new ArrayList<>();
        utenti_c1.add(u.getId());
        utenti_c2.add(u2.getId());
        Conto c1 = new Conto(0.5, 1000, utenti_c1);
        Conto c2 = new Conto(0.5, 800, utenti_c2);
        u.addConto(c1.getId());
        u2.addConto(c2.getId());
        Database.addConto(c1);
        Database.addConto(c2);
        Transazione t = u.createTransaction(150, c1.getId(), c2.getId());
        System.out.println(t);
        System.out.println("Il conto di u adesso ha: " + c1.getMoney());
        System.out.println("Il conto di u2 adesso ha: " + c2.getMoney());
        System.out.println(u.getKeyPair());
    }

}
