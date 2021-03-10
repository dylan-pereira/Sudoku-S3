package fr.umontpellier.iut.M3302.UI;

import fr.umontpellier.iut.M3302.sudoku.Difficulty;
import fr.umontpellier.iut.M3302.sudoku.Game;
import fr.umontpellier.iut.M3302.sudoku.checkers.Checker;
import fr.umontpellier.iut.M3302.sudoku.checkers.CheckerFactory;
import fr.umontpellier.iut.M3302.sudoku.generators.Generator;
import fr.umontpellier.iut.M3302.sudoku.generators.GeneratorFactory;
import fr.umontpellier.iut.M3302.sudoku.solvers.Solver;
import fr.umontpellier.iut.M3302.sudoku.solvers.SolverFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    public Button launchGame;
    @FXML
    public Button closeGame;
    @FXML
    public Button rules;
    @FXML
    private ChoiceBox<Difficulty> difficulty;
    @FXML
    private ChoiceBox<Integer> size;


    @FXML
    public void initialize() {
        difficulty.getSelectionModel().select(0);
        difficulty.valueProperty().addListener((observableValue, d, d1) -> difficulty.getSelectionModel().select(d1));
        size.getSelectionModel().select(1);
        size.valueProperty().addListener(((observableValue, integer, t1) -> size.getSelectionModel().select(t1)));
    }

    @FXML
    private void launchGame(MouseEvent event) throws IOException {
        Checker checker = CheckerFactory.makeChecker("clasic", size.getValue(), difficulty.getValue());
        Solver solver = SolverFactory.makeSolver("backTackingSolver",checker);
        Generator generator = GeneratorFactory.makeGenerator("rndNonUniGen", size.getValue(), difficulty.getValue(),
                checker, solver);
        Game game = new Game(size.getValue(), generator, checker, solver);

        GameController gameController = new GameController(game);

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/fr.umontpellier.iut.M3302/fxml/Game.fxml"));
        gameLoader.setController(gameController);

        Parent gamePane = gameLoader.load();

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.getScene().setRoot(gamePane);

//        primaryStage.getScene().widthProperty().addListener(((observable) -> gameController.resize(primaryStage)));
//        primaryStage.getScene().heightProperty().addListener(((observable) -> gameController.resize(primaryStage)));
        primaryStage.getScene().heightProperty().addListener((observable, oldValue, newValue) -> gameController.resize(
                newValue.doubleValue()));
        primaryStage.getScene().widthProperty().addListener((observable, oldValue, newValue) -> gameController.resize(
                newValue.doubleValue()));
        primaryStage.getScene().setOnKeyReleased(keyEvent -> {
            try {
                gameController.shortcuts(keyEvent);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @FXML
    private void rules(MouseEvent event) throws IOException {
        Parent rulesLoad = FXMLLoader.load(getClass().getResource("/fr.umontpellier.iut.M3302/fxml/Rules.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.getScene().setRoot(rulesLoad);
    }

    @FXML
    private void closeGame(MouseEvent event) {
        Platform.exit();
    }

}
