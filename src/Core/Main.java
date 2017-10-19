package Core;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

public class Main extends Application {

    static String gFileName;
    static String gSaveDir;
    DeckLoaderThread deckLoaderThread;

    @Override
    public void start(Stage primaryStage) throws Exception{
        int WindowWidth = Deck.kWidth/2 + 4;
        int WindowHalfWidth = WindowWidth/2;

        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, WindowWidth, Deck.kHeight/4+100));
        primaryStage.setTitle("MTG Deck creator for Tabletop Simulator");


        // create the top menu
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // create the file menu
        Menu fileMenu = new Menu ("File");
        MenuItem newMenuItem = new MenuItem("Open");
        MenuItem closeMenuItem = new MenuItem("Exit");
        closeMenuItem.setOnAction(actionEvent -> Platform.exit());
        newMenuItem.setOnAction(actionEvent -> OpenDeckFile());
        fileMenu.getItems().addAll(newMenuItem, closeMenuItem);

        // add all menus to the menu bar
        menuBar.getMenus().addAll(fileMenu);

        // the outer pane
        HBox hBox_outer = new HBox();
        root.setCenter(hBox_outer);

        // the left and right hand panes
        VBox vBox_left = new VBox();
        vBox_left.setMaxWidth(WindowHalfWidth);
        VBox vBox_right = new VBox();
        vBox_right.setMaxWidth(WindowHalfWidth);
        hBox_outer.getChildren().addAll(vBox_left, vBox_right);

        // elements for file input
        HBox hBox_DeckLoc = new HBox();
        Label label_DeckLocInfo = new Label("Deck file: ");
        TextField textField_DeckLoc = new TextField((gFileName == null) ? "not set" : gFileName);
        Button button_DeckLoc = new Button("Change");
        button_DeckLoc.setOnAction(actionEvent -> OpenDeckFile());
        HBox.setHgrow(textField_DeckLoc, Priority.ALWAYS);
        textField_DeckLoc.setMaxWidth(Double.MAX_VALUE);
        hBox_DeckLoc.getChildren().addAll(label_DeckLocInfo, textField_DeckLoc, button_DeckLoc);

        // elements for file input
        HBox hBox_SaveLoc = new HBox();
        Label label_SaveLocInfo = new Label("Save to: ");
        TextField textField_SaveLoc = new TextField((gSaveDir == null) ? "not set" : gSaveDir);
        label_SaveLocInfo.textProperty().bindBidirectional(new SimpleStringProperty(gSaveDir));
        Button button_SaveLoc = new Button("Change");
        button_SaveLoc.setOnAction(actionEvent -> OpenSaveDir());
        HBox.setHgrow(textField_SaveLoc, Priority.ALWAYS);
        textField_SaveLoc.setMaxWidth(Double.MAX_VALUE);
        hBox_SaveLoc.getChildren().addAll(label_SaveLocInfo, textField_SaveLoc, button_SaveLoc);

        vBox_left.getChildren().addAll(hBox_DeckLoc, hBox_SaveLoc);












        primaryStage.show();
    }

    private void OpenDeckFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("c:\\"));
        chooser.setDialogTitle("Chose deck list");;
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            gFileName = chooser.getSelectedFile().toString();
            gSaveDir = chooser.getCurrentDirectory().toString();

            deckLoaderThread = new DeckLoaderThread(gFileName, gSaveDir);
            deckLoaderThread.start();
        }
        else {
            System.out.println("No Selection ");
        }
    }

    private void OpenSaveDir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("c:\\"));
        chooser.setDialogTitle("Chose where to save complete image");;
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            gSaveDir = chooser.getCurrentDirectory().toString();
        }
        else {
            System.out.println("No Selection ");
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
