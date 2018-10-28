package humza.calculator;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorButtonPanel extends VBox implements EventHandler<ActionEvent> {

	TextField calcView; //The TextField that will display the input(s)/answer
	GridPane buttons; //The buttons of the GUI
	float num1 = 0; //Placeholder for the first number being operated on
	boolean isNum1 = false; //A flag that signifies if the holder is empty or not
	float num2 = 0; //Placeholder for the second number being operated on
	boolean isNum2 = false; //A flag that signifies if the holder is empty or not
	String operator = ""; //Placeholder for the operator
	boolean isOperator = false; //A flag that signifies if the operator is empty or not
	boolean isLastEqual = false; //A flag that signifies if the last operation was equal
	
	/**
	 * Creates the buttons of the calculator and the display
	 */
	public CalculatorButtonPanel() {
		Button b;
		calcView = new TextField();
		// Creates new TextField
		calcView.setPrefColumnCount(10);
		calcView.setEditable(false);
		this.getChildren().add(calcView);
		
		// Creates the button panel
		buttons = new GridPane();
		buttons.setPrefWidth(35);
		
		buttons.setHgap(2);
		buttons.setVgap(2);
		buttons.setPadding(new Insets(10));
		
		//Creates the buttons on the panel
		int i = 1;
		while (i < 10) {
			int x = (i - 1)/3;
			b = new Button(""+i);
			b.setMinWidth(buttons.getPrefWidth());
			buttons.add(b, 0, x);
			b.setOnAction(this);
			i++;
			b = new Button(""+i);
			b.setMinWidth(buttons.getPrefWidth());
			buttons.add(b, 1, x);
			b.setOnAction(this);
			i++;
			b = new Button(""+i);
			b.setMinWidth(buttons.getPrefWidth());
			buttons.add(b, 2, x);
			b.setOnAction(this);
			i++;
		}
		
		b = new Button("/");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 3, 0);
		b.setOnAction(this);
		
		b = new Button("x");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 3, 1);
		b.setOnAction(this);
		
		b = new Button("-");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 3, 2);
		b.setOnAction(this);
		
		b = new Button("0");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 0, 3);
		b.setOnAction(this);
		
		b = new Button(".");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 1, 3);
		b.setOnAction(this);
		
		b = new Button("=");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 2, 3);
		b.setOnAction(this);
		
		b = new Button("+");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 3, 3);
		b.setOnAction(this);
		
		b = new Button("C");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 4, 0);
		b.setOnAction(this);
		
		b = new Button("←");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 4, 1);
		b.setOnAction(this);
		
		b = new Button("x²");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 4, 2);
		b.setOnAction(this);
		
		b = new Button("√");
		b.setMinWidth(buttons.getPrefWidth());
		buttons.add(b, 4, 3);
		b.setOnAction(this);
		
		this.getChildren().add(buttons);
		
	}

	/**
	 * The handler that handles all the button clicks, updating the TextField and sets the calculator logic
	 */
	public void handle(ActionEvent event) {
		String input = ((Button) (event.getSource())).getText(); //Current button pressed
		String text = calcView.getText(); //What was inputed before the current button was pressed
		// Checks to see if the inputed button was any operation, otherwise updates TextField
		if (input == "=" || input == "+" || input == "-" || input == "x" || input == "/") {
			// Checks the flag of the first number, to see if it already has a value or not, 
			// otherwise changes the second number to match the text inputed before
			if (!isNum1) {
				num1 = Float.parseFloat(text);
				isNum1 = true;
				// If the operator was and "=" it just sets the value of itself without need of calculation
				// otherwise clears the TextField
				if (input == "=") {
					calcView.setText(text);
					isNum1 = false;
					isLastEqual = true;
					return;
				}
				// clears the TextField
				else {
					calcView.setText("");
				}
			}
			// sets the second number as the text inputed before the operation
			else {
				num2 = Float.parseFloat(text);
				isNum2 = true;
				// Checks to see if the operator was "=", calls calculate with num1, num2 and the operator 
				// inputed before this, otherwise empties TextField and sets num1 as num1 (operator) num2
				// e.g. if operator == "+" then num1 = num1 + num2
				if (input == "=") {
					calcView.setText(calculate(num1, num2, operator));
					isNum1 = false;
					isOperator = false;
					isNum2 = false;
					isLastEqual =true;
					return;
				}
				// Empties TextField and sets num1 as num1 (operator) num2
				else {
					num1 = Float.parseFloat(calculate(num1, num2, operator));
					calcView.setText("");
					operator = input;
					isNum2 = false;
				}
			}
			// Checks to see if the operator has a value already or not
			if (!isOperator) {
				operator = input;
				isOperator = true;
			}
		}
		// If the last operator was an "=" then empties the TextField and adds the new input
		if (isLastEqual) {
			calcView.setText(input);
			isLastEqual = false;
		}
		// If the C button was pressed, clears input
		else if (input =="C") {
			calcView.setText("");
			isNum1 = false;
			isNum2 = false;
			isOperator = false;
			isLastEqual = false;
		}
		// If the input is an operator, then it does not add it to the TextView
		else if (input == "=" || input == "+" || input == "-" || input == "x" || input == "/") {
			return;
		}
		// Adds the numbers to the TextField
		else {
			calcView.appendText(input);
		} 
		return;
	}
	
	public String add(String input, String text) {
		return input;
	}
	
	public String subtract(String input, String text) {
		return input;
	}
	
	public String multiply(String input, String text) {
		return input;
	}
	
	public String divide(String input, String text) {
		return input;
	}
	
	public String squareRoot(String input, String text) {
		return input;
	}
	
	public String square(String input, String text) {
		return input;
	} 

	/**
	 * The method that does the calculations based on what the handler calls upon
	 * @param num1 The first number being inputed into the calculator
	 * @param num2 The second number being inputed into the calculator
	 * @param input The operator that the two numbers are being calculated with
	 * @return returns a string with the result of the calculation 
	 */
	public String calculate(float num1, float num2, String input) {
		
		// If input was +, then add
		if (input == "+") {
			return Float.toString(num1 + num2);
		}
		
		// If input was -, then subtract
		else if (input == "-") {
			return Float.toString(num1 - num2);
		}
		
		// If input was x, then multiply
		else if (input == "x") {
			return Float.toString(num1 * num2);
		}
		
		// If input was /, then divide
		else {
			return Float.toString(num1 / num2);
		}
		
	}

}


