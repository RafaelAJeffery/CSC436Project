module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Library.MVC to javafx.fxml;
    exports Library.MVC;
}