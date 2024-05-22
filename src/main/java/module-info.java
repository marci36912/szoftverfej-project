module homework.template.project
{
    requires javafx.graphics;
    requires javafx.controls;
    exports Chess.UI;
    exports Chess.GameManager;
    exports Chess.Solver;
    requires org.tinylog.api;
    requires homework.project.utils;
}