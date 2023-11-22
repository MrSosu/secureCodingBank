package exceptions;

public class UtenteMismatchException extends Throwable {

    @Override
    public String getMessage() {
        return "Input invalido per l'utente";
    }
}
