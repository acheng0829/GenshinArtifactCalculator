package main;

import database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/mainwindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("MAIN WINDOW");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Boolean connected = DatabaseConnection.openConnection();
        if(connected == true) {
            launch();
            DatabaseConnection.closeConnection();
        }
        else { System.out.println("Unable to Establish Connection to Database"); }
    }
}