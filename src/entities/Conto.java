package entities;

import validators.Validator;

import java.time.LocalDate;

public class Conto {

    private static int idCounter = 0;
    private int id;
    private double costo;
    private double money;
    private final LocalDate sottoscrizione;

    public Conto(double costo, double money) {
        this.id = idCounter++;
        Validator.validateConto(costo, money);
        this.costo = costo;
        this.money = money;
        this.sottoscrizione = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public LocalDate getSottoscrizione() {
        return sottoscrizione;
    }
}
