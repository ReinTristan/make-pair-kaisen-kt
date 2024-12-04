module com.github.reintristan.makepairkaisenkt {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.github.reintristan.makepairkaisenkt to javafx.fxml;
    exports com.github.reintristan.makepairkaisenkt;
}