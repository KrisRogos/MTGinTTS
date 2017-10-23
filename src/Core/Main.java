package Core;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
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

    static SimpleStringProperty gFileName;
    static SimpleStringProperty gSaveDir;
    Deck deckThread;

    @Override
    public void start(Stage primaryStage) throws Exception{
        int WindowWidth = (Deck.kWidth/2) + 4;
        int WindowHalfWidth = WindowWidth/2 - 1;

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
        vBox_left.setPrefWidth(WindowHalfWidth);
        VBox vBox_right = new VBox();
        vBox_right.setPrefWidth(WindowHalfWidth);
        hBox_outer.getChildren().addAll(vBox_left, vBox_right);

        // elements for file input
        HBox hBox_DeckLoc = new HBox();
        hBox_DeckLoc.setSpacing(10);
        Label label_DeckLocInfo = new Label("Deck file: ");
        gFileName = new SimpleStringProperty();
        TextField textField_DeckLoc = new TextField((gFileName == null) ? "not set" : gFileName.get());
        textField_DeckLoc.textProperty().bindBidirectional(gFileName);
        Button button_DeckLoc = new Button("Change");
        button_DeckLoc.setOnAction(actionEvent -> OpenDeckFile());
        HBox.setHgrow(textField_DeckLoc, Priority.ALWAYS);
        textField_DeckLoc.setMaxWidth(Double.MAX_VALUE);
        hBox_DeckLoc.getChildren().addAll(label_DeckLocInfo, textField_DeckLoc, button_DeckLoc);

        // elements for file input
        HBox hBox_SaveLoc = new HBox();
        hBox_SaveLoc.setSpacing(10);
        Label label_SaveLocInfo = new Label("Save to: ");
        gSaveDir = new SimpleStringProperty();
        TextField textField_SaveLoc = new TextField((gSaveDir == null) ? "not set" : gSaveDir.get());
        textField_SaveLoc.textProperty().bindBidirectional(gSaveDir);
        Button button_SaveLoc = new Button("Change");
        button_SaveLoc.setOnAction(actionEvent -> OpenSaveDir());
        HBox.setHgrow(textField_SaveLoc, Priority.ALWAYS);
        textField_SaveLoc.setMaxWidth(Double.MAX_VALUE);
        hBox_SaveLoc.getChildren().addAll(label_SaveLocInfo, textField_SaveLoc, button_SaveLoc);

        // start button
        HBox hBox_StartLoading = new HBox();
        hBox_StartLoading.setPrefWidth(WindowHalfWidth);
        Button button_StartLoading = new Button ("Load deck");
        button_StartLoading.setOnAction(actionEvent -> LoadDeck());
        hBox_StartLoading.setAlignment(Pos.BOTTOM_RIGHT);
        hBox_StartLoading.setSpacing(10);
        hBox_StartLoading.getChildren().addAll(button_StartLoading);

        // display loader progress
        VBox vBox_Progress = new VBox();
        Label label_Progress = new Label("Waiting");


        vBox_left.getChildren().addAll(hBox_DeckLoc, hBox_SaveLoc, hBox_StartLoading);


        primaryStage.show();
    }

    private void LoadDeck() {
        deckThread = new Deck(gFileName.get() , gSaveDir.get());
        deckThread.start();
    }


    private void OpenDeckFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("c:\\"));
        chooser.setDialogTitle("Chose deck list");;
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            gFileName.set(chooser.getSelectedFile().toString());
            gSaveDir.set(chooser.getCurrentDirectory().toString());
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
            gSaveDir.set(chooser.getCurrentDirectory().toString());
        }
        else {
            System.out.println("No Selection ");
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
