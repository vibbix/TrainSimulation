package edu.wit.comp2000.group23.application3.UI;
/**
 * Created by dechristophera on 11/2/16.
 */

import edu.wit.comp2000.group23.application3.TrainSimulation;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class UI extends Application {

    @FXML
    private TextArea log;

    private void appendText(String valueOf) {
        Platform.runLater(() -> log.appendText(valueOf));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(root != null) {
            Scene scene = new Scene(root, 500, 300);

            primaryStage.setTitle("Train Simulation");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        String[] stops = new String[]{ "Kenmore", "Copley", "Arlington", "Boylston" };

        OutputStream out = new OutputStream(){
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char)b));
            }
        };
        PrintStream ps = new PrintStream(out);
        Logger l = new Logger(ps);
        TrainSimulation ts = new TrainSimulation(l, stops, true, 0);
        ts.startSimulation();
    }
}
