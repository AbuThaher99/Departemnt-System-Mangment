module com.example.datastructureprojectthree {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.datastructureprojectthree to javafx.fxml;
    exports com.example.datastructureprojectthree;
}