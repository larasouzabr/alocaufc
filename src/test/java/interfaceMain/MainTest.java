package interfaceMain;

import com.alocaufc.Main;
import com.alocaufc.controllers.salasController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.WindowMatchers.isShowing;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/interfaceMain/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 844, 504);
        primaryStage.setTitle("Aloca UFC");
        primaryStage.setScene(scene);
        primaryStage.show();
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
    public void testStartInterface() {
        verifyThat(window("Aloca UFC"), isShowing());
    }

    @Test
    public void testClickAddSalaButton() {
        clickOn("#addSalaButton");

        verifyThat(window("Registrar Sala"), isShowing());
    }
}