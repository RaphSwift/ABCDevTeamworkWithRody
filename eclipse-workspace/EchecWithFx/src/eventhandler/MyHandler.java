package eventhandler;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MyHandler implements EventHandler<WindowEvent> {
	
	private Stage stage;
	public MyHandler(Stage _stage) {
		stage = _stage;
	}
    @Override
    public void handle(WindowEvent t) {
    	stage.close();
        Platform.exit();
        System.exit(0);
    }
}
