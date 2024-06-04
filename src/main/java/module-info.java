module org.davelogapps.cineconcertmanagerv2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens org.davelogapps.cineconcertmanagerv2 to javafx.fxml;
    exports org.davelogapps.cineconcertmanagerv2;
}