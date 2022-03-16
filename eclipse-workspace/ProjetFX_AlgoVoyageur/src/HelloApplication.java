import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Utils;
import utils.Ville;

public class HelloApplication extends Application {
	@Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");

        stage.setScene(scene);
        gererScene(stage);
        stage.show();*/
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setTitle("Tests");
        stage.setResizable(false);
        
        ComboBox<Ville> comboBox = new ComboBox<Ville>();
        comboBox.setItems(FXCollections.observableArrayList(Utils.getElements()));
        Group root = new Group();
        Scene scene = new Scene(root);
        comboBox.setPromptText(comboBox.getItems().get(0).getNom());
        comboBox.setVisibleRowCount(3);
        root.getChildren().add(comboBox);
        
        scene.setFill(Color.SKYBLUE);
        Text text = new Text(10, 30, "HELLO");
        text.setFont(new Font(80));
        text.setY(100);
        text.setFill(Color.RED);


        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
