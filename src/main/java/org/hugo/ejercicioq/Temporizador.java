package org.hugo.ejercicioq;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;

public class Temporizador {
    private BooleanProperty fin;
    private int segundos;

        @FXML
        private Label min1;

        @FXML
        private Label seg2;

    public Temporizador(int segundos) {
        this.fin = new SimpleBooleanProperty(false);
        this.segundos = -1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/hugo/ejercicio15q/fxml/Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setSegundos(int segundos) {
        int minutos = (int)(segundos/60);
        if (minutos>0 && minutos<100) {
            this.segundos = segundos;
            return true;
        }
        return false;
    }

    public void iniciarTemporizador() {
        if (this.segundos == -1) {
            System.err.println("Error en los segundos");
        } else
            {
                final int[] resto = {this.segundos};
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        if (resto[0] < 0)
                        {
                            this.cancel();
                        }
                        int mins = resto[0] /60;
                        int mins1 = mins/10;
                        min1.setText(mins1 + "");
                        int mins2 = mins%10;
                        min2.setText(mins2 + "");
                        int segs = resto[0] %60;
                        int segs1 = segs/10;
                        seg1.setText(segs1 + "");
                        int segs2 = segs%10;
                        seg2.setText(segs2 + "");
                        resto[0] -= 1;
                    }
                }, 0, 1000);
                timer.purge();
                fin.set(true);
            }
         }
}
