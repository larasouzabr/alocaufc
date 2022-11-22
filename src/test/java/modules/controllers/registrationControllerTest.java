package modules.controllers;

import interfaceMain.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import modules.entities.Sala;
import modules.entities.enums.NumeroBloco;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import javafx.scene.text.Text;
import org.testfx.matcher.base.NodeMatchers;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;
import static org.testfx.util.NodeQueryUtils.isVisible;

public class registrationControllerTest extends ApplicationTest {
    @FXML private  ComboBox selectBloco;

    @Override
    public void start(Stage stage) throws Exception {
        String caminhoPagina = "/interfaceRegistration/Registration.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(salasController.class.getResource(caminhoPagina));
        Scene scene = new Scene(fxmlLoader.load(), 388, 458);
        Stage newStage = new Stage();
        newStage.setTitle("Registrar Sala");
        newStage.setScene(scene);
        newStage.show();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testRegistrationSalaWithNullBloco() {
        int numSala = 1;
        int qtdCadeiras = 45;

        clickOn("#numeroSalaField");
        write(Integer.toString(numSala));

        clickOn("#qtdCadeirasField");
        write(Integer.toString(qtdCadeiras));

        clickOn("#projetorCheckbox");

        clickOn("#arCondCheckbox");

        clickOn("#addNewSalaButton");

        verifyThat("OK", NodeMatchers.isVisible());
    }
}