package io.github.kickit123.TranquilBot.models;

public class EconomyUser {
    private int moneyAmount;
    private long user_id;
    private int xp;

    public EconomyUser() {
    }

    public EconomyUser(long user_id) {
        this.user_id = user_id;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
