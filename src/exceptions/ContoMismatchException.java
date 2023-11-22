package exceptions;

public class ContoMismatchException extends Throwable {

    @Override
    public String getMessage() {
        return "input invalido per il conto!";
    }
}
