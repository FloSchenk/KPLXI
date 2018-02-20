package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private String[] arguments;
    private Stage primaryStage;
    private VBox root; //change to the base node

    @Override
    public void start(Stage primaryStage) {
        setPrimaryStage(primaryStage);
        loadApplicationWindow();
    }

    private void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void loadApplicationWindow() {
        primaryStage.setTitle("Str8ts Solver");
        primaryStage.setResizable(false);

        try {
            root = FXMLLoader.load(getClass().getResource("model/ApplicationModel.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startApplication() {
        launch(arguments);
    }

    public void setArguments(String... args) {
        this.arguments = args;
    }
}
