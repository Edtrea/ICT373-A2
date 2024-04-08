
/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/3/2024
 * @File: Cliet.java
 * @Purpose: A class that represents the client application. The start point of the application.
 * @Assumptions:
 * @Limitations:
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/StartPage.fxml"));
        VBox vbox = loader.<VBox>load();

        primaryStage.setTitle("Magazine Service");

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}