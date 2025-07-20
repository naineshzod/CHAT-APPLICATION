package chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {
        String username = usernameField.getText();
        if (!username.isEmpty()) {
            try {
                URL fxmlLocation = getClass().getResource("/chat/chat.fxml");
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent root = loader.load();

                ChatController chatController = loader.getController();
                chatController.initData(username);

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Chat - " + username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}