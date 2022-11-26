package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.sql.PreparedStatement;
import java.util.HashSet;

import static database.DatabaseConnection.connection;

public class addArtifactController {
    @FXML private ComboBox ArtifactTypeComboBox;
    @FXML private ComboBox ArtifactSetComboBox;
    @FXML private ComboBox MainStatComboBox;

    @FXML private ComboBox Substat1ComboBox;
    @FXML private ComboBox Substat2ComboBox;
    @FXML private ComboBox Substat3ComboBox;
    @FXML private ComboBox Substat4ComboBox;

    @FXML private TextField MainStatValue;
    @FXML private TextField ArtifactLevel;

    @FXML private TextField Substat1Value;
    @FXML private TextField Substat2Value;
    @FXML private TextField Substat3Value;
    @FXML private TextField Substat4Value;

    @FXML private javafx.scene.control.Button AddArtifact;

    private ObservableList<String> FlowerMainStat;
    private ObservableList<String> PlumeMainStat;
    private ObservableList<String> SandsMainStatsList;
    private ObservableList<String> GobletsMainStatsList;
    private ObservableList<String> CircletsMainStatsList;
    private ObservableList<String> SubstatsList;

    private HashSet<Integer> FlowerTakenIDs;
    private HashSet<Integer> PlumeTakenIDs;
    private HashSet<Integer> SandsTakenIDs;
    private HashSet<Integer> GobletTakenIDs;
    private HashSet<Integer> CircletTakenIDs;


    @FXML
    private void initialize() {
        try {
            ArtifactSetComboBox.setItems(mainController.getArtifactSets());
            ArtifactTypeComboBox.setItems(mainController.getArtifactTypes());
            PopulateMainStatsLists();
            PopulateSubstatsLists();

            Substat1ComboBox.setValue(SubstatsList.get(0));
            Substat1ComboBox.setItems(SubstatsList);
            Substat2ComboBox.setValue(SubstatsList.get(0));
            Substat2ComboBox.setItems(SubstatsList);
            Substat3ComboBox.setValue(SubstatsList.get(0));
            Substat3ComboBox.setItems(SubstatsList);
            Substat4ComboBox.setValue(SubstatsList.get(0));
            Substat4ComboBox.setItems(SubstatsList);

            PopulateTakenIDs();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void PopulateTakenIDs() {
        FlowerTakenIDs = new HashSet<>();
        PlumeTakenIDs = new HashSet<>();
        SandsTakenIDs = new HashSet<>();
        GobletTakenIDs = new HashSet<>();
        CircletTakenIDs = new HashSet<>();

        for(Flower flower : mainController.getFlowersList()) {
            FlowerTakenIDs.add(flower.getID());
        }
        for(Plume plume : mainController.getPlumesList()) {
            PlumeTakenIDs.add(plume.getID());
        }
        for(Sands sands : mainController.getSandsList()) {
            SandsTakenIDs.add(sands.getID());
        }
        for(Goblet goblet : mainController.getGobletsList()) {
            FlowerTakenIDs.add(goblet.getID());
        }
        for(Circlet circlet : mainController.getCircletsList()) {
            CircletTakenIDs.add(circlet.getID());
        }

    }

    private void PopulateSubstatsLists() {
        SubstatsList = FXCollections.observableArrayList();
        SubstatsList.add("CRIT RATE");
        SubstatsList.add("CRIT DMG");
        SubstatsList.add("ATK%");
        SubstatsList.add("E.RECHARGE");
        SubstatsList.add("EM");
        SubstatsList.add("ATK Flat");
        SubstatsList.add("HP%");
        SubstatsList.add("HP Flat");
        SubstatsList.add("DEF%");
        SubstatsList.add("DEF Flat");
    }

    private void PopulateMainStatsLists() {
        FlowerMainStat = FXCollections.observableArrayList();
        FlowerMainStat.add("HP");

        PlumeMainStat = FXCollections.observableArrayList();
        PlumeMainStat.add("ATK");

        SandsMainStatsList = FXCollections.observableArrayList();
        SandsMainStatsList.add("ATK%");
        SandsMainStatsList.add("E.RECHARGE");
        SandsMainStatsList.add("EM");
        SandsMainStatsList.add("HP%");
        SandsMainStatsList.add("DEF%");

        GobletsMainStatsList = FXCollections.observableArrayList();
        GobletsMainStatsList.add("ELEMENTAL DMG");
        GobletsMainStatsList.add("EM");
        GobletsMainStatsList.add("ATK%");
        GobletsMainStatsList.add("HP%");
        GobletsMainStatsList.add("DEF%");
        GobletsMainStatsList.add("PHYSICAL DMG");

        CircletsMainStatsList = FXCollections.observableArrayList();
        CircletsMainStatsList.add("CRIT RATE");
        CircletsMainStatsList.add("CRIT DMG");
        CircletsMainStatsList.add("ATK%");
        CircletsMainStatsList.add("EM");
        CircletsMainStatsList.add("HEALING BONUS");
        CircletsMainStatsList.add("HP%");
        CircletsMainStatsList.add("DEF%");
    }

    @FXML
    private void AddArtifactButton() {
        try{
            String artifactType = (String) ArtifactTypeComboBox.getSelectionModel().getSelectedItem();
            int ID = generateID(artifactType);
            String artifactSet = (String) ArtifactSetComboBox.getSelectionModel().getSelectedItem();
            int artifactLevel = Integer.parseInt(this.ArtifactLevel.getText());
            String mainStatName = (String) MainStatComboBox.getSelectionModel().getSelectedItem();
            double mainStatValue = Double.parseDouble((this.MainStatValue.getText()));
            String subStat1Name = (String) Substat1ComboBox.getSelectionModel().getSelectedItem();
            double subStat1Value = Double.parseDouble((this.Substat1Value.getText()));
            String subStat2Name = (String) Substat2ComboBox.getSelectionModel().getSelectedItem();
            double subStat2Value = Double.parseDouble((this.Substat2Value.getText()));
            String subStat3Name = (String) Substat3ComboBox.getSelectionModel().getSelectedItem();
            double subStat3Value = Double.parseDouble((this.Substat3Value.getText()));
            String subStat4Name = (String) Substat4ComboBox.getSelectionModel().getSelectedItem();
            double subStat4Value = Double.parseDouble((this.Substat4Value.getText()));

            switch (artifactType) {
                case "Flower":
                    mainStatValue = 4780;
                    Flower flower = new Flower(ID, artifactSet, artifactLevel, mainStatValue, subStat1Name, subStat1Value,
                            subStat2Name, subStat2Value, subStat3Name, subStat3Value, subStat4Name, subStat4Value);

                    mainController.getFlowersList().add(flower);
                    addArtifactToDatabase(artifactType, flower);
                    break;
                case "Plume":
                    Plume plume = new Plume(ID, artifactSet, artifactLevel, mainStatValue, subStat1Name, subStat1Value,
                            subStat2Name, subStat2Value, subStat3Name, subStat3Value, subStat4Name, subStat4Value);
                    mainController.getPlumesList().add(plume);
                    addArtifactToDatabase(artifactType, plume);
                    break;
                case "Sands":
                    Sands sands = new Sands(ID, artifactSet, artifactLevel, mainStatName, mainStatValue, subStat1Name, subStat1Value,
                            subStat2Name, subStat2Value, subStat3Name, subStat3Value, subStat4Name, subStat4Value);
                    mainController.getSandsList().add(sands);
                    addArtifactToDatabase(artifactType, sands);
                    break;
                case "Goblet":
                    Goblet goblet = new Goblet(ID, artifactSet, artifactLevel, mainStatName, mainStatValue, subStat1Name, subStat1Value,
                            subStat2Name, subStat2Value, subStat3Name, subStat3Value, subStat4Name, subStat4Value);
                    mainController.getGobletsList().add(goblet);
                    addArtifactToDatabase(artifactType, goblet);
                    break;
                case "Circlet":
                    Circlet circlet = new Circlet(ID, artifactSet, artifactLevel, mainStatName, mainStatValue, subStat1Name, subStat1Value,
                            subStat2Name, subStat2Value, subStat3Name, subStat3Value, subStat4Name, subStat4Value);
                    mainController.getCircletsList().add(circlet);
                    addArtifactToDatabase(artifactType, circlet);
                    break;
            }
            Stage stage = (Stage) AddArtifact.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String buildStringForQuery(String artifactType) {
        String query = "INSERT INTO " + artifactType + "s (ArtifactID, ArtifactSet, ArtifactLevel, MainStatName, MainStatValue, " +
                "SubStat1Name, SubStat1Value, SubStat2Name, SubStat2Value, SubStat3Name, SubStat3Value, SubStat4Name, SubStat4Value) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if(artifactType.equals("Sands")) {
            query = "INSERT INTO " + artifactType + " (ArtifactID, ArtifactSet, ArtifactLevel, MainStatName, MainStatValue, " +
                    "SubStat1Name, SubStat1Value, SubStat2Name, SubStat2Value, SubStat3Name, SubStat3Value, SubStat4Name, SubStat4Value) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        return query;
    }

    public void addArtifactToDatabase (String artifactType, Artifact artifact) {
        try {
            String query = buildStringForQuery(artifactType);
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, artifact.getID());
            ps.setString(2, artifact.getArtifactSet());
            ps.setInt(3, artifact.getArtifactLevel());
            ps.setString(4, artifact.getMainStatName());
            ps.setDouble(5, artifact.getMainStatValue());
            ps.setString(6, artifact.getSubStat1Name());
            ps.setDouble(7, artifact.getSubStat1Value());
            ps.setString(8, artifact.getSubStat2Name());
            ps.setDouble(9, artifact.getSubStat2Value());
            ps.setString(10, artifact.getSubStat3Name());
            ps.setDouble(11, artifact.getSubStat3Value());
            ps.setString(12, artifact.getSubStat4Name());
            ps.setDouble(13, artifact.getSubStat4Value());

            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void ArtifactTypeSelected() {
        String artifactType = (String) ArtifactTypeComboBox.getSelectionModel().getSelectedItem();
        switch (artifactType) {
            case "Flower":
                MainStatComboBox.setValue(FlowerMainStat.get(0));
                MainStatComboBox.setItems(FlowerMainStat);
                break;
            case "Plume":
                MainStatComboBox.setValue(PlumeMainStat.get(0));
                MainStatComboBox.setItems(PlumeMainStat);
                break;
            case "Sands":
                MainStatComboBox.setValue(SandsMainStatsList.get(0));
                MainStatComboBox.setItems(SandsMainStatsList);
                break;
            case "Goblet":
                MainStatComboBox.setValue(GobletsMainStatsList.get(0));
                MainStatComboBox.setItems(GobletsMainStatsList);
                break;
            case "Circlet":
                MainStatComboBox.setValue(CircletsMainStatsList.get(0));
                MainStatComboBox.setItems(CircletsMainStatsList);
                break;
        }


        /*if(artifactType.equals("Flower")) {
            MainStatComboBox.setValue(FlowerMainStat.get(0));
            MainStatComboBox.setItems(FlowerMainStat);
        }
        else if(artifactType.equals("Plume")) {
            MainStatComboBox.setValue(PlumeMainStat.get(0));
            MainStatComboBox.setItems(PlumeMainStat);
        }

         */


    }

    private int generateID(String artifactType) {
        try {
            int ID = 1;
            switch (artifactType) {
                case "Flower":
                    while (true) {
                        if(!FlowerTakenIDs.contains(ID)) { return ID; }
                        else { ID+=1; }
                    }
                case "Plume":
                    while (true) {
                        if(!PlumeTakenIDs.contains(ID)) { return ID; }
                        else { ID+=1; }
                    }
                case "Sands":
                    while (true) {
                        if(!SandsTakenIDs.contains(ID)) { return ID; }
                        else { ID+=1; }
                    }
                case "Goblet":
                    while (true) {
                        if(!GobletTakenIDs.contains(ID)) { return ID; }
                        else { ID+=1; }
                    }
                case "Circlet":
                    while (true) {
                        if(!CircletTakenIDs.contains(ID)) { return ID; }
                        else { ID+=1; }
                    }

            }
            return -1;

        } catch (Exception e) {
            System.out.println(e);
            return -2;
        }
    }

}
