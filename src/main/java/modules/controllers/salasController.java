package modules.controllers;

import interfaceMain.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modules.entities.Sala;
import modules.entities.enums.NumeroBloco;
import modules.services.SalaService;

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
        FXMLLoader fxmlLoader = new FXMLLoader(salasController.class.getResource(caminhoPagina));
            Scene scene = new Scene(fxmlLoader.load(), 388, 458);
            Stage newStage = new Stage();
            newStage.setTitle("Registrar Sala");
            newStage.setScene(scene);
            newStage.show();
    }
    // method receives parameters from add window
    public void addNewSala(NumeroBloco numBloco, int numeroSala, int qtdCadeiras, boolean projetor, boolean arCondicionado){
        SalaService service = new SalaService();
        Sala sala = new Sala(null,numBloco,numeroSala,qtdCadeiras,projetor, arCondicionado);
        service.create(sala);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void checkBlocoInfo(ActionEvent actionEvent) throws IOException {
        String caminhoPagina = "/interfaceSala/sala.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(salasController.class.getResource(caminhoPagina));
        Scene scene = new Scene(fxmlLoader.load(), 1044, 590);
        Stage newStage = new Stage();
        newStage.setTitle("Salas do bloco 1");
        newStage.setScene(scene);
        newStage.show();
    }
}
