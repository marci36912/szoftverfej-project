module homework.template.project
{
    exports Chess.UI;
    exports Chess.GameManager;
    exports Chess.Solver;
    exports Chess.Saves;
    exports Chess.GameManager.Positions;
    requires javafx.graphics;
    requires javafx.controls;
    requires org.tinylog.api;
    requires homework.project.utils;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
}