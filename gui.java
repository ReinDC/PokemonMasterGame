import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleGamepadGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Gamepad GUI");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create buttons for up, down, left, and right
        Button upButton = new Button("Up");
        Button downButton = new Button("Down");
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");

        // Add buttons to the grid
        gridPane.add(upButton, 1, 0);
        gridPane.add(downButton, 1, 2);
        gridPane.add(leftButton, 0, 1);
        gridPane.add(rightButton, 2, 1);

        // Set event handlers for the buttons (you can replace these with your game logic)
        upButton.setOnAction(e -> System.out.println("Up button pressed"));
        downButton.setOnAction(e -> System.out.println("Down button pressed"));
        leftButton.setOnAction(e -> System.out.println("Left button pressed"));
        rightButton.setOnAction(e -> System.out.println("Right button pressed"));

        Scene scene = new Scene(gridPane, 200, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
