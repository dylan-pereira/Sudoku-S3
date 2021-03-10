package fr.umontpellier.iut.M3302.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RulesController {

    @FXML
    public Button mainMenu;
    @FXML
    private void mainMenu(MouseEvent event) throws IOException {
        Parent mainMenuLoad = FXMLLoader.load(getClass().getResource("/fr.umontpellier.iut.M3302/fxml/MainMenu.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.getScene().setRoot(mainMenuLoad);
    }
}
