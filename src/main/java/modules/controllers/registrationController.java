package modules.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modules.entities.enums.NumeroBloco;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class registrationController implements Initializable {

    public Button CancelRegistrationButton;
    public Button addNewSalaButton;
    public Label numeroSalaLabel;
    public Label possuiProjetorLabel;
    public Label blocoLabel;
    public Label arCondLabel;
    public Label qtdCadeirasLabel;
    public TextField numeroSalaField;
    public TextField qtdCadeirasField;
    public CheckBox projetorCheckbox;
    public CheckBox arCondCheckbox;

    @FXML private  ComboBox selectBloco;

    ObservableList<Integer> options;
    public salasController salasController;

    @FXML
   void closeButtonAction(ActionEvent event){

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void saveSalaInfoAction(ActionEvent event){
        salasController SalaContr = new salasController();
        if(selectBloco.getValue() != null){
        SalaContr.addNewSala (
                            NumeroBloco.valueOf((Integer) selectBloco.getValue()),
                            Integer.parseInt(numeroSalaField.getText()),
                            Integer.parseInt(qtdCadeirasField.getText()),
                            Boolean.parseBoolean(String.valueOf(projetorCheckbox.isSelected())),
                            Boolean.parseBoolean(String.valueOf(arCondCheckbox.isSelected())));

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
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Bloco não pode ser nulo");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NumeroBloco[] numerosBloco = NumeroBloco.class.getEnumConstants();
        ArrayList<Integer> numsBlocoValues = new ArrayList<>();
        for(NumeroBloco nb: numerosBloco) {
            numsBlocoValues.add(nb.getNumero());
        }
        options = FXCollections.observableArrayList(numsBlocoValues);

        selectBloco.getItems().setAll(options);
    }
    public void setParentController(salasController documentController) {
            this.salasController = documentController;
    }

}
