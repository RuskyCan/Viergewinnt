module com.example.viergewinnt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.viergewinnt to javafx.fxml;
    exports com.example.viergewinnt;
}