module com.lepesha.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lepesha.client to javafx.fxml;
    exports com.lepesha.client;
    exports com.lepesha.client.viewcontroller;
    opens com.lepesha.client.viewcontroller to javafx.fxml;
}