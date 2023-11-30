package services;

import database.Database;
import entities.Utente;
import exceptions.UtenteMismatchException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class IDService {

    /**
     * Questo metodo permette tramite la console di inserire i dati e registrare
     * un utente.
     * @return l'utente registrato
     */
    public static Utente register() {
        Scanner input = new Scanner(System.in);
        System.out.println("Compila il form di registrazione");
        System.out.println("Nome: ");
        String name = input.nextLine();
        System.out.println("Cognome: ");
        String surname = input.nextLine();
        System.out.println("Data di nascita, inserisci giorno mese ed anno: ");
        int giorno = input.nextInt();
        int mese = input.nextInt();
        int anno = input.nextInt();
        LocalDate birthday = LocalDate.of(anno, mese, giorno);
        input.nextLine();
        System.out.println("Indirizzo: ");
        String indirizzo = input.nextLine();
        System.out.println("Comune: ");
        String comune = input.nextLine();
        System.out.println("Codice Fiscale: ");
        String codiceFiscale = input.nextLine();
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Telefono: ");
        String telefono = input.nextLine();
        System.out.println("Inserisci una password: ");
        String password = input.nextLine();
        input.close();


        try {
            Utente u = new Utente(name, surname, birthday, indirizzo, comune, codiceFiscale, email, telefono, password);
            Database.addUtente(u);
            System.out.println("Registrazione avvenuta con successo!");
            return u;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UtenteMismatchException e) {
            System.out.println("Dati inseriti non corretti");
            register();
        }
        return null;
    }

    public static void login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci username e password");
        System.out.println("username: ");
        String email = input.nextLine();
        System.out.println("password: ");
        String password = input.nextLine();
        Utente u = Database.findUserByEmail(email);
        if (u.getPassword().equals(password)) {
            u.setLoggedIn(true);
        }
        else {
            System.out.println("Credenziali errate!");
            login();
        }
    }

    public static void logout(Utente u) {
        if (!u.isLoggedIn()) throw new IllegalArgumentException("Non sei loggato");
        u.setLoggedIn(false);
    }
}
