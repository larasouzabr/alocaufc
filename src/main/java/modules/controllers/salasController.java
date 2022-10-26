package modules.controllers;

import modules.controllers.registrationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modules.entities.Sala;
import modules.entities.enums.NumeroBloco;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class salasController implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    void salaAddButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tela adicionar sala");
            stage.setScene(new Scene(root));
            loader.<registrationController>getController()
                    .setParentController(this);
            stage.show();

        } catch (IOException ex) {
        }

    }
    // method receives parameters from add window
    public void addNewSala(int id, NumeroBloco numBloco, int numeroSala, int qtdCadeiras, boolean projetor, boolean arCondicionado){
        Sala sala = new Sala(id,numBloco,numeroSala,qtdCadeiras,projetor, arCondicionado);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
