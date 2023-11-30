package services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidatorService {

    /**
     * Questo metodo valida gli input per il costruttore dell'utente
     * @param dataNascita deve essere compresa tra 100 anni fa e non più di oggi
     * @param comune deve essere contenuto nella lista dei comuni
     * @param codiceFiscale deve seguire un certo formato
     * @param email deve seguire il formato delle email
     * @param tel deve seguire il formato dei numeri di telefono
     * @return true se tutti gli input sono validati
     */
    public static boolean validateUtente(LocalDate dataNascita, String comune, String codiceFiscale, String email, String tel) throws IOException {
        return validateDataNascita(dataNascita) && validateComune(comune)
                && validateEmail(email) && validateTelefono(tel) && validateCodiceFiscale(codiceFiscale);
    }

    public static boolean validateDataNascita(LocalDate dataNascita) {
        LocalDate startDate = LocalDate.now().minusYears(120);
        LocalDate endDate = LocalDate.now().minusYears(18);
        return dataNascita.isAfter(startDate) && dataNascita.isBefore(endDate);
    }

    public static boolean validateComune(String comune) throws IOException {
        List<String> righe = Files.readAllLines(Paths.get("resources/listacomuni.txt"), StandardCharsets.ISO_8859_1);
        List<String> comuni = new ArrayList<>();
        for (String riga : righe) {
            String firstSubstring = riga.substring(7);
            comuni.add(firstSubstring.substring(0, firstSubstring.indexOf(";")));
        }
        return comuni.contains(comune);
    }

    /**
     * Valida un codice fiscale in input. Un input è ritenuto valido se contiene
     * solo lettere maiuscole e numeri
     * @param codiceFiscale una stringa
     * @return true se il codice fiscale è valido secondo quanto detto sopra
     */
    public static boolean validateCodiceFiscale(String codiceFiscale) {
        return codiceFiscale.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$");
    }

    public static boolean validateEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean validateTelefono(String telefono) {
        return telefono.matches("^((00|\\+)[0-9]{2,3}[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$");
    }

    public static boolean validateConto(double costo, double money, List<Integer> owners) {
        return costo >= 0 && money >= 0 && !owners.isEmpty();
    }

    public static boolean validateFinanziamento(double money, double tasso, int durata) {
        return money >= 0 && tasso >= 0 && durata >= 0;
    }
}
