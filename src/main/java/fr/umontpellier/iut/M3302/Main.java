package fr.umontpellier.iut.M3302;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fr.umontpellier.iut.M3302/fxml/MainMenu.fxml"));
        primaryStage.setTitle("Sudoku");

        primaryStage.setScene(new Scene(root, 900, 720));
        primaryStage.show();
    }

}
