package ServerClient;

import java.awt.*;
import java.io.Serializable;


public class MessageModel implements Serializable {
    private int round;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;

    public MessageModel(int round, Color color1, Color color2, Color color3, Color color4) {
        this.round = round;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColor3() {
        return color3;
    }

    public void setColor3(Color color3) {
        this.color3 = color3;
    }

    public Color getColor4() {
        return color4;
    }

    public void setColor4(Color color4) {
        this.color4 = color4;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "round=" + round +
                ", color1=" + color1 +
                ", color2=" + color2 +
                ", color3=" + color3 +
                ", color4=" + color4 +
                '}';
    }
}
