package modules.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class salasControllerTest extends ApplicationTest {
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
}