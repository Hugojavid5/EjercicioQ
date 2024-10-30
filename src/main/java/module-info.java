module org.hugo.ejercicioq {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.hugo.ejercicioq to javafx.fxml;
    exports org.hugo.ejercicioq;
}