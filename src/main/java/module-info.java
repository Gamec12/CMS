module com.example.cms {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.cms to javafx.fxml;
    exports com.example.cms;
    exports com.example.cms.AdminSide;
    opens com.example.cms.AdminSide to javafx.fxml;
    exports com.example.cms.CustomerSide;
    opens com.example.cms.CustomerSide to javafx.fxml;
}