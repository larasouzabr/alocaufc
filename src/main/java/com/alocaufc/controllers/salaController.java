package com.alocaufc.controllers;

import com.alocaufc.entities.enums.Bloco;
import com.alocaufc.services.SalaService;
import com.alocaufc.utils.BlocoHolder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.alocaufc.entities.Aula;
import com.alocaufc.entities.Sala;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class salaController implements Initializable{
    public TableView<Sala> tabelaSalas;
    public TableView<Aula> tabelaAulasSala;
    public TableColumn<Sala, String> salaTituloCell;
    public TableColumn<Sala, Integer> lugaresCell;
    public TableColumn<Sala, Boolean> projetorCell;
    public TableColumn<Aula,String> horarioCell;
    public TableColumn<Aula,Integer> diaDaSemanaCell;
    public TableColumn<Aula, String> disciplinaCell;
    public TableColumn<Aula,String> turmaCell;
    public TableColumn<Sala, Boolean> arcondtCell;
    public TextField searchField;
    public Text numeroBloco;
    public TableColumn<Sala, Boolean> acoesCell;
    public static ObservableList<Sala> data_table;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      initializeColumns();
      addDataToColumns();
    }

    private void initializeColumns(){
        BlocoHolder holder = BlocoHolder.getInstance();
        numeroBloco.setText(holder.getBloco().getNumero().toString());
        salaTituloCell.setCellValueFactory(new PropertyValueFactory<Sala,String>("titulo"));
        lugaresCell.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("lugares"));
        arcondtCell.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getArCondicionado()));
        arcondtCell.setCellFactory(col -> new TableCell<Sala, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty) ;
                setText(empty ? null : item ? "Sim" : "Não" );
            }
        });
        projetorCell.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getProjetor()));
        projetorCell.setCellFactory(col -> new TableCell<Sala, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty) ;
                setText(empty ? null : item ? "Sim" : "Não" );
            }
        });
        acoesCell.setCellFactory(new Callback<>() {
            final Stage stage = new Stage();

            @Override
            public TableCell<Sala, Boolean> call(TableColumn<Sala, Boolean> personBooleanTableColumn) {
                return new EditSala(stage, tabelaSalas);
            }
        });
    }

    private void addDataToColumns(){
        data_table = FXCollections.observableArrayList();
        SalaService salaService = new SalaService();

        BlocoHolder holder = BlocoHolder.getInstance();
        List<Sala> salas = salaService.getByBloco(holder.getBloco().getNumero());

        for(Sala sala: salas) {
            data_table.add(sala);
        }

//        for (int x = 1; x < 12; x++) {
//           data_table.add( new Sala(null, "Sala X", Bloco.BLOCO_1.getNumero(),x,true,true, null, ""));
//        }

        tabelaSalas.setItems(data_table);
    }

    private class EditSala extends TableCell<Sala, Boolean> {
        final Button editButton       = new Button("Editar");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        EditSala(final Stage stage, final TableView table) {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(editButton);
            editButton.setOnMousePressed(mouseEvent -> buttonY.set(mouseEvent.getScreenY()));
            editButton.setOnAction(actionEvent -> {
                showEditSalaDialog(stage, table, buttonY.get());
                table.getSelectionModel().select(getTableRow().getIndex());
            });
        }

        @Override protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }


    private void showEditSalaDialog(Stage parent, final TableView<Sala> table, double y) {
        final Stage dialog = new Stage();
        dialog.setTitle("Edit Sala");
        dialog.initOwner(parent);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setX(parent.getX() + parent.getWidth());
        dialog.setY(y);

        GridPane grid = new GridPane();
        final Label numSala = new Label();
        final CheckBox HasProjetor = new CheckBox();
        final CheckBox HasArcond = new CheckBox();
        final TextField qtdCadeiras= new TextField();

        //numSala.setText(Integer.toString(tabelaSalas.getSelectionModel().getSelectedItem().getNumSala()));
        numSala.setText(Integer.toString(1));
        qtdCadeiras.setText(Integer.toString(1));
        HasArcond.setSelected(true);
        HasProjetor.setSelected(true);
//        qtdCadeiras.setText(Integer.toString(tabelaSalas.getSelectionModel().getSelectedItem().getQtdCadeiras()));
//        HasArcond.setSelected(tabelaSalas.getSelectionModel().getSelectedItem().getArCondidionado());
//        HasProjetor.setSelected(tabelaSalas.getSelectionModel().getSelectedItem().getProjetor());


        grid.addRow(0, new Label("Numero da sala"), numSala);
        grid.addRow(1, new Label("Quantidade Cadeira"), qtdCadeiras);
        grid.addRow(2, new Label("Ar Condicionado"), HasArcond);
        grid.addRow(2, new Label("Projetor"), HasProjetor);

        grid.setHgap(10);
        grid.setVgap(10);
        GridPane.setHgrow(numSala, Priority.ALWAYS);
        GridPane.setHgrow(qtdCadeiras, Priority.ALWAYS);

        // create action buttons for the dialog.
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        Button cancel = new Button("Cancel");
        cancel.setCancelButton(true);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                int Index = table.getSelectionModel().getSelectedIndex();
                table.getItems().set(Index, new Sala(null, "Sala X", Bloco.BLOCO_1.getNumero(),1,true,true, null, ""));
                //table.getItems().set(Index, new Sala(null,NumeroBloco.BLOCO_1,tabelaSalas.getSelectionModel().getSelectedItem().getNumSala(), Integer.parseInt(qtdCadeiras.getText()), HasProjetor.isSelected(), HasArcond.isSelected()));
                dialog.close();
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                dialog.close();
            }
        });
        HBox r = new HBox();
        r.getChildren().addAll(ok,cancel);
        r.setSpacing(3.0);
        r.setAlignment(Pos.CENTER_RIGHT);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid,r);
        layout.setPadding(new Insets(5));
        dialog.setScene(new Scene(layout));
        dialog.show();
    }



}