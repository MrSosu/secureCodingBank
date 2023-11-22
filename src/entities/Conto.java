package entities;

import exceptions.ContoMismatchException;
import exceptions.FinanziamentoMismatchException;
import validators.Validator;

import java.time.LocalDate;

public class Conto {

    private static int idCounter = 0;
    private int id;
    private double costo;
    private double money;
    private final LocalDate sottoscrizione;

    public Conto(double costo, double money) throws ContoMismatchException {
        if (!Validator.validateConto(costo, money)) {
            throw new ContoMismatchException();
        }
        this.id = idCounter++;
        this.costo = costo;
        this.money = money;
        this.sottoscrizione = LocalDate.now();
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
}
