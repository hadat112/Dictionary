import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

public class DicApp extends Application {
    private static final String FILE_FXML = "resources/javaFx.fxml";
    private static Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Paths.get(FILE_FXML).toUri().toURL();
        System.out.println(url);
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();

        controller.init(root);
        controller.initData();
        controller.function();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
