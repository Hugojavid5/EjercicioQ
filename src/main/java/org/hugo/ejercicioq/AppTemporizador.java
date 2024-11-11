package org.hugo.ejercicioq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX que carga la interfaz definida en el archivo FXML
 * y muestra una ventana con un temporizador.
 */
public class AppTemporizador extends Application {

    /**
     * Método start que se ejecuta al iniciar la aplicación.
     * Configura y muestra la ventana principal.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppTemporizador.class.getResource("EjercicioQ.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Temporizador");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal que inicia la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
