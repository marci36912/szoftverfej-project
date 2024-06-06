module homework.template.project
{
    requires javafx.graphics;
    requires javafx.controls;
    exports Chess.UI;
    exports Chess.GameManager;
    exports Chess.Solver;
    exports Chess.Saves;
    requires org.tinylog.api;
    requires homework.project.utils;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
}