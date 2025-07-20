package chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatController {
    @FXML
    private ListView<String> messageArea;
    @FXML
    private TextArea messageInput;

    private PrintWriter out;
    private String username; // We need to store the username here

    // This method is called by the LoginController to pass the username
    public void initData(String username) {
        this.username = username; // Store the username
        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Announce that this user has joined the chat
            out.println(this.username + " has joined the chat.");

            // Start a new thread to listen for messages from the server
            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        final String finalMessage = serverMessage;
                        // Update the UI with messages from others
                        Platform.runLater(() -> messageArea.getItems().add(finalMessage));
                    }
                } catch (IOException e) {
                    Platform.runLater(() -> messageArea.getItems().add("Error: Disconnected from server."));
                }
            }).start();
        } catch (IOException e) {
            Platform.runLater(() -> messageArea.getItems().add("Could not connect to the server."));
        }
    }

    @FXML
    private void sendMessage() {
        String message = messageInput.getText();
        if (out != null && !message.isEmpty()) {
            // FIX #1: Send the username along with the message to the server
            String fullMessageToServer = this.username + ": " + message;
            out.println(fullMessageToServer);

            // FIX #2: Add your own message to your window immediately
            String messageForMyScreen = "You: " + message;
            messageArea.getItems().add(messageForMyScreen);

            messageInput.clear();
        }
    }
}