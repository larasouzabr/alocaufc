package com.alocaufc.controllers;
import com.alocaufc.buiders.SalaBuilder;
import com.alocaufc.entities.Sala;
import com.alocaufc.services.HorarioService;
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
    public Label observacao;

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
        if(selectBloco.getValue() != null) {
            SalaService service = new SalaService();
            HorarioService horarioService = new HorarioService();

            SalaBuilder salaBuilder = SalaBuilder.builder()
                    .setTitulo(tituloSalaField.getText())
                    .setBloco(Bloco.valueOf(Integer.parseInt(selectBloco.getValue().toString())))
                    .setLugares(Integer.parseInt(qtdCadeirasField.getText()))
                    .setObservacao(observacaoField.getText());

            if(projetorCheckbox.isSelected()) salaBuilder.hasProjetor();
            if(arCondCheckbox.isSelected()) salaBuilder.hasArCondicionado();

            Sala sala = salaBuilder.build();

            service.create(sala);
            horarioService.generateHorarios(sala);

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

        } else {
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
