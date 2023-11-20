package validators;

import java.time.LocalDate;

public class Validator {

    public static boolean validateUtente(LocalDate dataNascita, String comune, String codiceFiscale, String email, String tel) {
        return true;
    }

    public static boolean validateConto(double costo, double money) {
        return true;
    }

    public static boolean validateFinanziamento(double money, double tasso, int durata) {
        return true;
    }
}
