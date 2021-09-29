package model;


import java.sql.Date;

public class Deposits {
    int depoId;
    int id;
    Double balance;
    Date fullday;

    public Deposits() {
    }

    public Deposits(int depoId, int id, Double balance, Date fullday) {
        this.depoId = depoId;
        this.id = id;
        this.balance = balance;
        this.fullday = fullday;
    }


    public int getDepoId() {
        return depoId;
    }

    public void setDepoId(int depoId) {
        this.depoId = depoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getFullday() {
        return fullday;
    }

    public void setFullday(Date fullday) {
        this.fullday = fullday;
    }
}
