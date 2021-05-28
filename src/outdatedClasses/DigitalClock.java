package outdatedClasses;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DigitalClock extends Parent {
    private final int boxHeight = 10;
    private final int boxWidth = 6;
    private final int scale = 5;
    private Font FONT = new Font(50.0D);
    private HBox hbox = new HBox();
    private Text[] digits = new Text[2];
    private Group[] digitsGroup = new Group[2];

    public DigitalClock() {
        this.configureDigits();
        this.configureHbox();
        this.getChildren().add(this.hbox);
    }

    private void configureHbox() {
        this.hbox.getChildren().addAll(this.digitsGroup);
        this.hbox.setSpacing(2.0D);
    }

    private void configureDigits() {
        for(int i = 0; i < this.digits.length; ++i) {
            this.digits[i] = new Text("0");
            this.digits[i].setFont(this.FONT);
            this.digits[i].setTextOrigin(VPos.TOP);
            this.digits[i].setLayoutY(-10.0D);
            Rectangle bg = null;
            if (i == 0) {
                bg = this.createBackground(Color.ANTIQUEWHITE, Color.BLACK);
                this.digits[i].setFill(Color.BLACK);
            }

            if (i == 1) {
                bg = this.createBackground(Color.RED, Color.BLACK);
                this.digits[i].setFill(Color.WHITE);
            }

            this.digitsGroup[i] = new Group(new Node[]{bg, this.digits[i]});
        }

    }

    private Rectangle createBackground(Color fill, Color stroke) {
        Rectangle bg = new Rectangle(30.0D, 50.0D, fill);
        bg.setStroke(stroke);
        bg.setStrokeWidth(3.0D);
        bg.setEffect(new Lighting());
        return bg;
    }

    public void refreshDigits(String number) {
        for(int i = 0; i < this.digits.length; ++i) {
            this.digits[i].setText(number.substring(i, i + 1));
        }

    }
}
