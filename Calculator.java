package humza.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calculator extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starts up the GUI with title: Humza's Calculator
	 */
	@Override
	public void start(Stage stage) {

		CalculatorButtonPanel pane = new CalculatorButtonPanel();

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Humza's Calculator");
		stage.show();
	}
}

