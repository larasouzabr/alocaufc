package com.alocaufc.controllers;

import com.alocaufc.Main;
import com.alocaufc.entities.enums.Bloco;
import com.alocaufc.utils.BlocoHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.alocaufc.entities.Sala;
import com.alocaufc.services.SalaService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class salasController implements Initializable {

    public Button addSalaButton;

    public static void main(String[] args) {
    }

    @FXML
    void salaAddButtonAction(ActionEvent event) throws IOException {
        String caminhoPagina = "/interfaceRegistration/Registration.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(caminhoPagina));
            Scene scene = new Scene(fxmlLoader.load(), 388, 458);
            Stage newStage = new Stage();
            newStage.setTitle("Registrar Sala");
            newStage.setScene(scene);
            newStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void checkBlocoInfo(ActionEvent actionEvent) throws IOException {
        String caminhoPagina = "/interfaceSala/sala.fxml";

        Node node = (Node) actionEvent.getSource();
        String bloco = (String) node.getUserData();
        BlocoHolder holder = BlocoHolder.getInstance();
        holder.setBloco(Bloco.valueOf(Integer.parseInt(bloco)));

        FXMLLoader fxmlLoader = new FXMLLoader(salasController.class.getResource(caminhoPagina));
        Scene scene = new Scene(fxmlLoader.load(), 1293, 687);
        Stage newStage = new Stage();
        newStage.setTitle("Salas do bloco " + bloco);
        newStage.setScene(scene);
        newStage.show();
    }
}
