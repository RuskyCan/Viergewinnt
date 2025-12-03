import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model("Spieler 1 (Rot)", "Spieler 2 (Gelb)", "RED", "YELLOW");

        View view = new View(model, primaryStage);

        Controller controller = new Controller(model, view);

        view.update();
    }

    public static void main(String[] args) {
        launch(args);
    }
}