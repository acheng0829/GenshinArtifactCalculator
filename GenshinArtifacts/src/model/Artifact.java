package model;

public class Artifact {
    public int ID;
    public String artifactSet;
    public int artifactLevel;
    public String mainStatName;
    public double mainStatValue;
    public String subStat1Name;
    public double subStat1Value;
    public String subStat2Name;
    public double subStat2Value;
    public String subStat3Name;
    public double subStat3Value;
    public String subStat4Name;
    public double subStat4Value;
    public String currentlyEquipped;

    public Artifact(){
    }

    public int getID() {
        return ID;
    }

    public String getArtifactSet(){
        return artifactSet;
    }

    public int getArtifactLevel(){
        return artifactLevel;
    }

    public String getMainStatName() {
        return mainStatName;
    }

    public double getMainStatValue(){
        return mainStatValue;
    }

    public String getSubStat1Name() {
        return subStat1Name;
    }

    public double getSubStat1Value(){
        return subStat1Value;
    }

    public String getSubStat2Name() {
        return subStat2Name;
    }

    public double getSubStat2Value(){
        return subStat2Value;
    }

    public String getSubStat3Name() {
        return subStat3Name;
    }

    public double getSubStat3Value(){
        return subStat3Value;
    }

    public String getSubStat4Name() {
        return subStat4Name;
    }

    public double getSubStat4Value(){
        return subStat4Value;
    }

    public String getCurrentlyEquipped() {
        return currentlyEquipped;
    }

    public void setCurrentlyEquipped(String equippedCharacter) {
        this.currentlyEquipped = equippedCharacter;
    }
}
