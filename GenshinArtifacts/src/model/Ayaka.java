package model;

import controller.mainController;

public class Ayaka extends GenshinCharacter{
    double talentMultipler;

    /*
        Soumetsu:
        Cutting DMG = 202%
        Bloom DMG = 303%

        Blizzard Strayer = 40% Crit Rate
        Rosaria Added CR = 12.285%, Rosaria Crit Rate = 81.9%(69.9 + 12)
        Total CR Added: 52.285% + Cryo Resonance

        Currently Not Applied:
        Kanten Senmyou Blessing: 18% Cryo DMG Bonus
        Kokomi: Thrilling Tales(48%) + Tenacity of the Millelith(20%) Make sure which ATK
        Kazuha:

        Amenoma : 454 BaseATK, 55.1Atk%
         */


    public Ayaka(String name) {
        this.name = name;
    }

    private void valuesFromArtifact(Artifact artifact) {
        setValuesFromSubstats (artifact.getSubStat1Name(), artifact.getSubStat1Value());
        setValuesFromSubstats (artifact.getSubStat2Name(), artifact.getSubStat2Value());
        setValuesFromSubstats (artifact.getSubStat3Name(), artifact.getSubStat3Value());
        setValuesFromSubstats (artifact.getSubStat4Name(), artifact.getSubStat4Value());
    }

    private void setValuesFromSubstats (String subStatName, double subStatValue) {
        switch (subStatName) {
            case "ATK%":
                this.ATKPercent += subStatValue;
                break;
            case "ATK Flat":
                this.flatATK += subStatValue;
                break;
            case "CRIT RATE":
                this.critRate += subStatValue;
                break;
            case "CRIT DMG":
                this.critDamage += subStatValue;
                break;
        }
    }

    private void resetStats() {
        this.talentMultipler = 0;
        this.charBaseATK = 0;
        this.weaponBaseATK = 0;
        this.dmgBonusPercent = 0;
        this.ATKPercent = 0;
        this.flatATK = 0;
        this.critRate = 5;
        this.critDamage = 50;
    }
    public double getSoumetsuDamage() {
        resetStats();
        this.talentMultipler = 2.02; //(2.02*20) + 3.03
        this.charBaseATK = 342; //at level 90
        this.weaponBaseATK = this.weapon.getBaseATK();

        //main Stats
        setValuesFromMainStats();

        //substats
        valuesFromArtifact(this.flower);
        valuesFromArtifact(this.plume);
        valuesFromArtifact(this.sands);
        valuesFromArtifact(this.goblet);
        valuesFromArtifact(this.circlet);

        //get total damage of crit
        //(Blizzard Strayer + Rosaria Added CR: 52.285% + ADD CRYO RESONANCE)
        this.critRate += 67.285;
        double critMultiplier = getCritMultiplier();

        double dmg = this.talentMultipler * (((this.charBaseATK + this.weaponBaseATK) * (1 + this.ATKPercent/100)) + this.flatATK) *
                (critMultiplier) * (1 + dmgBonusPercent/100);
        return Math.round(dmg * 100.0) / 100.0;
    }

    private double getCritMultiplier() {
        /*
        get CRIT Multiplier value before equation
        Crit Multiplier Total: (Crit Rate/100) X (1 + Crit DMG/100)
                + (1 - CritRate/100)(1)
         */
        if(this.critRate > 100) { this.critRate = 100; }
        else if(this.critRate <= 0) { return 1; }

        return (this.critRate/100) * (this.critDamage/100) + 1;
    }

    private void setValuesFromMainStats() {
        this.flatATK += this.plume.getMainStatValue();

        if(this.sands.getMainStatName().equals("ATK%")) {
            this.ATKPercent += this.sands.getMainStatValue();
        }

        if(this.goblet.getMainStatName().equals("ELEMENTAL DMG")){
            this.dmgBonusPercent += this.goblet.getMainStatValue();
        }

        if(this.getCirclet().getMainStatName().equals("CRIT RATE")) {
            this.critRate += this.getCirclet().getMainStatValue();
        } else if (this.getCirclet().getMainStatName().equals("CRIT DMG")) {
            this.critDamage += this.getCirclet().getMainStatValue();
        }
    }

}
