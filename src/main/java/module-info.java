//module com.example.quan_ly_tuyen_bay {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.web;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
////    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;
//
//    opens com.example.quan_ly_tuyen_bay to javafx.fxml;
//    exports com.example.quan_ly_tuyen_bay;
//}

module com.example.quan_ly_tuyen_bay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.quan_ly_tuyen_bay to javafx.fxml;
    opens com.example.quan_ly_tuyen_bay.Controller to javafx.fxml;
    opens com.example.quan_ly_tuyen_bay.Model to javafx.base;

    exports com.example.quan_ly_tuyen_bay;
    exports com.example.quan_ly_tuyen_bay.Controller;
}