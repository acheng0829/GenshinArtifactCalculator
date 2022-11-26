package model;

public class Weapon {
    String name;
    double baseATK;
    double ATKPercent;
    String effects;

    public Weapon(String name, double baseATK, double ATKPercent) {
        this.name = name;
        this.baseATK = baseATK;
        this.ATKPercent = ATKPercent;
    }

    public String getName() {
        return name;
    }

    public double getBaseATK() {
        return baseATK;
    }

    public double getATKPercent() {
        return ATKPercent;
    }

}
