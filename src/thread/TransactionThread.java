package thread;

import database.Database;
import entities.Conto;
import entities.Transazione;
import entities.Utente;
import services.SignatureService;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.LocalDateTime;

public class TransactionThread extends Thread {

    private Utente u;
    private int id_conto_mittente;
    private int id_conto_destinatario;
    private double money;
    private Transazione transazione;

    public TransactionThread(Utente u, int id_conto_mittente, int id_conto_destinatario, double money) {
        this.u = u;
        this.id_conto_mittente = id_conto_mittente;
        this.id_conto_destinatario = id_conto_destinatario;
        this.money = money;
    }

    @Override
    public void run() {
        System.out.println("Ciao sono il thread " + Thread.currentThread().threadId() + " e parto al tempo " + System.currentTimeMillis());
        Conto conto_mittente = Database.getConti().get(id_conto_mittente);
        Conto conto_destinatario = Database.getConti().get(id_conto_destinatario);
        conto_destinatario.addMoney(money);
        conto_mittente.subtractMoney(money);
        transazione = new Transazione(id_conto_mittente, id_conto_destinatario, u.getId(), money);
        String str_transaction = String.valueOf(transazione.hashCode());
        try {
            transazione.setSignature(SignatureService.sign(str_transaction.getBytes(), u.getKeyPair().getPrivate()));
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ciao sono il thread " + Thread.currentThread().threadId() + " e termino al tempo " + System.currentTimeMillis());
        Database.addTransazione(transazione);
    }

    public Transazione getTransazione() {
        return transazione;
    }


}
