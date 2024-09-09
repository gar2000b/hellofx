module com.example.hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires watchmaker.framework;
    requires uncommons.maths;


    opens com.example.hellofx to javafx.fxml;
    exports com.example.hellofx;
}