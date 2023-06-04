package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Animations {

    public static void shake(TextField[] selectedAttempt) {
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

    public static void pop(TextField[] selectedAttempt) {
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

    public static Timeline rotate(TextField[] selectedAttempt, TextField enter, TextField back) {
        enter.setDisable(true);
        back.setDisable(true);
        double flipDuration = 1000; // Duration for the flip animation in milliseconds
        double flipAngle = 360; // Angle to rotate the TextField for the flip animation

        Timeline timeline = null;

        for (TextField field : selectedAttempt) {
            timeline = new Timeline();

            // Rotate the TextField around the Y-axis
            Rotate rotate = new Rotate(flipAngle, Rotate.Y_AXIS);
            field.getTransforms().add(rotate);

            // Step 1: Rotate the TextField back to its normal position
            KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO,
                    new KeyValue(rotate.angleProperty(), flipAngle));
            KeyFrame keyFrame2 = new KeyFrame(Duration.millis(flipDuration),
                    new KeyValue(rotate.angleProperty(), 0));
            timeline.getKeyFrames().addAll(keyFrame1, keyFrame2);

            timeline.setCycleCount(1);
            timeline.setAutoReverse(false);
            timeline.play();
        }

        return timeline;
    }

}
