package com.alocaufc.controllers;

import com.alocaufc.entities.Horario;
import com.alocaufc.entities.enums.Bloco;
import com.alocaufc.entities.enums.DiaSemana;
import com.alocaufc.services.AulaService;
import com.alocaufc.services.HorarioService;
import com.alocaufc.services.SalaService;
import com.alocaufc.utils.BlocoHolder;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.alocaufc.entities.Aula;
import com.alocaufc.entities.Sala;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class salaController implements Initializable{
    public static ObservableList<Sala> data_table;
    public TableView<Sala> tabelaSalas;
    public TableColumn<Sala, String> salaTituloCell;
    public TableColumn<Sala, Integer> lugaresCell;
    public TableColumn<Sala, Boolean> projetorCell;
    public TableColumn<Sala, String> observacoesSalaCell;
    public TableColumn<Sala, Boolean> arcondtCell;
    public TableColumn<Sala, Boolean> acoesCell;

    public static ObservableList<Horario> data_table_aula;
    public TableView<Horario> tabelaAulasSala;
    public TableColumn<Horario, String> horarioCell;
    public TableColumn<Horario,String> diaDaSemanaCell;
    public TableColumn<Horario, String> disciplinaCell;
    public TableColumn<Horario,String> observacoesAulaCell;
    public TableColumn<Horario,String> turmaCell;
    public TableColumn<Horario, Boolean>  alocarCell;
    public TableColumn<Horario, Boolean>  deletarCell;
    public TextField searchField;
    public Text numeroBloco;
    public ChoiceBox filterChoiceBox;
    public Label salaAlocLabel;
    public AnchorPane SalaManagement;
    public Label salaAlocLabelName;
    public TableColumn acoesColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      init();
    }

    public void init(){
        initializeSalaColumns();
        addDataToColumns();
        initializeScheduleColumns();
    }


    private void initializeSalaColumns(){
        BlocoHolder holder = BlocoHolder.getInstance();
        numeroBloco.setText(holder.getBloco().getNumero().toString());
        salaTituloCell.setCellValueFactory(new PropertyValueFactory<Sala,String>("titulo"));
        lugaresCell.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("Lugares"));
        observacoesSalaCell.setCellValueFactory(new PropertyValueFactory<Sala,String>("observacao"));
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

    private void initializeScheduleColumns(){

        horarioCell.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio() + " - " + cellData.getValue().getHoraFim()));
        diaDaSemanaCell.setCellValueFactory(cellData -> new SimpleStringProperty(DiaSemana.valueOf(cellData.getValue().getDiaSemana()).toString()));
        disciplinaCell.setCellValueFactory (cellData -> cellData.getValue().getAula() != null ? new SimpleStringProperty(cellData.getValue().getAula().getDisciplina()) : new SimpleStringProperty("-") );
        turmaCell.setCellValueFactory(cellData -> cellData.getValue().getAula() != null ? new SimpleStringProperty(cellData.getValue().getAula().getTurma()) : new SimpleStringProperty("-"));
        observacoesAulaCell.setCellValueFactory(cellData -> cellData.getValue() != null ? new SimpleStringProperty(cellData.getValue().getObservacao()) : new SimpleStringProperty("-"));

        diaDaSemanaCell.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item);
                } else {
                    setText("");
                }
            }
        });


        disciplinaCell.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item);
                } else {
                    setText("-");
                }
            }
        });
        turmaCell.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item);
                } else {
                    setText("-");
                }
            }
        });
        alocarCell.setCellFactory(new Callback<>() {
            final Stage stage = new Stage();
            @Override
            public TableCell<Horario, Boolean> call(TableColumn<Horario, Boolean> aulaBooleanColumn) {
                return new AlocarAula(stage, tabelaAulasSala);
            }
        });
        deletarCell.setCellFactory(new Callback<>() {
            final Stage stage = new Stage();
            @Override
            public TableCell<Horario, Boolean> call(TableColumn<Horario, Boolean> aulaBooleanColumn) {
                return new DeletarAula(stage, tabelaAulasSala);
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
        tabelaSalas.setItems(data_table);
    }
    @FXML
    void checkSelectedCel(MouseEvent event){
        data_table_aula = FXCollections.observableArrayList();
        tabelaSalas.refresh();
        Sala sala = tabelaSalas.getSelectionModel().getSelectedItem();
        List<Horario> horariosDaSala = sala.getHorarios();
        for(Horario salaHorario: horariosDaSala) {
            data_table_aula.add(salaHorario);
        }

        tabelaAulasSala.setItems(data_table_aula);
        salaAlocLabelName.setText(sala.getTitulo());

    }
    private class EditSala extends TableCell<Sala, Boolean> {
        File imgFile = new File("src/main/resources/interfaceSala/image/edit-icon.png");
        ImageView imgV = new ImageView(imgFile.toURI().toString());
        final Button editButton       = new Button("",imgV);
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
        final TextArea observacao = new TextArea();

        numSala.setText(tabelaSalas.getSelectionModel().getSelectedItem().getTitulo());
        qtdCadeiras.setText(Integer.toString(tabelaSalas.getSelectionModel().getSelectedItem().getLugares()));
        HasArcond.setSelected(tabelaSalas.getSelectionModel().getSelectedItem().getArCondicionado());
        HasProjetor.setSelected(tabelaSalas.getSelectionModel().getSelectedItem().getProjetor());
        observacao.setText(tabelaSalas.getSelectionModel().getSelectedItem().getObservacao());

        grid.addRow(0, new Label("Titulo da sala"), numSala);
        grid.addRow(1, new Label("Quantidade Cadeira"), qtdCadeiras);
        grid.addRow(2, new Label("Ar Condicionado"), HasArcond);
        grid.addRow(2, new Label("Projetor"), HasProjetor);
        grid.addRow(3, new Label("Observação"), observacao);

        grid.setHgap(10);
        grid.setVgap(10);
        GridPane.setHgrow(numSala, Priority.ALWAYS);
        GridPane.setHgrow(qtdCadeiras, Priority.ALWAYS);

        // create action buttons for the dialog.
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        Button cancel = new Button("Cancelar");
        cancel.setCancelButton(true);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                SalaService salaService = new SalaService();
                Sala sala = salaService.obterPorId(table.getSelectionModel().getSelectedItem().getId());
                sala.setLugares(Integer.parseInt(qtdCadeiras.getText()));
                sala.setArCondicionado(HasArcond.isSelected());
                sala.setProjetor(HasProjetor.isSelected());
                sala.setObservacao(observacao.getText());

                salaService.update(sala);

                init();

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
    private class AlocarAula extends TableCell<Horario, Boolean> {
        final Button alocarButton       = new Button("Alocar");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        AlocarAula(final Stage stage, final TableView table) {

            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(alocarButton);
            alocarButton.setOnMousePressed(mouseEvent -> buttonY.set(mouseEvent.getScreenY()));
            alocarButton.setOnAction(actionEvent -> {
                showAlocarAulaDialog(stage, table, buttonY.get());
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

    private void showAlocarAulaDialog(Stage parent, final TableView<Horario> table, double y) {
        final Stage dialog = new Stage();

        dialog.setTitle("Alocar Aula");
        dialog.initOwner(parent);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setX(parent.getX() + parent.getWidth());
        dialog.setY(y);
        GridPane grid = new GridPane();
        final  TextField textDisAloc = new TextField();
        final  TextField textTurmaAloc = new TextField();
        final Label horarioLabelStatic = new Label();
        final TextArea observacao = new TextArea();

        observacao.setLayoutY(30);
        observacao.setMinWidth(100);
        observacao.setMinHeight(10);

        horarioLabelStatic.setText(tabelaAulasSala.getSelectionModel().getSelectedItem().getHoraInicio() + " - " + tabelaAulasSala.getSelectionModel().getSelectedItem().getHoraFim());

        HBox horarios = new HBox();
        horarios.getChildren().addAll( new Label("Horário"),horarioLabelStatic);
        horarios.setSpacing(3.0);
        horarios.setAlignment(Pos.CENTER);

        VBox alocar = new VBox();
        alocar.getChildren().addAll(new Label("Alocar"), horarios);
        alocar.setSpacing(3.0);
        alocar.setAlignment(Pos.CENTER);

        VBox disciplina = new VBox(10);
        disciplina.getChildren().addAll(new Label("Disciplina"), textDisAloc);
        disciplina.setPadding(new Insets(5));

        VBox turma = new VBox(10);
        turma.getChildren().addAll(new Label("Turma"), textTurmaAloc);
        turma.setPadding(new Insets(5));



        final Separator sepVert1 = new Separator();
        sepVert1.setOrientation(Orientation.VERTICAL);
        sepVert1.setValignment(VPos.CENTER);
        sepVert1.setPrefHeight(80);
        GridPane.setConstraints(sepVert1, 2, 2);
        GridPane.setRowSpan(sepVert1, 2);


        HBox discAndTurma = new HBox();
        discAndTurma.getChildren().addAll(disciplina,sepVert1, turma);
        discAndTurma.setSpacing(7.0);
        discAndTurma.setAlignment(Pos.CENTER);

        grid.addRow(5, new Label("Observação"), observacao);

        grid.setHgap(10);
        grid.setVgap(10);
        GridPane.setHgrow(observacao, Priority.ALWAYS);
        GridPane.setHgrow(disciplina,Priority.ALWAYS);
        GridPane.setHgrow(turma,Priority.ALWAYS);
        GridPane.setVgrow(alocar, Priority.ALWAYS);

        // create action buttons for the dialog.
        Button cancelarAlocButton = new Button("Cancelar");
        Button salvarAlocButton = new Button("Salvar");
        salvarAlocButton.setDefaultButton(true);
        cancelarAlocButton.setCancelButton(true);
        salvarAlocButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                HorarioService horarioService = new HorarioService();
                AulaService aulaService = new AulaService();
                Aula novaAula = aulaService.create(new Aula(null,textDisAloc.getText(),textTurmaAloc.getText()));
                Horario horario = horarioService.getById(table.getSelectionModel().getSelectedItem().getId());
                horario.setAula(novaAula);
                horarioService.update(horario);

                init();
                table.refresh();
                dialog.close();
            }
        });
        cancelarAlocButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                dialog.close();
            }
        });
        HBox r = new HBox();
        r.getChildren().addAll(cancelarAlocButton,salvarAlocButton);
        r.setSpacing(3.0);
        r.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(alocar,discAndTurma,grid,r);
        layout.setPadding(new Insets(5));
        dialog.setScene(new Scene(layout));
        dialog.show();
    }


    private class DeletarAula extends TableCell<Horario, Boolean> {
        File imgFile = new File("src/main/resources/interfaceSala/image/delete-icon.png");
        ImageView imgV = new ImageView(imgFile.toURI().toString());
        final Button deletarButton    = new Button("",imgV);
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        DeletarAula(final Stage stage, final TableView table) {

            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(deletarButton);
            deletarButton.setOnMousePressed(mouseEvent -> buttonY.set(mouseEvent.getScreenY()));
            deletarButton.setOnAction(actionEvent -> {
                showDeletarAulaDialog(stage, table, buttonY.get());
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

    private void showDeletarAulaDialog(Stage parent, final TableView<Horario> table, double y) {
        final Stage dialog = new Stage();

        dialog.setTitle("Deletar Aula");
        dialog.initOwner(parent);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setX(parent.getX() + parent.getWidth());
        dialog.setY(y);

        final Label disciplina = new Label();
        final Label turma = new Label();
        final Label horarioInicial = new Label();
        final Label horarioFinal = new Label();
        final Label text = new Label();


        disciplina.setText(table.getSelectionModel().getSelectedItem().getAula().getDisciplina());
        turma.setText(table.getSelectionModel().getSelectedItem().getAula().getTurma());
        horarioInicial.setText(table.getSelectionModel().getSelectedItem().getHoraInicio().toString());
        horarioFinal.setText(table.getSelectionModel().getSelectedItem().getHoraFim().toString());

       disciplina.setStyle("-fx-font-weight: bold;");

       text.setText("Você tem certeza que quer deletar a disciplina de " + disciplina.getText()+ " da turma " + turma.getText() + " do horário das " + horarioInicial.getText() + " as " + horarioFinal.getText() + "?" );


        // create action buttons for the dialog.
        Button cancelarAlocButton = new Button("Cancelar");
        Button deletarAulaButton = new Button("Deletar");
        deletarAulaButton.setDefaultButton(true);
        cancelarAlocButton.setCancelButton(true);
        deletarAulaButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        deletarAulaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {

                HorarioService horarioService = new HorarioService();
                AulaService aulaService = new AulaService();

                Horario horario = horarioService.getById(table.getSelectionModel().getSelectedItem().getId());
                Aula aula = horario.getAula();
                horario.setAula(null);
                aulaService.delete(aula);

                horarioService.update(horario);

                init();
                table.refresh();
                dialog.close();
            }
        });
        cancelarAlocButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                dialog.close();
            }
        });
        HBox r = new HBox();
        r.getChildren().addAll(cancelarAlocButton,deletarAulaButton);
        r.setSpacing(3.0);
        r.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(text,r);
        layout.setPadding(new Insets(10));
        dialog.setScene(new Scene(layout));
        dialog.show();
    }
}
