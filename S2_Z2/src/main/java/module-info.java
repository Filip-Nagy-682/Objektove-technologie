module com.example.s2_z2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.s2_z2 to javafx.fxml;
    exports com.example.s2_z2;
}