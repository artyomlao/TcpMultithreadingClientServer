module com.lepesha.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lepesha.server to javafx.fxml;
    exports com.lepesha.server;
    exports com.lepesha.server.viewcontroller;
    opens com.lepesha.server.viewcontroller to javafx.fxml;
}