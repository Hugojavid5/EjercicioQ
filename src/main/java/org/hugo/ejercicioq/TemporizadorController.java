package org.hugo.ejercicioq;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Timer;
import java.util.TimerTask;

public class TemporizadorController extends AnchorPane {
    private BooleanProperty fin;
    private int segundos;
    private Timer timer;

    @FXML
    private Label min1;

    @FXML
    private Label min2;

    @FXML
    private Label seg1;

    @FXML
    private Label seg2;

    public TemporizadorController() {
        this.fin = new SimpleBooleanProperty(false);
        this.segundos = -1;
    }
    public boolean setSegundos(int segundos) {
        if (segundos >= 60) {
            int minutos = (int) (segundos / 60);
            if (minutos < 100) {
                this.segundos = segundos;
                return true;
            }
        } else {
            if (segundos > 0) {
                this.segundos = segundos;
                return true;
            }
        }
        return false;
    }

    public void iniciarTemporizaddor() {
        if (this.segundos == -1) {
            System.err.println("Error");
        } else {
            final int[] resto = {this.segundos};
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (resto[0] < 0) {
                        timer.cancel();
                        Platform.runLater(() -> fin.set(true)); // Actualizar la propiedad fin para indicar que el temporizador ha terminado
                        return;
                    }
                    int mins = resto[0] / 60;
                    int mins1 = mins / 10;
                    int mins2 = mins % 10;
                    int segs = resto[0] % 60;
                    int segs1 = segs / 10;
                    int segs2 = segs % 10;

                    Platform.runLater(() -> {
                        min1.setText(mins1 + "");
                        min2.setText(mins2 + "");
                        seg1.setText(segs1 + "");
                        seg2.setText(segs2 + "");
                    });
                    resto[0] -= 1;
                }
            }, 0, 1000);
        }
    }


    public BooleanProperty finProperty() {
        return fin;
    }

}