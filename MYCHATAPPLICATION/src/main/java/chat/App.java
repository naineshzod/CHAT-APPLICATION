package chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // The path is now absolute from the root of the resources folder
        
        URL fxmlLocation = getClass().getResource("/chat/login.fxml");
        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Chat Application");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}