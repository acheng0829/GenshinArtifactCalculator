package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Flower;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class rollProbabilityController {

    @FXML private Label MainStatType;
    @FXML private Label MainStatValue;
    @FXML private Label Probability;
    @FXML private Label Fraction;


    @FXML private ComboBox ArtifactType;
    @FXML private ComboBox SubstatType1;
    @FXML private ComboBox SubstatType2;
    @FXML private ComboBox SubstatType3;
    @FXML private ComboBox SubstatType4;
    @FXML private ComboBox CalculationType;

    @FXML private TextField SubstatValue1;
    @FXML private TextField SubstatValue2;
    @FXML private TextField SubstatValue3;
    @FXML private TextField SubstatValue4;
    @FXML private TextField ArtifactLevel;

    @FXML
    private void initialize() {
        try {
            ObservableList<String> SubstatTypesList = FXCollections.observableArrayList();
            ObservableList<String> ArtifactTypesList = FXCollections.observableArrayList();
            ObservableList<String> CalculationTypesList = FXCollections.observableArrayList();

            SubStatComboBoxes(SubstatTypesList);
            ArtifactTypeComboBox(ArtifactTypesList);
            CalculationTypeComboBox(CalculationTypesList);

            MainStatValue.setText("HP");
            MainStatValue.setText("4780");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void CalculationTypeComboBox(ObservableList<String> CalculationTypes) {
        CalculationTypes.add("ALL CRIT ROLLS");
        CalculationTypes.add("5 Crit DMG Rolls");

        CalculationType.setValue(CalculationTypes.get(0));
        CalculationType.setItems(CalculationTypes);
    }

    private void ArtifactTypeComboBox(ObservableList<String> ArtifactTypes) {
        ArtifactTypes.add("Flower");
        ArtifactTypes.add("Plume");
        ArtifactTypes.add("Sands");
        ArtifactTypes.add("Goblet");
        ArtifactTypes.add("Circlet");

        ArtifactType.setValue(ArtifactTypes.get(0));
        ArtifactType.setItems(ArtifactTypes);
    }

    private void SubStatComboBoxes(ObservableList<String> SubstatTypes) {
        SubstatTypes.add("HP Flat");
        SubstatTypes.add("ATK Flat");
        SubstatTypes.add("DEF Flat");
        SubstatTypes.add("HP%");
        SubstatTypes.add("ATK%");
        SubstatTypes.add("DEF%");
        SubstatTypes.add("EM");
        SubstatTypes.add("ER");
        SubstatTypes.add("CRIT RATE");
        SubstatTypes.add("CRIT DMG");

        SubstatType1.setValue(SubstatTypes.get(0));
        SubstatType2.setValue(SubstatTypes.get(0));
        SubstatType3.setValue(SubstatTypes.get(0));

        SubstatType1.setItems(SubstatTypes);
        SubstatType2.setItems(SubstatTypes);
        SubstatType3.setItems(SubstatTypes);
        ObservableList<String> SubstatTypesListWithNone = FXCollections.observableArrayList();

        SubstatTypesListWithNone.add("None");
        for(String s : SubstatTypes) { SubstatTypesListWithNone.add(s);}
        SubstatType4.setValue(SubstatTypesListWithNone.get(0));
        SubstatType4.setItems(SubstatTypesListWithNone);

    }
    @FXML
    private void ArtifactTypeComboBoxAction() {
        String artifactType = (String) ArtifactType.getSelectionModel().getSelectedItem();
        if(artifactType.equals("Flower")) {
            MainStatType.setText("HP");
            MainStatValue.setText("4780");
        }
        else if(artifactType.equals("Plume")) {
            MainStatType.setText("ATK");
            MainStatValue.setText("311");
        }
        else if(artifactType.equals("Sands")) {
            MainStatType.setText("");
            MainStatValue.setText("");
        }
        else if(artifactType.equals("Goblet")) {
            MainStatType.setText("");
            MainStatValue.setText("");
        }
        else if(artifactType.equals("Circlet")) {
            MainStatType.setText("");
            MainStatValue.setText("");
        }
        else {
            System.out.println("Error in selecting artifact type");
        }

        /*
        ObservableList<Appointment> appointmentsForContact = FXCollections.observableArrayList();
        String contactName = (String) ContactComboBox.getSelectionModel().getSelectedItem();
        int contactID = -1;
        for(Contact contact : contactsList) {
            if(contact.getContactName().equals(contactName)) { contactID = contact.getContactId(); }
        }

        for(Appointment appointment : appointmentsList) {
            if(appointment.getContactID() == contactID) { appointmentsForContact.add(appointment); }
        }

        ContactAppointmentsTable.setItems(appointmentsForContact);

         */

    }

    @FXML
    private void CalculationComboBoxAction() {

    }

    @FXML private void CalculateButtonAction() {
        try {
            String artifactType = (String) ArtifactType.getSelectionModel().getSelectedItem();
            int artifactLevel = Integer.valueOf(ArtifactLevel.getText());
            String substat1 = (String) SubstatType1.getSelectionModel().getSelectedItem();
            String substat2 = (String) SubstatType2.getSelectionModel().getSelectedItem();
            String substat3 = (String) SubstatType3.getSelectionModel().getSelectedItem();
            String substat4 = (String) SubstatType4.getSelectionModel().getSelectedItem();
            double substat1Val = Double.parseDouble(SubstatValue1.getText());
            double substat2Val = Double.parseDouble(SubstatValue2.getText());
            double substat3Val = Double.parseDouble(SubstatValue3.getText());
            double substat4Val = Double.parseDouble(SubstatValue4.getText());
            String calculationType = (String) CalculationType.getSelectionModel().getSelectedItem();

            int rollsLeft = 5 - (artifactLevel/4);
            double critSubstats = 0;

            if(substat1.equals("CRIT RATE") || substat1.equals("CRIT DMG")) { critSubstats++; }
            if(substat2.equals("CRIT RATE") || substat2.equals("CRIT DMG")) { critSubstats++; }
            if(substat3.equals("CRIT RATE") || substat3.equals("CRIT DMG")) { critSubstats++; }
            if(substat4.equals("CRIT RATE") || substat4.equals("CRIT DMG")) { critSubstats++; }

            if(calculationType.equals("ALL CRIT ROLLS")) {
                double denominator = 1024; //how many possibilities left for rolls depending on artifact level
                for(int i=0; i<5-rollsLeft; i++) { denominator = denominator/4;}

                if(artifactType.equals("Flower")){
                    /*double probability = (critSubstats/4);
                    for(int i=1; i<rollsLeft; i++) {
                        probability = probability * (critSubstats/4);
                    }
                     */


                    double numerator = 1;

                    if(critSubstats == 2) {
                        for(int i=0; i<rollsLeft; i++) { numerator = numerator * 2; }}

                    double probability = numerator/denominator * 100;

                    BigDecimal bd = new BigDecimal(probability).setScale(4, RoundingMode.HALF_UP);
                    double rounded = bd.doubleValue();

                    Probability.setText((rounded) + "%");
                    Fraction.setText(numerator + "/" + denominator);
                }
                //An artifact cannot have duplicate sub-stats, and its sub-stats cannot be the same as the main stat
                //artifactType;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
