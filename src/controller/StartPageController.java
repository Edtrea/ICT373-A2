/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/3/2024
 * @File: StartPageController.java
 * @Purpose: Controller class for the start page
 * @Assumptions:
 * @Limitations:
 */

package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MagazineModel;

public class StartPageController {
    @FXML
    private Label studentDetailsLabel;

    @FXML
    private Button startButton;

    /**
     * Initializes the controller class. This method is automatically called after
     * the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        studentDetailsLabel.setText(displayStudentDetails());
    }

    /**
     * Handle the start button action
     */
    @FXML
    private void startButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Create.fxml"));
            MagazineModel magazineModel = new MagazineModel();
            // Set the controller factory
            loader.setControllerFactory(c -> new CreateController(magazineModel));

            VBox nextLayout = loader.load();
            Scene nextScene = new Scene(nextLayout, 300, 200);

            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display student details
     * 
     * @return student details
     */
    static String displayStudentDetails() {
        StringBuilder studentDetails = new StringBuilder();
        studentDetails.append("Name: Lim Wen Chao\n");
        studentDetails.append("Student ID: 34368872\n");
        studentDetails.append("Mode of Enrolment: Kaplan Singapore\n");
        studentDetails.append("Tutor Name: Andy Lee Kok Eng\n");
        studentDetails.append("Tutorial Day and Time: Tuesday 2:15PM\n");
        return studentDetails.toString();
    }
}
