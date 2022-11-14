package modules.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modules.entities.Aula;
import modules.entities.Sala;
import modules.entities.enums.NumeroBloco;

import java.net.URL;
import java.util.ResourceBundle;

public class salaController implements Initializable{
    public TableView<Sala> tabelaSalas;
    public TableView<Aula> tabelaAulasSala;
    public TableColumn<Sala, Integer> numSalaCell;
    public TableColumn<Sala, Integer> qtdCadeirasCell;
    public TableColumn<Sala, Boolean> projetorCell;
    public TableColumn<Aula,String> horarioCell;
    public TableColumn<Aula,Integer> diaDaSemanaCell;
    public TableColumn<Aula, String> disciplinaCell;
    public TableColumn<Aula,String> turmaCell;
    public ChoiceBox filterChoiceBox;
    public TextField searchField;
    public TableColumn acoesCell;
    public TableColumn<Sala, Boolean> arCondCell;

    ObservableList<Sala> listSala = FXCollections.observableArrayList(
      new Sala(null, NumeroBloco.BLOCO_1, 3 , 22, true , true),
            new Sala(null, NumeroBloco.BLOCO_1, 5 , 33, false , true)  ,
            new Sala(null, NumeroBloco.BLOCO_1, 2 , 50, true , true)
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numSalaCell.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("numSala"));
        qtdCadeirasCell.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("qtdCadeiras"));
        projetorCell.setCellValueFactory(new PropertyValueFactory<Sala,Boolean>("projetor"));
        arCondCell.setCellValueFactory(new PropertyValueFactory<Sala,Boolean>("arCondicionado"));

        tabelaSalas.setItems(listSala);

    }
}
