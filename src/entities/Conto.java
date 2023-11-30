package entities;

import exceptions.ContoMismatchException;
import exceptions.FinanziamentoMismatchException;
import services.ValidatorService;

import java.time.LocalDate;
import java.util.List;

public class Conto {

    private static int idCounter = 0;
    private int id;
    private double costo;
    private double money;
    private final LocalDate sottoscrizione;
    private List<Integer> owners;

    public Conto(double costo, double money, List<Integer> owners) throws ContoMismatchException {
        if (!ValidatorService.validateConto(costo, money, owners)) {
            throw new ContoMismatchException();
        }
        this.id = idCounter++;
        this.costo = costo;
        this.money = money;
        this.sottoscrizione = LocalDate.now();
        this.owners = owners;
    }

    public int getId() {
        return id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) throws ContoMismatchException {
        if (costo < 0) {
            throw new ContoMismatchException();
        }
        this.costo = costo;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) throws FinanziamentoMismatchException {
        if (money < 0) throw new FinanziamentoMismatchException();
        this.money = money;
    }

    public LocalDate getSottoscrizione() {
        return sottoscrizione;
    }

    public List<Integer> getOwners() {
        return owners;
    }

    public void setOwners(List<Integer> owners) {
        this.owners = owners;
    }

    // funzionalità
    public void addMoney(double money) {
        if (money <= 0) { throw new IllegalArgumentException("non posso aggiungere soldi che non ci sono!"); }
        this.money += money;
    }

    public void subtractMoney(double money) {
        if (money <= 0) { throw new IllegalArgumentException("non posso togliere soldi che non ci sono!"); }
        this.money -= money;
    }

}
