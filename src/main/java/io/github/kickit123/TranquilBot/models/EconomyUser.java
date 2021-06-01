package io.github.kickit123.TranquilBot.models;

public class EconomyUser {
    private int moneyAmount;
    private long user_id;
    private int xp;

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

    public EconomyUser(long user_id) {
        this.user_id = user_id;
    }

    public int getLvl() {
        if (xp <= 255) {
            return xp / 17;
        } else if (xp > 272 && xp < 887) {
            return (int) ((Math.sqrt(24 * xp - 5159) + 59) / 6);
        } else if (xp > 825) {
            return (int) ((Math.sqrt(56 * xp - 32511) + 303) / 14);
        }
        return 0;
    }

    public void setLevel(int lvl) {
        if (lvl <= 15) {
            xp = lvl * 17;
        } else if (lvl > 16 && lvl < 31) {
            xp = (int) (1.5 * Math.pow(lvl, 2) - 29.5 * lvl + 360);
        } else if (lvl > 30) {
            xp = (int) (3.5 * Math.pow(lvl, 2) - 151.5 * lvl + 2220);
        }
    }
}
