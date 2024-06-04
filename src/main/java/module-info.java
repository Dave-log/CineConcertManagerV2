module org.davelogapps.cineconcertmanagerv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires static lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens org.davelogapps.cineconcertmanagerv2 to javafx.fxml;
    exports org.davelogapps.cineconcertmanagerv2;
    exports org.davelogapps.cineconcertmanagerv2.controller;
    exports org.davelogapps.cineconcertmanagerv2.data;
    exports org.davelogapps.cineconcertmanagerv2.model;
    opens org.davelogapps.cineconcertmanagerv2.controller to javafx.fxml;
}