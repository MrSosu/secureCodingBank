package entities;

import exceptions.UtenteMismatchException;
import validators.Validator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Set;

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
    private String tel;

    public Utente(String nome, String cognome, LocalDate dataNascita,
                  String indirizzo, String comune, String codiceFiscale, String email, String tel) throws IOException, UtenteMismatchException {
            if (!Validator.validateUtente(dataNascita, comune, codiceFiscale, email, tel)) {
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
            this.tel = tel;
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
        if (!Validator.validateDataNascita(dataNascita)) {
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
        if (!Validator.validateComune(comune)) {
            throw new UtenteMismatchException();
        }
        this.comune = comune;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) throws UtenteMismatchException {
        if (!Validator.validateCodiceFiscale(codiceFiscale)) {
            throw new UtenteMismatchException();
        }
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws UtenteMismatchException {
        if (!Validator.validateEmail(email)) {
            throw new UtenteMismatchException();
        }
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) throws UtenteMismatchException {
        if (!Validator.validateTelefono(tel)) {
            throw new UtenteMismatchException();
        }
        this.tel = tel;
    }
}
