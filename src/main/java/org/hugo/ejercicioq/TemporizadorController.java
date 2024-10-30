package org.hugo.ejercicioq;

import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.application.Platform;

/**
 * Controlador de la interfaz FXML para gestionar un temporizador.
 * Se encarga de actualizar los labels que muestran los minutos y segundos restantes.
 */
public class TemporizadorController extends GridPane {

    @FXML
    private Label lbl_min_derecha;

    @FXML
    private Label lbl_min_izquierda;

    @FXML
    private Label lbl_seg_derecha;

    @FXML
    private Label lbl_seg_izquierda;

    private int tiempo;
    private Timer timer;
    private BooleanProperty fin;

    /**
     * Constructor de la clase TemporizadorController.
     * Inicializa la propiedad fin para indicar si el temporizador ha terminado.
     */
    public TemporizadorController() {
        fin = new SimpleBooleanProperty(false);
    }

    /**
     * Establece el tiempo en segundos para el temporizador.
     * Si el tiempo no es válido (menor a 1 minuto o mayor a 99 minutos), no se fija.
     *
     * @param tiempo Tiempo en segundos que se desea establecer en el temporizador.
     */
    public void fijarTiempo(int tiempo) {
        int mins = tiempo / 60;
        if (mins < 1 || mins > 99) {
            return;
        }
        this.tiempo = tiempo;
    }

    /**
     * Inicia el temporizador y actualiza cada segundo los labels con los minutos
     * y segundos restantes. Cuando el tiempo llega a cero, establece la propiedad
     * fin a true y detiene el temporizador.
     */
    public void iniciarTemporizador() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (tiempo == 0) {
                    Platform.runLater(() -> fin.set(true));
                    detenerTemporizador();
                }
                int mins = tiempo / 60;
                int segs = tiempo % 60;
                Platform.runLater(() -> {
                    lbl_min_izquierda.setText(mins / 10 + "");
                    lbl_min_derecha.setText(mins % 10 + "");
                    lbl_seg_izquierda.setText(segs / 10 + "");
                    lbl_seg_derecha.setText(segs % 10 + "");
                });
                tiempo--;
            }
        }, 0, 1000);
    }

    /**
     * Detiene el temporizador si está en ejecución.
     * Cancela y limpia el Timer.
     */
    public void detenerTemporizador() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    /**
     * Devuelve la propiedad BooleanProperty que indica si el temporizador ha terminado.
     *
     * @return Propiedad BooleanProperty que indica el estado final del temporizador.
     */
    public BooleanProperty getFin() {
        return fin;
    }
}
