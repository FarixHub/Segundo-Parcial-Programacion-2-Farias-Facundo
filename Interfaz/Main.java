package interfaz;

import gui.MenuPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrar(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
