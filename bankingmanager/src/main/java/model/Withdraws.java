package model;

import java.sql.Date;

public class Withdraws {
    int withId;
    int id;
    Double balance;
    Date fullday;

    public Withdraws() {
    }

    public Withdraws(int withId, int id, Double balance, Date fullday) {
        this.withId = withId;
        this.id = id;
        this.balance = balance;
        this.fullday = fullday;
    }

    public int getWithId() {
        return withId;
    }

    public void setWithId(int withId) {
        this.withId = withId;
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
