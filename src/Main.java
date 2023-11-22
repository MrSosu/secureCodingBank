import entities.Utente;
import exceptions.UtenteMismatchException;
import validators.Validator;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException, UtenteMismatchException {
        /*System.out.println(Validator.validateComune("Roma"));
        System.out.println(Validator.validateComune("Pippo"));
        System.out.println(Validator.validateCodiceFiscale("PCGAAA87SD"));
        System.out.println(Validator.validateCodiceFiscale("ABCDEFGHIA12A"));
        System.out.println(Validator.validateCodiceFiscale("ABCDEFGhIA12345A"));
        System.out.println(Validator.validateCodiceFiscale("ABC.EFGHIA12345A")); */
        Utente u = new Utente("Marco", "Adriani", LocalDate.of(1993, 9, 17), "via Fasulla 123", "abababab", "ahfgwe", "fhfuierhufw", "gerhgherwoi");

    }

}
