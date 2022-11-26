package model;

public class Circlet extends Artifact{

    public Circlet(int ID, String artifactSet, int artifactLevel, String mainStatName, double mainStatValue, String subStat1Name, double subStat1Value,
                  String subStat2Name, double subStat2Value, String subStat3Name, double subStat3Value, String subStat4Name, double subStat4Value) {
        this.ID = ID;
        this.artifactSet = artifactSet;
        this.artifactLevel = artifactLevel;
        this.mainStatName = mainStatName;
        this.mainStatValue = mainStatValue;
        this.subStat1Name = subStat1Name;
        this.subStat1Value = subStat1Value;
        this.subStat2Name = subStat2Name;
        this.subStat2Value = subStat2Value;
        this.subStat3Name = subStat3Name;
        this.subStat3Value = subStat3Value;
        this.subStat4Name = subStat4Name;
        this.subStat4Value = subStat4Value;
    }

}
