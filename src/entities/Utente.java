package entities;

import database.Database;
import exceptions.UtenteMismatchException;
import services.IDService;
import services.SignatureService;
import services.ValidatorService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utente {

    private static int idCounter = 0;
    private int id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String indirizzo;
    private String comune;
    private String codiceFiscale;
    private String email;
    private String password;
    private String tel;
    private boolean isLoggedIn;
    private final KeyPair keyPair;
    private List<Integer> conti_utente;

    public Utente(String nome, String cognome, LocalDate dataNascita,
                  String indirizzo, String comune, String codiceFiscale, String email, String tel, String password) throws IOException, UtenteMismatchException {
            if (!ValidatorService.validateUtente(dataNascita, comune, codiceFiscale, email, tel)) {
                System.out.println("non valido!");
                throw new UtenteMismatchException();
            }
            this.id = idCounter++;
            this.nome = nome;
            this.cognome = cognome;
            this.dataNascita = dataNascita;
            this.indirizzo = indirizzo;
            this.comune = comune;
            this.codiceFiscale = codiceFiscale;
            this.email = email;
            this.password = password;
            this.tel = tel;
            this.keyPair = SignatureService.keyPair();
            this.conti_utente = new ArrayList<>();
            this.isLoggedIn = false;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) throws UtenteMismatchException {
        if (!ValidatorService.validateDataNascita(dataNascita)) {
            throw new UtenteMismatchException();
        }
        this.dataNascita = dataNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) throws UtenteMismatchException, IOException {
        if (!ValidatorService.validateComune(comune)) {
            throw new UtenteMismatchException();
        }
        this.comune = comune;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) throws UtenteMismatchException {
        if (!ValidatorService.validateCodiceFiscale(codiceFiscale)) {
            throw new UtenteMismatchException();
        }
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws UtenteMismatchException {
        if (!ValidatorService.validateEmail(email)) {
            throw new UtenteMismatchException();
        }
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) throws UtenteMismatchException {
        if (!ValidatorService.validateTelefono(tel)) {
            throw new UtenteMismatchException();
        }
        this.tel = tel;
    }

    public List<Integer> getConti_utente() {
        return conti_utente;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    // funzionalità

    public void addConto(Integer id_conto) {
        if (!isLoggedIn) IDService.login();
        conti_utente.add(id_conto);
    }

    /**
     * Questo metodo permette all'utente di effettuare una transazione da un
     * suo conto ad un altro conto (anche appartanente sempre a lui).
     * @param money il quantitativo di denaro spostato
     * @param id_conto_mittente l'id del conto del mittente
     * @param id_conto_destinatario l'id del conto del destinatario
     * @return la transazione
     */
    public Transazione createTransaction(double money, int id_conto_mittente, int id_conto_destinatario) throws UnsupportedEncodingException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
        // input validation
        if (!isLoggedIn) {
            System.out.println("Devi essere loggato per poter eseguire questa funzionalità!");
            IDService.login();
        }
        if (money <= 0) {
            throw new IllegalArgumentException("la transazione non può essere senza denaro!");
        }
        if (!Database.getConti().containsKey(id_conto_mittente) ||
                !Database.getConti().containsKey(id_conto_destinatario)) {
            throw new NullPointerException("Il conto non esiste!");
        }
        if (!conti_utente.contains(id_conto_mittente)) {
            throw new IllegalArgumentException("A ladro, il conto non è tuo!");
        }
        Conto conto_mittente = Database.getConti().get(id_conto_mittente);
        if (conto_mittente.getMoney() < money) {
            throw new IllegalArgumentException("Il conto non ha abbastanza denaro");
        }
        // logica
        Conto conto_destinatario = Database.getConti().get(id_conto_destinatario);
        conto_destinatario.addMoney(money);
        conto_mittente.subtractMoney(money);
        // creo la transazione, compresa di firma

        Transazione t = new Transazione(id_conto_mittente, id_conto_destinatario, this.id, money);
        String str_transaction = String.valueOf(t.hashCode());
        System.out.println(str_transaction);
        t.setSignature(SignatureService.sign(str_transaction.getBytes(), keyPair.getPrivate()));
        Database.addTransazione(t);
        return t;
    }

}
