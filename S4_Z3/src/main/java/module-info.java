module com.example.s4_z3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.s4_z3 to javafx.fxml;
    exports com.example.s4_z3;
}