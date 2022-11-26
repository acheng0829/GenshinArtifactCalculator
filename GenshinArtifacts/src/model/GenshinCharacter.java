package model;


import controller.mainController;

public class GenshinCharacter {
    String name;
    public Flower flower;
    public Plume plume;
    public Sands sands;
    public Goblet goblet;
    public Circlet circlet;
    int level;

    public Weapon weapon;
    double charBaseATK;
    double weaponBaseATK;
    double ATKPercent;
    double flatATK;
    double dmgBonusPercent;
    double critRate;
    double critDamage;

    int specialMultiplier;
    int amplifyingMultiplier;
    //amplifyingMultiplier = 1; //no amplifying reaction

    double damageValue;

    public GenshinCharacter() {

    }

    public GenshinCharacter(String name) {
        this.name = name;
        this.flower = null;
        this.plume = null;
        this.sands = null;
        this.goblet = null;
        this.circlet = null;
    }

    public GenshinCharacter(String name, Flower flower, Plume plume, Sands sands, Goblet goblet, Circlet circlet) {
        this.flower = flower;
        this.plume = plume;
        this.sands = sands;
        this.goblet = goblet;
        this.circlet = circlet;
    }





    public double calculateDamage() {
        /*Solo Damage: talentPercent * (((charBaseATK + wepBaseATK) * (1 + ATKPercent)) + flatATK) *
                (Crit Multiplier Total) * (1 + dmgBonusPercent)



        Crit Multiplier Total: (Crit Rate/100) X (Total Damage of a Crit)
        + (1 - CritRate/100)(Non Crit Damage)
            Crit DMG
            Crit RATE(if over 100% Crit Rate = 100%) (Blizzard Strayer + Rosaria Added CR: 52.285%)

        DmgBonusPercent(Goblets)


Solo Damage: talentPercent * (((charBaseATK + wepBaseATK) * (1 + ATKPercent)) + flatATK) *
(Crit Multiplier Total) * (1 + dmgBonusPercent)


AtkCharacter is the base attack of the character

AtkWeapon is the base attack of the equipped weapon

Attack% includes all percentage-based attack modifiers from weapons, artifacts and other sources

FlatAttack includes all non-percentage-based attack modifiers from artifacts, * Bennet's burst, Zhongli's A4 passive, etc

CritRate% includes all sources of crit rate, including the 5% base crit rate, artifacts, etc.

CritDamage% includes all sources of crit damage, including the 50% base crit damage, artifacts, etc.

DamageBonus includes all sources of increased damage such as elemental goblets, Gladiator's Finale 4-piece bonus, etc, excluding Xingqiu's constellation 4 effect Evilsoother

LvlCharacter is the level of your character

LvlEnemy is the level of the enemy

DefReduction includes all sources of defense (but not resistance) reduction, such as Razor's constellation 4

RES is the enemy elemental resistance to the element of the attack, including any resistance modifiers such as the Viridescent Venerer 4-piece bonus. See https://genshin-impact.fandom.com/wiki/Damage for a table of all enemy base elemental resistances.

EM is the character's Elemental Mastery

ReactionBonus% includes reaction damage bonuses from Crimson Witch 4-piece bonus (for Vaporize and Melt) and Mona's C1 (for Vaporize)

Evilsoother is Xingqiu's constellation 4 effect and applies only when using his elemental skill

ReactionRES is the enemy elemental resistance to the element of the elemental reaction: electro for electro-charged, pyro for overloaded, cryo for superconduct and either electro, cryo, pyro or hydro for swirl

ElectroChargedTriggers is the number of times Electro-Charged triggers, and depends on the elemental gauge strength of the hydro and electro elements applied to the enemy.




         */
        return damageValue;
    }



    public int damageValue(GenshinCharacter character) {
        /*
        ((Base Damage * Special Multiplier) + Flat Damage Bonus)
        * (1 + Percentage Damage Bonus - Damage Reduction of Target)
        * Amplifying Multiplier * Defense Multiplier of Target * Resistance Multiplier of Target

        Simplified ignoring target weakness:
        ((Base Damage * Special Multiplier) + Flat Damage Bonus)
        * (1 + Percentage Damage Bonus) * Amplifying Multiplier

        If Crit:
        Crit Damage = DMG * (1 + %CRIT DMG)
         */

        int damageValue = 0;
        int amplifyingMultiplier; //Vape or Melt
        int specialMultiplier = 1; //Special Multipliers are directly multiplicative with Base DMG, obtainable through certain abilities such as Xingqiu's Evilsoother or Yoimiya's Niwabi Fire-Dance.

        int baseDMG, flatDMGBonus, percentageDMGBonus;

        baseDMG =
                amplifyingMultiplier = 1; //no amplifying reaction

        return damageValue;
    }

    public String getName() {
        return name;
    }

    public void equipFlower(Flower flower) {
        this.flower = flower;
    }

    public Flower getFlower() {
        return flower;
    }

    public void equipPlume(Plume plume) {
        this.plume = plume;
    }

    public Plume getPlume() {
        return plume;
    }

    public void equipSands(Sands sands) {
        this.sands = sands;
    }

    public Sands getSands() {
        return sands;
    }

    public void equipGoblet(Goblet goblet) {
        this.goblet = goblet;
    }

    public Goblet getGoblet() {
        return goblet;
    }

    public void equipCirclet(Circlet circlet) {
        this.circlet = circlet;
    }

    public Circlet getCirclet() {
        return circlet;
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
