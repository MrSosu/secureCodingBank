package exceptions;

public class FinanziamentoMismatchException extends Throwable {

    @Override
    public String getMessage() {
        return "Input per il finanziamento non corretto";
    }
}
