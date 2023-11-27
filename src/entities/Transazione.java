package entities;

import java.security.Signature;
import java.time.LocalDateTime;

public class Transazione {

    private static int idCounter = 0;
    private int id;
    private final LocalDateTime timestamp;
    private final int id_mittente; // ---> conto
    private final int id_destinatario; // ---> conto
    private final int id_utente; // ---> utente
    private byte[] signature;
    private final double money;

    public Transazione(int id_mittente, int id_destinatario, int id_utente, double money) {
        this.id = idCounter++;
        this.timestamp = LocalDateTime.now();
        this.id_mittente = id_mittente;
        this.id_destinatario = id_destinatario;
        this.id_utente = id_utente;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getId_mittente() {
        return id_mittente;
    }

    public int getId_destinatario() {
        return id_destinatario;
    }

    public int getId_utente() {
        return id_utente;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Transazione{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", id_mittente=" + id_mittente +
                ", id_destinatario=" + id_destinatario +
                ", id_utente=" + id_utente +
                ", signature=" + signature +
                ", money=" + money +
                '}';
    }
}
