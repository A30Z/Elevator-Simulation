package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        primaryStage.setTitle("elevator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        System.out.println("Welcome to MyLift");
        Thread requestListenerThread = new Thread(new RequestListener(), "RequestListenerThread");

        // RequestProcessorThread to read Set and process requested floor
        Thread requestProcessorThread1 = new Thread(new RequestProcessor1(), "RequestProcessorThread1");
        Thread requestProcessorThread2 = new Thread(new RequestProcessor2(), "RequestProcessorThread2");
        Thread requestProcessorThread3 = new Thread(new RequestProcessor3(), "RequestProcessorThread3");
        Controller controller = new Controller();
        Elevator elevator1 = Elevator.getInstance("elevator1",controller);
        Elevator elevator2 = Elevator.getInstance("elevator2",controller);
        Elevator elevator3 = Elevator.getInstance("elevator3",controller);
        elevator1.setRequestProcessorThread(requestProcessorThread1);
        elevator2.setRequestProcessorThread(requestProcessorThread2);
        elevator3.setRequestProcessorThread(requestProcessorThread3);
        requestListenerThread.start();
        requestProcessorThread1.start();
        requestProcessorThread2.start();
        requestProcessorThread3.start();
        System.out.println("Welcome to MyLift");
        launch(args);

    }

}

















enum Direction {
    UP, DOWN
}


