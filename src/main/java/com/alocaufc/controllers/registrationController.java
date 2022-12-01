package com.alocaufc.controllers;
import com.alocaufc.entities.Sala;
import com.alocaufc.services.SalaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.alocaufc.entities.enums.Bloco;

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
    public TextField tituloSalaField;
    public TextField qtdCadeirasField;
    public CheckBox projetorCheckbox;
    public CheckBox arCondCheckbox;

    public TextArea observacaoField;

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
        if(selectBloco.getValue() != null){
        SalaService service = new SalaService();
        service.create(new Sala(
                null,
                tituloSalaField.getText(),
                (int) selectBloco.getValue(),
                Integer.parseInt(qtdCadeirasField.getText()),
                Boolean.parseBoolean(String.valueOf(projetorCheckbox.isSelected())),
                Boolean.parseBoolean(String.valueOf(arCondCheckbox.isSelected())),
                null,
                observacaoField.getText()
        ));
//
//        salasController SalaContr = new salasController();
//        if(selectBloco.getValue() != null){
//        SalaContr.addNewSala (
//                            Bloco.valueOf((Integer) selectBloco.getValue()),
//                            Integer.parseInt(numeroSalaField.getText()),
//                            Integer.parseInt(qtdCadeirasField.getText()),
//                            Boolean.parseBoolean(String.valueOf(projetorCheckbox.isSelected())),
//                            Boolean.parseBoolean(String.valueOf(arCondCheckbox.isSelected())));

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
        Bloco[] numerosBloco = Bloco.class.getEnumConstants();
        ArrayList<Integer> numsBlocoValues = new ArrayList<>();
        for(Bloco nb: numerosBloco) {
            numsBlocoValues.add(nb.getNumero());
        }
        options = FXCollections.observableArrayList(numsBlocoValues);

        selectBloco.getItems().setAll(options);
    }
    public void setParentController(salasController documentController) {
            this.salasController = documentController;
    }

}
