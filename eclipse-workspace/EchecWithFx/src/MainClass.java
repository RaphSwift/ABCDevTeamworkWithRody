

import utils.Jeu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import eventhandler.MyHandler;
public class MainClass extends Application {


    private static interface PatternSource {
        ImagePattern getImagePattern();
    }

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

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.SKYBLUE);
        Text text = new Text(10, 30, "HELLO");
        text.setFont(new Font(80));
        text.setY(100);
        text.setFill(Color.RED);


        /*Image img = new Image(getClass().getResourceAsStream("/img/sprite.png"));

        ImagePattern imgpat = new ImagePattern(img, 0, 0, img.getWidth(), img.getHeight(), false);

        PatternSource mE = () -> imgpat;

        Rectangle rect = new Rectangle(64, 0, 64, 64);

        rect.setFill(mE.getImagePattern());*/
        Jeu jeu = new Jeu("J1","J2");

        //stage.addEventFilter(KeyEvent.KEY_PRESSED, sp);


        jeu.dessiner(root);
        stage.setScene(scene);
        stage.addEventFilter(MouseEvent.MOUSE_CLICKED,jeu);
        stage.setOnCloseRequest(new MyHandler(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void gererScene(Stage stage) {

        Scene scene = stage.getScene();
        VBox vb = (VBox) scene.lookup("#main_vb");

        vb.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

    }


}