package controller;

import com.mysql.cj.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.DatabaseConnection.connection;

public class damageCalculatorController {
    @FXML private Label displayFlowerMainStatValue;
    @FXML private Label FlowerStat1Name;
    @FXML private Label FlowerStat2Name;
    @FXML private Label FlowerStat3Name;
    @FXML private Label FlowerStat4Name;
    @FXML private Label FlowerStat1Value;
    @FXML private Label FlowerStat2Value;
    @FXML private Label FlowerStat3Value;
    @FXML private Label FlowerStat4Value;

    @FXML private Label displayPlumeMainStatValue;
    @FXML private Label PlumeStat1Name;
    @FXML private Label PlumeStat2Name;
    @FXML private Label PlumeStat3Name;
    @FXML private Label PlumeStat4Name;
    @FXML private Label PlumeStat1Value;
    @FXML private Label PlumeStat2Value;
    @FXML private Label PlumeStat3Value;
    @FXML private Label PlumeStat4Value;

    @FXML private Label displaySandsMainStat;
    @FXML private Label displaySandsMainStatValue;
    @FXML private Label SandsStat1Name;
    @FXML private Label SandsStat2Name;
    @FXML private Label SandsStat3Name;
    @FXML private Label SandsStat4Name;
    @FXML private Label SandsStat1Value;
    @FXML private Label SandsStat2Value;
    @FXML private Label SandsStat3Value;
    @FXML private Label SandsStat4Value;

    @FXML private Label displayGobletMainStat;
    @FXML private Label displayGobletMainStatValue;
    @FXML private Label GobletStat1Name;
    @FXML private Label GobletStat2Name;
    @FXML private Label GobletStat3Name;
    @FXML private Label GobletStat4Name;
    @FXML private Label GobletStat1Value;
    @FXML private Label GobletStat2Value;
    @FXML private Label GobletStat3Value;
    @FXML private Label GobletStat4Value;

    @FXML private Label displayCircletMainStat;
    @FXML private Label displayCircletMainStatValue;
    @FXML private Label CircletStat1Name;
    @FXML private Label CircletStat2Name;
    @FXML private Label CircletStat3Name;
    @FXML private Label CircletStat4Name;
    @FXML private Label CircletStat1Value;
    @FXML private Label CircletStat2Value;
    @FXML private Label CircletStat3Value;
    @FXML private Label CircletStat4Value;

    @FXML private Label DamageLabel;

    @FXML private ComboBox ArtifactSetComboBox;
    @FXML private ComboBox GenshinCharacterComboBox;
    @FXML private ComboBox WeaponComboBox;

    public TableView<Flower> FlowersTable;
    public TableColumn FlowerMainStat;
    public TableColumn FlowerID;
    public TableColumn FlowerSubstat1;
    public TableColumn FlowerValue1;
    public TableColumn FlowerSubstat2;
    public TableColumn FlowerValue2;
    public TableColumn FlowerSubstat3;
    public TableColumn FlowerValue3;
    public TableColumn FlowerSubstat4;
    public TableColumn FlowerValue4;
    public TableColumn FlowerArtifactSet;
    public TableColumn FlowerEquipped;

    public TableView<Plume> PlumesTable;
    public TableColumn PlumeMainStat;
    public TableColumn PlumeID;
    public TableColumn PlumeSubstat1;
    public TableColumn PlumeValue1;
    public TableColumn PlumeSubstat2;
    public TableColumn PlumeValue2;
    public TableColumn PlumeSubstat3;
    public TableColumn PlumeValue3;
    public TableColumn PlumeSubstat4;
    public TableColumn PlumeValue4;
    public TableColumn PlumeArtifactSet;
    public TableColumn PlumeEquipped;

    public TableView<Sands> SandsTable;
    public TableColumn SandsMainStat;
    public TableColumn SandsID;
    public TableColumn SandsSubstat1;
    public TableColumn SandsValue1;
    public TableColumn SandsSubstat2;
    public TableColumn SandsValue2;
    public TableColumn SandsSubstat3;
    public TableColumn SandsValue3;
    public TableColumn SandsSubstat4;
    public TableColumn SandsValue4;
    public TableColumn SandsArtifactSet;
    public TableColumn SandsEquipped;

    public TableView<Goblet> GobletsTable;
    public TableColumn GobletMainStat;
    public TableColumn GobletID;
    public TableColumn GobletSubstat1;
    public TableColumn GobletValue1;
    public TableColumn GobletSubstat2;
    public TableColumn GobletValue2;
    public TableColumn GobletSubstat3;
    public TableColumn GobletValue3;
    public TableColumn GobletSubstat4;
    public TableColumn GobletValue4;
    public TableColumn GobletArtifactSet;
    public TableColumn GobletEquipped;

    public TableView<Circlet> CircletsTable;
    public TableColumn CircletMainStat;
    public TableColumn CircletID;
    public TableColumn CircletSubstat1;
    public TableColumn CircletValue1;
    public TableColumn CircletSubstat2;
    public TableColumn CircletValue2;
    public TableColumn CircletSubstat3;
    public TableColumn CircletValue3;
    public TableColumn CircletSubstat4;
    public TableColumn CircletValue4;
    public TableColumn CircletArtifactSet;
    public TableColumn CircletEquipped;

    Ayaka ayaka;
    GenshinCharacter Keqing;
    GenshinCharacter Xiangling;
    GenshinCharacter currentCharacter; //character state
    Weapon currentWeapon; //current weapon in combobox
    List<GenshinCharacter> GenshinCharacterObjects;
    ObservableList<String> GenshinCharacters;
    List<Weapon> weaponsObjects;
    ObservableList<String> WeaponsList;


    @FXML
    private void initialize() {
        try {
            GenshinCharacters = FXCollections.observableArrayList();
            WeaponsList = FXCollections.observableArrayList();
            ObservableList<String> ArtifactSets = FXCollections.observableArrayList();

            setFlowersTable();
            setPlumesTable();
            setSandsTable();
            setGobletsTable();
            setCircletsTable();

            setWeapons();
            artifactSetComboBox(ArtifactSets);
            genshinCharactersComboBox(GenshinCharacters);
            GenshinCharacterObjects = new ArrayList<>();
            createGenshinCharacters();

            GenshinCharacterComboBoxAction();
            WeaponComboBoxAction();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private void setEquippedArtifacts() throws SQLException {
        String query= "";
        query = "SELECT EquippedFlowerID, EquippedPlumeID, EquippedSandsID, EquippedGobletID, EquippedCircletID FROM currentlyequipped WHERE CharacterName = '" + currentCharacter.getName() + "'";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int flowerID = rs.getInt("EquippedFlowerID");
            int plumeID = rs.getInt("EquippedPlumeID");
            int sandsID = rs.getInt("EquippedSandsID");
            int gobletID = rs.getInt("EquippedGobletID");
            int circletID = rs.getInt("EquippedCircletID");

            for (Flower f : mainController.getFlowersList()) {
                if (f.getID() == flowerID) { currentCharacter.equipFlower(f); break; }
            }

            for (Plume p : mainController.getPlumesList()) {
                if (p.getID() == plumeID) { currentCharacter.equipPlume(p); break; }
            }

            for (Sands s : mainController.getSandsList()) {
                if (s.getID() == sandsID) { currentCharacter.equipSands(s); break; }
            }

            for (Goblet g : mainController.getGobletsList()) {
                if (g.getID() == gobletID) { currentCharacter.equipGoblet(g); break; }
            }

            for (Circlet c : mainController.getCircletsList()) {
                if (c.getID() == circletID) { currentCharacter.equipCirclet(c); break; }
            }
        }
    }

    private void setWeapons() {
        weaponsObjects = new ArrayList<>();
        Weapon amenonaKageuchi = new Weapon("Amenona Kageuchi", 454, 55.1);
        weaponsObjects.add(amenonaKageuchi);
        for(Weapon w : weaponsObjects) {
            WeaponsList.add(w.getName());
        }
        WeaponComboBox.setValue(WeaponsList.get(0));
        WeaponComboBox.setItems(WeaponsList);
    }

    private void createGenshinCharacters() {
        ayaka = new Ayaka("Ayaka");
        Keqing = new GenshinCharacter("Keqing");
        Xiangling = new GenshinCharacter("Xiangling");
        GenshinCharacterObjects.add(ayaka);
        GenshinCharacterObjects.add(Keqing);
        GenshinCharacterObjects.add(Xiangling);
    }

    private void genshinCharactersComboBox(ObservableList<String> Characters) {
        Characters.add("Ayaka");
        Characters.add("Keqing");
        Characters.add("Xiangling");
        GenshinCharacterComboBox.setItems(Characters);
        GenshinCharacterComboBox.setValue(Characters.get(0));

    }

    private void artifactSetComboBox(ObservableList<String> ArtifactSets) {
        ArtifactSets.add("SHOW ALL");
        for(String s : mainController.getArtifactSets()) { ArtifactSets.add(s); }

        ArtifactSetComboBox.setItems(ArtifactSets);
        ArtifactSetComboBox.setValue(ArtifactSets.get(0));
    }

    private void setFlowersTable() {
        FlowerMainStat.setCellValueFactory(new PropertyValueFactory<>("mainStatName"));
        FlowerID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        FlowerSubstat1.setCellValueFactory(new PropertyValueFactory<>("subStat1Name"));
        FlowerValue1.setCellValueFactory(new PropertyValueFactory<>("subStat1Value"));
        FlowerSubstat2.setCellValueFactory(new PropertyValueFactory<>("subStat2Name"));
        FlowerValue2.setCellValueFactory(new PropertyValueFactory<>("subStat2Value"));
        FlowerSubstat3.setCellValueFactory(new PropertyValueFactory<>("subStat3Name"));
        FlowerValue3.setCellValueFactory(new PropertyValueFactory<>("subStat3Value"));
        FlowerSubstat4.setCellValueFactory(new PropertyValueFactory<>("subStat4Name"));
        FlowerValue4.setCellValueFactory(new PropertyValueFactory<>("subStat4Value"));
        FlowerArtifactSet.setCellValueFactory(new PropertyValueFactory<>("artifactSet"));
        FlowerEquipped.setCellValueFactory(new PropertyValueFactory<>("currentlyEquipped"));

        FlowersTable.setItems(mainController.getFlowersList());
    }

    private void setPlumesTable() {
        PlumeMainStat.setCellValueFactory(new PropertyValueFactory<>("mainStatName"));
        PlumeID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        PlumeSubstat1.setCellValueFactory(new PropertyValueFactory<>("subStat1Name"));
        PlumeValue1.setCellValueFactory(new PropertyValueFactory<>("subStat1Value"));
        PlumeSubstat2.setCellValueFactory(new PropertyValueFactory<>("subStat2Name"));
        PlumeValue2.setCellValueFactory(new PropertyValueFactory<>("subStat2Value"));
        PlumeSubstat3.setCellValueFactory(new PropertyValueFactory<>("subStat3Name"));
        PlumeValue3.setCellValueFactory(new PropertyValueFactory<>("subStat3Value"));
        PlumeSubstat4.setCellValueFactory(new PropertyValueFactory<>("subStat4Name"));
        PlumeValue4.setCellValueFactory(new PropertyValueFactory<>("subStat4Value"));
        PlumeArtifactSet.setCellValueFactory(new PropertyValueFactory<>("artifactSet"));
        PlumeEquipped.setCellValueFactory(new PropertyValueFactory<>("currentlyEquipped"));

        PlumesTable.setItems(mainController.getPlumesList());
    }

    private void setSandsTable() {
        SandsMainStat.setCellValueFactory(new PropertyValueFactory<>("mainStatName"));
        SandsID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        SandsSubstat1.setCellValueFactory(new PropertyValueFactory<>("subStat1Name"));
        SandsValue1.setCellValueFactory(new PropertyValueFactory<>("subStat1Value"));
        SandsSubstat2.setCellValueFactory(new PropertyValueFactory<>("subStat2Name"));
        SandsValue2.setCellValueFactory(new PropertyValueFactory<>("subStat2Value"));
        SandsSubstat3.setCellValueFactory(new PropertyValueFactory<>("subStat3Name"));
        SandsValue3.setCellValueFactory(new PropertyValueFactory<>("subStat3Value"));
        SandsSubstat4.setCellValueFactory(new PropertyValueFactory<>("subStat4Name"));
        SandsValue4.setCellValueFactory(new PropertyValueFactory<>("subStat4Value"));
        SandsArtifactSet.setCellValueFactory(new PropertyValueFactory<>("artifactSet"));
        SandsEquipped.setCellValueFactory(new PropertyValueFactory<>("currentlyEquipped"));

        SandsTable.setItems(mainController.getSandsList());
    }

    private void setGobletsTable() {
        GobletMainStat.setCellValueFactory(new PropertyValueFactory<>("mainStatName"));
        GobletID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        GobletSubstat1.setCellValueFactory(new PropertyValueFactory<>("subStat1Name"));
        GobletValue1.setCellValueFactory(new PropertyValueFactory<>("subStat1Value"));
        GobletSubstat2.setCellValueFactory(new PropertyValueFactory<>("subStat2Name"));
        GobletValue2.setCellValueFactory(new PropertyValueFactory<>("subStat2Value"));
        GobletSubstat3.setCellValueFactory(new PropertyValueFactory<>("subStat3Name"));
        GobletValue3.setCellValueFactory(new PropertyValueFactory<>("subStat3Value"));
        GobletSubstat4.setCellValueFactory(new PropertyValueFactory<>("subStat4Name"));
        GobletValue4.setCellValueFactory(new PropertyValueFactory<>("subStat4Value"));
        GobletArtifactSet.setCellValueFactory(new PropertyValueFactory<>("artifactSet"));
        GobletEquipped.setCellValueFactory(new PropertyValueFactory<>("currentlyEquipped"));

        GobletsTable.setItems(mainController.getGobletsList());
    }

    private void setCircletsTable() {
        CircletMainStat.setCellValueFactory(new PropertyValueFactory<>("mainStatName"));
        CircletID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        CircletSubstat1.setCellValueFactory(new PropertyValueFactory<>("subStat1Name"));
        CircletValue1.setCellValueFactory(new PropertyValueFactory<>("subStat1Value"));
        CircletSubstat2.setCellValueFactory(new PropertyValueFactory<>("subStat2Name"));
        CircletValue2.setCellValueFactory(new PropertyValueFactory<>("subStat2Value"));
        CircletSubstat3.setCellValueFactory(new PropertyValueFactory<>("subStat3Name"));
        CircletValue3.setCellValueFactory(new PropertyValueFactory<>("subStat3Value"));
        CircletSubstat4.setCellValueFactory(new PropertyValueFactory<>("subStat4Name"));
        CircletValue4.setCellValueFactory(new PropertyValueFactory<>("subStat4Value"));
        CircletArtifactSet.setCellValueFactory(new PropertyValueFactory<>("artifactSet"));
        CircletEquipped.setCellValueFactory(new PropertyValueFactory<>("currentlyEquipped"));

        CircletsTable.setItems(mainController.getCircletsList());
    }

    @FXML
    private void ArtifactSetComboBoxAction() {

    }

    @FXML
    private void GenshinCharacterComboBoxAction() throws SQLException {
        String selectedCharacterName = (String) GenshinCharacterComboBox.getSelectionModel().getSelectedItem();

        for(GenshinCharacter ch : GenshinCharacterObjects) {
            if(selectedCharacterName.equals(ch.getName())) {
                currentCharacter = ch;
                setEquippedArtifacts();

                displayFlower();
                displayPlume();
                displaySands();
                displayGoblet();
                displayCirclet();
                break;
            }
        }
    }

    @FXML
    private void WeaponComboBoxAction() {
        String weapon = (String) WeaponComboBox.getSelectionModel().getSelectedItem();

        for(Weapon w : weaponsObjects) {
            if(w.getName().equals(weapon)) {
                currentWeapon = w;
                currentCharacter.equipWeapon(w);
            }
        }
    }

    private void displayFlower() {
        if(currentCharacter.getFlower() != null) {
            Flower flower = currentCharacter.getFlower();
            displayFlowerMainStatValue.setText(Double.toString(flower.getMainStatValue()));

            FlowerStat1Name.setText(flower.getSubStat1Name());
            FlowerStat1Value.setText(Double.toString(flower.getSubStat1Value()));
            FlowerStat2Name.setText(flower.getSubStat2Name());
            FlowerStat2Value.setText(Double.toString(flower.getSubStat2Value()));
            FlowerStat3Name.setText(flower.getSubStat3Name());
            FlowerStat3Value.setText(Double.toString(flower.getSubStat3Value()));
            FlowerStat4Name.setText(flower.getSubStat4Name());
            FlowerStat4Value.setText(Double.toString(flower.getSubStat4Value()));
        } else {
            FlowerStat1Name.setText("");
            FlowerStat1Value.setText("");
            FlowerStat2Name.setText("");
            FlowerStat2Value.setText("");
            FlowerStat3Name.setText("");
            FlowerStat3Value.setText("");
            FlowerStat4Name.setText("");
            FlowerStat4Value.setText("");
        }
    }

    private void displayPlume() {
        if(currentCharacter.getPlume() != null) {
            Plume plume = currentCharacter.getPlume();
            displayPlumeMainStatValue.setText(Double.toString(plume.getMainStatValue()));

            PlumeStat1Name.setText(plume.getSubStat1Name());
            PlumeStat1Value.setText(Double.toString(plume.getSubStat1Value()));
            PlumeStat2Name.setText(plume.getSubStat2Name());
            PlumeStat2Value.setText(Double.toString(plume.getSubStat2Value()));
            PlumeStat3Name.setText(plume.getSubStat3Name());
            PlumeStat3Value.setText(Double.toString(plume.getSubStat3Value()));
            PlumeStat4Name.setText(plume.getSubStat4Name());
            PlumeStat4Value.setText(Double.toString(plume.getSubStat4Value()));
        } else {
            PlumeStat1Name.setText("");
            PlumeStat1Value.setText("");
            PlumeStat2Name.setText("");
            PlumeStat2Value.setText("");
            PlumeStat3Name.setText("");
            PlumeStat3Value.setText("");
            PlumeStat4Name.setText("");
            PlumeStat4Value.setText("");
        }
    }

    private void displaySands() {
        if(currentCharacter.getSands() != null) {
            Sands sands = currentCharacter.getSands();
            displaySandsMainStat.setText(sands.getMainStatName());
            displaySandsMainStatValue.setText(Double.toString(sands.getMainStatValue()));

            SandsStat1Name.setText(sands.getSubStat1Name());
            SandsStat1Value.setText(Double.toString(sands.getSubStat1Value()));
            SandsStat2Name.setText(sands.getSubStat2Name());
            SandsStat2Value.setText(Double.toString(sands.getSubStat2Value()));
            SandsStat3Name.setText(sands.getSubStat3Name());
            SandsStat3Value.setText(Double.toString(sands.getSubStat3Value()));
            SandsStat4Name.setText(sands.getSubStat4Name());
            SandsStat4Value.setText(Double.toString(sands.getSubStat4Value()));
        } else {
            SandsStat1Name.setText("");
            SandsStat1Value.setText("");
            SandsStat2Name.setText("");
            SandsStat2Value.setText("");
            SandsStat3Name.setText("");
            SandsStat3Value.setText("");
            SandsStat4Name.setText("");
            SandsStat4Value.setText("");
        }
    }

    private void displayGoblet() {
        if(currentCharacter.getGoblet() != null) {
            Goblet goblet = currentCharacter.getGoblet();
            displayGobletMainStat.setText(goblet.getMainStatName());
            displayGobletMainStatValue.setText(Double.toString(goblet.getMainStatValue()));

            GobletStat1Name.setText(goblet.getSubStat1Name());
            GobletStat1Value.setText(Double.toString(goblet.getSubStat1Value()));
            GobletStat2Name.setText(goblet.getSubStat2Name());
            GobletStat2Value.setText(Double.toString(goblet.getSubStat2Value()));
            GobletStat3Name.setText(goblet.getSubStat3Name());
            GobletStat3Value.setText(Double.toString(goblet.getSubStat3Value()));
            GobletStat4Name.setText(goblet.getSubStat4Name());
            GobletStat4Value.setText(Double.toString(goblet.getSubStat4Value()));
        } else {
            GobletStat1Name.setText("");
            GobletStat1Value.setText("");
            GobletStat2Name.setText("");
            GobletStat2Value.setText("");
            GobletStat3Name.setText("");
            GobletStat3Value.setText("");
            GobletStat4Name.setText("");
            GobletStat4Value.setText("");
        }
    }

    private void displayCirclet() {
        if(currentCharacter.getCirclet() != null) {
            Circlet circlet = currentCharacter.getCirclet();
            displayCircletMainStat.setText(circlet.getMainStatName());
            displayCircletMainStatValue.setText(Double.toString(circlet.getMainStatValue()));

            CircletStat1Name.setText(circlet.getSubStat1Name());
            CircletStat1Value.setText(Double.toString(circlet.getSubStat1Value()));
            CircletStat2Name.setText(circlet.getSubStat2Name());
            CircletStat2Value.setText(Double.toString(circlet.getSubStat2Value()));
            CircletStat3Name.setText(circlet.getSubStat3Name());
            CircletStat3Value.setText(Double.toString(circlet.getSubStat3Value()));
            CircletStat4Name.setText(circlet.getSubStat4Name());
            CircletStat4Value.setText(Double.toString(circlet.getSubStat4Value()));
        } else {
            CircletStat1Name.setText("");
            CircletStat1Value.setText("");
            CircletStat2Name.setText("");
            CircletStat2Value.setText("");
            CircletStat3Name.setText("");
            CircletStat3Value.setText("");
            CircletStat4Name.setText("");
            CircletStat4Value.setText("");
        }
    }

    @FXML
    private void AddArtifactButton() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/addartifact.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            Stage stage = new Stage();
            stage.setTitle("Add Artifact");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void CalculateDamageButton() {
        //only Ayaka formula is done
        if(currentCharacter.getName().equals("Ayaka")) {
            double damage = ayaka.getSoumetsuDamage();
            DamageLabel.setText(String.valueOf(damage));
        }
    }

    public void deleteFromDatabase(String artifactType, Artifact artifact) {
        try {
            String deleteQuery = "DELETE FROM " + artifactType + " WHERE ArtifactID=?";

            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setInt(1, artifact.getID());
            ps.execute();
            ps.close();
        } catch (Exception e) { System.out.println(e); }
    }

    public void deleteFromList(String artifactType, Artifact artifact) {
        try {
            switch (artifactType) {
                case "Flowers":
                    for(int i=0; i<mainController.getFlowersList().size(); i++) {
                        if(mainController.getFlowersList().get(i).getID()==artifact.getID()) {
                            mainController.getFlowersList().remove(i);
                        }
                    }
                    break;
                case "Plumes":
                    for(int i=0; i<mainController.getPlumesList().size(); i++) {
                        if(mainController.getPlumesList().get(i).getID()==artifact.getID()) {
                            mainController.getPlumesList().remove(i);
                        }
                    }
                    break;
                case "Sands":
                    for(int i=0; i<mainController.getSandsList().size(); i++) {
                        if(mainController.getSandsList().get(i).getID()==artifact.getID()) {
                            mainController.getSandsList().remove(i);
                        }
                    }
                    break;
                case "Goblets":
                    for(int i=0; i<mainController.getGobletsList().size(); i++) {
                        if(mainController.getGobletsList().get(i).getID()==artifact.getID()) {
                            mainController.getGobletsList().remove(i);
                        }
                    }
                    break;
                case "Circlets":
                    for(int i=0; i<mainController.getCircletsList().size(); i++) {
                        if(mainController.getCircletsList().get(i).getID()==artifact.getID()) {
                            mainController.getCircletsList().remove(i);
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void DeleteConfirmation(String artifactType, Artifact artifact) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        String displayArtifactName = artifactType;
        if(!artifactType.equals("Sands")) {
            displayArtifactName = artifactType.substring(0, artifactType.length() - 1);
        }
        alert.setHeaderText("DELETING " + displayArtifactName.toUpperCase() + " WITH:");
        alert.setContentText(artifact.getSubStat1Name() + ": " + artifact.getSubStat1Value() + ", " + artifact.getSubStat2Name() + ": " + artifact.getSubStat2Value() + ", "
                        + artifact.getSubStat3Name() + ": " + artifact.getSubStat3Value() + ", " + artifact.getSubStat4Name() + ": " + artifact.getSubStat4Value());
        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            deleteFromDatabase(artifactType, artifact);
            deleteFromList(artifactType, artifact);

            /*switch (artifactType) {
                case "Flowers":
                    FlowersTable.setItems(mainController.getFlowersList());
                    break;
            }

             */
        }
    }


    private void equipArtifactInDB(Artifact artifact, String artifactType) {
        try {
            String artifactTypeColumnName ="";
            switch (artifactType) {
                case "Flower":
                    artifactTypeColumnName = "EquippedFlowerID";
                    break;
                case "Plume":
                    artifactTypeColumnName = "EquippedPlumeID";
                    break;
                case "Sands":
                    artifactTypeColumnName = "EquippedSandsID";
                    break;
                case "Goblet":
                    artifactTypeColumnName = "EquippedGobletID";
                    break;
                case "Circlet":
                    artifactTypeColumnName = "EquippedCircletID";
                    break;
            }

            String query = "UPDATE currentlyequipped SET " + artifactTypeColumnName + " = ? WHERE CharacterName = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, artifact.getID());
            ps.setString(2, currentCharacter.getName());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML private void EquipFlower() {
        try {
            Flower flower = FlowersTable.getSelectionModel().getSelectedItem();
            currentCharacter.equipFlower(flower);
            equipArtifactInDB(flower, "Flower");
            displayFlower();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML private void EquipPlume() {
        try {
            Plume plume = PlumesTable.getSelectionModel().getSelectedItem();
            currentCharacter.equipPlume(plume);
            equipArtifactInDB(plume, "Plume");
            displayPlume();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML private void EquipSands() {
        try {
            Sands sands = SandsTable.getSelectionModel().getSelectedItem();
            currentCharacter.equipSands(sands);
            equipArtifactInDB(sands, "Sands");
            displaySands();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML private void EquipGoblet() {
        try {
            Goblet goblet = GobletsTable.getSelectionModel().getSelectedItem();
            currentCharacter.equipGoblet(goblet);
            equipArtifactInDB(goblet, "Goblet");
            displayGoblet();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML private void EquipCirclet() {
        try {
            Circlet circlet = CircletsTable.getSelectionModel().getSelectedItem();
            currentCharacter.equipCirclet(circlet);
            equipArtifactInDB(circlet, "Circlet");
            displayCirclet();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void DeleteFlower() {
        Flower flower = FlowersTable.getSelectionModel().getSelectedItem();
        String artifactType = "Flowers";
        DeleteConfirmation(artifactType, flower);
    }

    @FXML
    private void DeletePlume() {
        Plume plume = PlumesTable.getSelectionModel().getSelectedItem();
        String artifactType = "Plumes";
        DeleteConfirmation(artifactType, plume);
    }

    @FXML
    private void DeleteSands() {
        Sands sands = SandsTable.getSelectionModel().getSelectedItem();
        String artifactType = "Sands";
        DeleteConfirmation(artifactType, sands);
    }

    @FXML
    private void DeleteGoblet() {
        Goblet goblet = GobletsTable.getSelectionModel().getSelectedItem();
        String artifactType = "Goblets";
        DeleteConfirmation(artifactType, goblet);
    }

    @FXML
    private void DeleteCirclet() {
        Circlet circlet = CircletsTable.getSelectionModel().getSelectedItem();
        String artifactType = "Circlets";
        DeleteConfirmation(artifactType, circlet);
    }
}
