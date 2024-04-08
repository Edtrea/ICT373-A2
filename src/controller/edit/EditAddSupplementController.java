/**
 * @Title: ICT 373 A1
 * @Author: Lim Wen Chao
 * @Date: 2/3/2024
 * @File: EditAddSupplementController.java
 * @Purpose: Controller class for the edit add supplement view
 * @Assumptions:
 * @Limitations:
 */

package controller.edit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import controller.EditController;
import magazine.Supplement;
import model.MagazineModel;
import helper.ValidationHelper;

public class EditAddSupplementController {
    MagazineModel magazineModel;

    @FXML
    private TextField supplementName;

    @FXML
    private Label nameError;

    @FXML
    private TextField supplementCost;

    @FXML
    private Label costError;

    @FXML
    private Button createSupplementButton;

    @FXML
    private Button cancelButton;

    /**
     * Constructor for EditAddSupplementController
     * 
     * @param magazineModel The model for the magazine
     */
    public EditAddSupplementController(MagazineModel magazineModel) {
        this.magazineModel = magazineModel;
    }

    /**
     * createSupplementAction method is called when the createSupplementButton is
     * clicked
     * It creates a new Supplement object with the entered name and cost
     * and adds it to the data model
     */
    @FXML
    void createSupplementAction() {
        // Clear previous error messages
        nameError.setText("");
        costError.setText("");

        // Get the input from the TextField
        String nameInput = supplementName.getText();
        String costInput = supplementCost.getText();
        double cost = 0;

        // Check if the name is not empty
        if (nameInput.isEmpty()) {
            nameError.setText("Name field is empty.");
        } else if (ValidationHelper.isDuplicateSupplement(magazineModel.getMagazine().getSupplements(),
                nameInput)) {
            // Check if the name is unique
            nameError.setText("Name is not unique.");
        }

        if (costInput.isEmpty()) {
            costError.setText("Cost field is empty.");
        } else if (ValidationHelper.validateCost(costInput)) {
            cost = Double.parseDouble(costInput);
        } else {
            costError.setText("Invalid cost.");
        }

        // Create a new Supplement object with the entered name and cost
        Supplement supplement = new Supplement(nameInput, cost);

        // Add the supplement to your data model
        magazineModel.addSupplement(supplement);

        Stage stage = (Stage) createSupplementButton.getScene().getWindow();
        magazineModel.saveModel(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Edit.fxml"));
            // Set the controller factory
            loader.setControllerFactory(c -> new EditController(magazineModel));

            VBox nextLayout = loader.load();
            Scene nextScene = new Scene(nextLayout, 500, 400);

            stage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * cancelAction method is called when the cancelButton is clicked
     * It returns to the edit view
     */
    @FXML
    void cancelAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Edit.fxml"));
            // Set the controller factory
            loader.setControllerFactory(c -> new EditController(magazineModel));

            VBox nextLayout = loader.load();
            Scene nextScene = new Scene(nextLayout, 520, 400);

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
