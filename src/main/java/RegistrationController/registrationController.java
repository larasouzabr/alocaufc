package RegistrationController;
import SalasController.salasController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modules.entities.enums.NumeroBloco;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class registrationController implements Initializable {
    @FXML private TextField NumeroSalaField,qtdCadeirasField;
    @FXML private Label blocoLabel,numeroSalaLabel, projetorLabel,numeroCadeirasLabel;
    @FXML private javafx.scene.control.Button closeButton;
    @FXML private  ComboBox selectBloco,selectProjetorSituation,selectAirCondSituation;

    ObservableList<Integer> options;
    String[] options2 = {"Sim","Não"};
    public salasController salasController;

    @FXML
    void closeButtonAction(ActionEvent event){

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void saveSalaInfoAction(ActionEvent event){
        salasController.addNewSala (
                            2,
                            NumeroBloco.valueOf(selectBloco.getPromptText()),
                            Integer.parseInt(NumeroSalaField.getText()),
                            Integer.parseInt(qtdCadeirasField.getText()),
                            Boolean.parseBoolean(selectProjetorSituation.getPromptText()),
                            Boolean.parseBoolean(selectAirCondSituation.getPromptText()));

                    // Show successfully new classroom addition dialog
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Sala adicionada com sucesso!!");
                    alert.setContentText(null);
                    alert.showAndWait();


                // Closing the window after the save/update has been successfully finished
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NumeroBloco[] numerosBloco = NumeroBloco.class.getEnumConstants();
        ArrayList<Integer> numsBlocoValues = new ArrayList<>();
        for(NumeroBloco nb: numerosBloco) {
            numsBlocoValues.add(nb.getNumero());
        }

//        options2 = FXCollections.observableArrayList(selectProjetorSituation);
//        selectAirCondSituation.getItems().setAll(options2);
//        selectProjetorSituation.getItems().setAll(options2);

        options = FXCollections.observableArrayList(numsBlocoValues);

        selectBloco.getItems().setAll(options);
    }
    public void setParentController(salasController documentController) {
            this.salasController = documentController;
    }

}
