module wguclass.software1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires jdk.compiler;


    opens wguclass.software1 to javafx.fxml;
    exports wguclass.software1;
}