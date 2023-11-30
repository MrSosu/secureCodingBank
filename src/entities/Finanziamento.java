package entities;

import exceptions.FinanziamentoMismatchException;
import services.ValidatorService;

public class Finanziamento {

    private static int idCounter = 0;
    private int id;
    private double money;
    private double tasso;
    private int durata;
    private int id_utente;
    private int id_conto;

    public Finanziamento(double money, double tasso, int durata, int id_utente, int id_conto) throws FinanziamentoMismatchException {
        if (!ValidatorService.validateFinanziamento(money, tasso, durata)) {
            throw new FinanziamentoMismatchException();
        }
        this.id = idCounter++;
        this.money = money;
        this.tasso = tasso;
        this.durata = durata;
        this.id_utente = id_utente;
        this.id_conto = id_conto;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) throws FinanziamentoMismatchException {
        if (money < 0) throw new FinanziamentoMismatchException();
        this.money = money;
    }

    public double getTasso() {
        return tasso;
    }

    public void setTasso(double tasso) throws FinanziamentoMismatchException {
        if (tasso < 0) throw new FinanziamentoMismatchException();
        this.tasso = tasso;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) throws FinanziamentoMismatchException {
        if (durata < 0) throw new FinanziamentoMismatchException();
        this.durata = durata;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public int getId_conto() {
        return id_conto;
    }

    public void setId_conto(int id_conto) {
        this.id_conto = id_conto;
    }
}
