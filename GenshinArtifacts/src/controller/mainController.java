package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;
import model.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.DatabaseConnection.connection;

public class mainController {
    @FXML
    private Label welcomeText;

    public static ObservableList<String> ArtifactSetsList;
    public static ObservableList<String> ArtifactTypesList;
    public static ObservableList<Flower> FlowersList;
    public static ObservableList<Plume> PlumesList;
    public static ObservableList<Sands> SandsList;
    public static ObservableList<Goblet> GobletsList;
    public static ObservableList<Circlet> CircletsList;

    public static ObservableList<String> getArtifactSets() { return ArtifactSetsList; }
    public static ObservableList<String> getArtifactTypes() { return ArtifactTypesList; }
    public static ObservableList<Flower> getFlowersList() { return FlowersList; }
    public static ObservableList<Plume> getPlumesList() { return PlumesList; }
    public static ObservableList<Sands> getSandsList() { return SandsList; }
    public static ObservableList<Goblet> getGobletsList() { return GobletsList; }
    public static ObservableList<Circlet> getCircletsList() { return CircletsList; }

    @FXML
    private void initialize() {
        try {
            populateArtifactSetList();
            populateArtifactTypesList();

            FlowersList = FXCollections.observableArrayList();
            PlumesList = FXCollections.observableArrayList();
            SandsList = FXCollections.observableArrayList();
            GobletsList = FXCollections.observableArrayList();
            CircletsList = FXCollections.observableArrayList();
            getArtifacts();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void getArtifacts() {
        for(String artifactType : getArtifactTypes()) {
            String query = queryString(artifactType);
            getArtifactsFromDatabase(artifactType, query);
        }
    }


    private static String queryString(String artifactType) {
        String query = "";
        if(artifactType.equals("Sands")) {
            query = "SELECT ArtifactID, ArtifactSet, ArtifactLevel, MainStatName, MainStatValue, SubStat1Name, SubStat1Value, SubStat2Name, " +
                    "SubStat2Value, SubStat3Name, SubStat3Value, SubStat4Name, SubStat4Value, CurrentlyEquipped FROM " + artifactType;
            return query;
        }
        query = "SELECT ArtifactID, ArtifactSet, ArtifactLevel, MainStatName, MainStatValue, SubStat1Name, SubStat1Value, SubStat2Name, " +
                "SubStat2Value, SubStat3Name, SubStat3Value, SubStat4Name, SubStat4Value, CurrentlyEquipped FROM " + artifactType + "s";
        return query;
    }

    public static void getArtifactsFromDatabase(String artifactType, String query) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ArtifactID");
                String set = rs.getString("ArtifactSet");
                int level = rs.getInt("ArtifactLevel");
                String mainStatName = rs.getString("MainStatName");
                double mainStatValue = rs.getDouble("MainStatValue");
                String subStat1Name = rs.getString("SubStat1Name");
                double subStat1Value = rs.getDouble("SubStat1Value");
                String subStat2Name = rs.getString("SubStat2Name");
                double subStat2Value = rs.getDouble("SubStat2Value");
                String subStat3Name = rs.getString("SubStat3Name");
                double subStat3Value = rs.getDouble("SubStat3Value");
                String subStat4Name = rs.getString("SubStat4Name");
                double subStat4Value = rs.getDouble("SubStat4Value");
                String equipped = rs.getString("CurrentlyEquipped");

                switch (artifactType) {
                    case "Flower":
                        Flower flower = new Flower(ID, set, level, mainStatValue, subStat1Name, subStat1Value, subStat2Name, subStat2Value, subStat3Name,
                                subStat3Value, subStat4Name, subStat4Value);
                        flower.setCurrentlyEquipped(equipped);
                        FlowersList.add(flower);
                        break;
                    case "Plume":
                        Plume plume = new Plume(ID, set, level, mainStatValue, subStat1Name, subStat1Value, subStat2Name, subStat2Value, subStat3Name,
                                subStat3Value, subStat4Name, subStat4Value);
                        plume.setCurrentlyEquipped(equipped);
                        PlumesList.add(plume);
                        break;
                    case "Sands":
                        Sands sands = new Sands(ID, set, level, mainStatName, mainStatValue, subStat1Name, subStat1Value, subStat2Name, subStat2Value, subStat3Name,
                                subStat3Value, subStat4Name, subStat4Value);
                        sands.setCurrentlyEquipped(equipped);
                        SandsList.add(sands);
                        break;
                    case "Goblet":
                        Goblet goblet = new Goblet(ID, set, level, mainStatName, mainStatValue, subStat1Name, subStat1Value, subStat2Name, subStat2Value, subStat3Name,
                                subStat3Value, subStat4Name, subStat4Value);
                        goblet.setCurrentlyEquipped(equipped);
                        GobletsList.add(goblet);
                        break;
                    case "Circlet":
                        Circlet circlet = new Circlet(ID, set, level, mainStatName, mainStatValue, subStat1Name, subStat1Value, subStat2Name, subStat2Value, subStat3Name,
                                subStat3Value, subStat4Name, subStat4Value);
                        circlet.setCurrentlyEquipped(equipped);
                        CircletsList.add(circlet);
                        break;
                }
            }
        } catch(SQLException s){
            System.out.println(s);
        }
    }

    private void populateArtifactTypesList() {
        ArtifactTypesList = FXCollections.observableArrayList();
        ArtifactTypesList.add("Flower");
        ArtifactTypesList.add("Plume");
        ArtifactTypesList.add("Sands");
        ArtifactTypesList.add("Goblet");
        ArtifactTypesList.add("Circlet");
    }

    private void populateArtifactSetList() {
        ArtifactSetsList = FXCollections.observableArrayList();
        ArtifactSetsList.add("Blizzard Strayer");
        ArtifactSetsList.add("Emblem Of Severed Fate");
        ArtifactSetsList.add("Heart Of Depth");
        ArtifactSetsList.add("Thundering Fury");
        ArtifactSetsList.add("Viridescent Venerer");
        ArtifactSetsList.add("Gilded Dreams");
        ArtifactSetsList.add("Deepwood Memories");
        ArtifactSetsList.add("Echoes Of An Offering");
        ArtifactSetsList.add("Vermillion Hereafters");
        ArtifactSetsList.add("Husk Of Opulent Dreams");
    }

    @FXML
    private void ButtonArtifactDamageCalculator() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/artifactdamagecalculator.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
            Stage stage = new Stage();
            stage.setTitle("Artifact Damage Calculator");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void ButtonRollProbability() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/artifactrollprobability.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Artifact Roll Probability");
        stage.setScene(scene);
        stage.show();
    }
}