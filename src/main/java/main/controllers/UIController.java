package main.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UIController implements Initializable {

    @FXML
    private TextField A;
    @FXML
    private TextField B;
    @FXML
    private TextField C;
    @FXML
    private TextField D;
    @FXML
    private TextField E;
    @FXML
    private TextField F;
    @FXML
    private TextField G;
    @FXML
    private TextField H;
    @FXML
    private TextField I;
    @FXML
    private TextField J;
    @FXML
    private TextField K;
    @FXML
    private TextField L;
    @FXML
    private TextField M;
    @FXML
    private TextField N;
    @FXML
    private TextField O;
    @FXML
    private TextField P;
    @FXML
    private TextField Q;
    @FXML
    private TextField R;
    @FXML
    private TextField S;
    @FXML
    private TextField T;
    @FXML
    private TextField U;
    @FXML
    private TextField V;
    @FXML
    private TextField W;
    @FXML
    private TextField X;
    @FXML
    private TextField Y;
    @FXML
    private TextField Z;
    @FXML
    private TextField enter;
    @FXML
    private TextField back;
    @FXML
    private TextField A1L1;
    @FXML
    private TextField A1L2;
    @FXML
    private TextField A1L3;
    @FXML
    private TextField A1L4;
    @FXML
    private TextField A1L5;
    @FXML
    private TextField A2L1;
    @FXML
    private TextField A2L2;
    @FXML
    private TextField A2L3;
    @FXML
    private TextField A2L4;
    @FXML
    private TextField A2L5;
    @FXML
    private TextField A3L1;
    @FXML
    private TextField A3L2;
    @FXML
    private TextField A3L3;
    @FXML
    private TextField A3L4;
    @FXML
    private TextField A3L5;
    @FXML
    private TextField A4L1;
    @FXML
    private TextField A4L2;
    @FXML
    private TextField A4L3;
    @FXML
    private TextField A4L4;
    @FXML
    private TextField A4L5;
    @FXML
    private TextField A5L1;
    @FXML
    private TextField A5L2;
    @FXML
    private TextField A5L3;
    @FXML
    private TextField A5L4;
    @FXML
    private TextField A5L5;
    @FXML
    private TextField A6L1;
    @FXML
    private TextField A6L2;
    @FXML
    private TextField A6L3;
    @FXML
    private TextField A6L4;
    @FXML
    private TextField A6L5;
    @FXML
    private AnchorPane gameOver;
    @FXML
    private Label won;
    @FXML
    private Label streak;
    @FXML
    private Label word;
    @FXML
    private ImageView play;

    private TextField[][] attempts;
    private TextField[] selectedAttempt;
    private int streakCount = 0;
    private int letterCount = 0;
    private int attemptCount = 0;
    private String wordToGuess;
    private File words = new File(System.getProperty("user.dir") +
            "\\src\\main\\resources\\main\\data\\words.txt");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateWord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TextField[] keyboard = new TextField[]{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
        TextField[] attempt1 = new TextField[]{A1L1, A1L2, A1L3, A1L4, A1L5};
        TextField[] attempt2 = new TextField[]{A2L1, A2L2, A2L3, A2L4, A2L5};
        TextField[] attempt3 = new TextField[]{A3L1, A3L2, A3L3, A3L4, A3L5};
        TextField[] attempt4 = new TextField[]{A4L1, A4L2, A4L3, A4L4, A4L5};
        TextField[] attempt5 = new TextField[]{A5L1, A5L2, A5L3, A5L4, A5L5};
        TextField[] attempt6 = new TextField[]{A6L1, A6L2, A6L3, A6L4, A6L5};
        attempts = new TextField[][]{attempt1, attempt2, attempt3, attempt4, attempt5, attempt6};

        selectedAttempt = attempt1;

        for (TextField field : keyboard) {
            field.setCursor(Cursor.HAND);
            field.setOnMouseClicked(e -> onKeyboardClick(field));
            field.setStyle("-fx-text-fill: white; -fx-background-color: #888484;" +
                    "-fx-highlight-fill: null; -fx-highlight-text-fill: null;");
        }

        setAttempts();

        enter.setCursor(Cursor.HAND);
        enter.setStyle("-fx-text-fill: white; -fx-background-color:  #888484;" +
                "-fx-highlight-fill: null; -fx-highlight-text-fill: null;");
        enter.setOnMouseClicked(e -> {
            if (letterCount == 5) {
                StringBuilder word = new StringBuilder();
                for (TextField field : selectedAttempt) {
                    word.append(field.getText());
                }

                try {
                    if (dictionaryContainsWord(word.toString().toLowerCase())) {
                        pop();
                        char[] guess = word.toString().toLowerCase().toCharArray();
                        char[] actualWord = wordToGuess.toCharArray();

                        String green = "-fx-background-color: #588c4c; -fx-border-color: #588c4c; -fx-text-fill: white;" +
                                "-fx-highlight-fill: null; -fx-highlight-text-fill: null;";
                        String yellow = "-fx-background-color: #b89c3c; -fx-border-color: #b89c3c; -fx-text-fill: white;" +
                                "-fx-highlight-fill: null; -fx-highlight-text-fill: null;";
                        String grey = "-fx-background-color: #403c3c; -fx-border-color: #403c3c; -fx-text-fill: white;" +
                                "-fx-highlight-fill: null; -fx-highlight-text-fill: null;";

                        for (int i = 0; i < 5; i++) {
                            if (guess[i] == actualWord[i]) {
                                selectedAttempt[i].setStyle(green);
                            } else if (guess[i] != actualWord[i] && wordToGuess.contains(String.valueOf(guess[i]))) {
                                selectedAttempt[i].setStyle(yellow);
                            } else {
                                selectedAttempt[i].setStyle(grey);
                            }
                        }

                        if (word.toString().equalsIgnoreCase(wordToGuess)) {
                            streakCount++;
                            streak.setText("Streak: " + streakCount);
                            rotate();
                        } else {
                            if (attemptCount < 5) {
                                attemptCount++;
                                selectedAttempt = attempts[attemptCount];
                                letterCount = 0;
                            } else {
                                streakCount = 0;
                                streak.setText("Streak: " + streakCount);
                                gameOver(false);
                            }
                        }
                    } else {
                        shake();
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        back.setCursor(Cursor.HAND);
        back.setStyle("-fx-text-fill: white; -fx-background-color:  #888484; " +
                "-fx-highlight-fill: null; -fx-highlight-text-fill: null;");
        back.setOnMouseClicked(e -> {

            if (letterCount > 0) {
                selectedAttempt[letterCount - 1].setText("");
                letterCount--;
            }
        });
    }

    private void gameOver(boolean wonB) {
        enter.setDisable(true);
        back.setDisable(true);

        gameOver.toFront();
        if (wonB) {
            won.setText("You Won!");
            word.setStyle("-fx-text-fill: green;");
        } else {
            won.setText("You Lost!");
            word.setStyle("-fx-text-fill: red;");
        }

        word.setText(wordToGuess);
        gameOver.toFront();

        play.setOnMouseClicked(e -> {
            try {
                generateWord();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            setAttempts();
            reset();
            gameOver.toBack();
        });
    }

    private void setAttempts() {
        for (TextField[] att : attempts) {
            for (TextField atte : att) {
                atte.setCursor(Cursor.DEFAULT);
                atte.setText("");
                atte.setStyle("-fx-background-color: #181414; -fx-border-color: #403c3c; -fx-text-fill: white;" +
                        "-fx-highlight-fill: null; -fx-highlight-text-fill: null;");
            }
        }
    }

    private void reset() {
        selectedAttempt = attempts[0];
        attemptCount = 0;
        letterCount = 0;
        enter.setDisable(false);
        back.setDisable(false);
    }

    private boolean dictionaryContainsWord(String word) throws FileNotFoundException {
        Scanner scanner = new Scanner(words);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    private void onKeyboardClick(TextField field) {
        if (letterCount < 5) {
            selectedAttempt[letterCount].setText(String.valueOf(field.getCharacters().charAt(0)));
            letterCount++;
        }
    }

    private void generateWord() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(words));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();

        wordToGuess = Files.readAllLines(Paths.get(words.toURI())).get(new Random().nextInt(lines));
    }

    private void shake() {
        int duration = 500; // Total duration for the animation in milliseconds
        int stepDuration = duration / 3; // Duration for each movement step

        for (TextField field : selectedAttempt) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(stepDuration),
                            new KeyValue(field.layoutXProperty(), field.getLayoutX() + 5)),
                    new KeyFrame(Duration.millis(2 * stepDuration),
                            new KeyValue(field.layoutXProperty(), field.getLayoutX() - 5)),
                    new KeyFrame(Duration.millis(3 * stepDuration),
                            new KeyValue(field.layoutXProperty(), field.getLayoutX())));

            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void pop() {
        int duration = 500; // Total duration for the animation in milliseconds
        int stepDuration = duration / 4; // Duration for each step

        for (TextField field : selectedAttempt) {
            double originalWidth = field.getWidth();
            double originalHeight = field.getHeight();

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(stepDuration),
                    new KeyValue(field.layoutXProperty(), field.getLayoutX() - 1),
                    new KeyValue(field.layoutYProperty(), field.getLayoutY() - 1),
                    new KeyValue(field.prefWidthProperty(), originalWidth + 2),
                    new KeyValue(field.prefHeightProperty(), originalHeight + 2)),
                    new KeyFrame(Duration.millis(2 * stepDuration),
                            new KeyValue(field.layoutXProperty(), field.getLayoutX()),
                            new KeyValue(field.layoutYProperty(), field.getLayoutY()),
                            new KeyValue(field.prefWidthProperty(), originalWidth),
                            new KeyValue(field.prefHeightProperty(), originalHeight)));

            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void rotate() {
        enter.setDisable(true);
        back.setDisable(true);
        double flipDuration = 1000; // Duration for the flip animation in milliseconds
        double flipAngle = 360; // Angle to rotate the TextField for the flip animation

        for (TextField field : selectedAttempt) {
            // Rotate the TextField around the Y-axis
            Rotate rotate = new Rotate(flipAngle, Rotate.Y_AXIS);
            field.getTransforms().add(rotate);

            Timeline timeline = new Timeline();

            // Step 1: Rotate the TextField back to its normal position
            KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO,
                    new KeyValue(rotate.angleProperty(), flipAngle));
            KeyFrame keyFrame2 = new KeyFrame(Duration.millis(flipDuration),
                    new KeyValue(rotate.angleProperty(), 0));
            timeline.getKeyFrames().addAll(keyFrame1, keyFrame2);

            timeline.setCycleCount(1);
            timeline.setAutoReverse(false);
            timeline.play();

            timeline.setOnFinished(e -> gameOver(true));
        }
    }

}