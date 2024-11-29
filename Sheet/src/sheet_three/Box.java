package sheet_three;

enum FilledByPlayer {
    PLAYER1,
    PLAYER2,
    NOPLAYER;
}

public class Box {

    private boolean upLine;
    private boolean downLine;
    private boolean leftLine;
    private boolean rightLine;
    private FilledByPlayer filledByPlayer;

    public Box() {
        this.upLine = false;
        this.downLine = false;
        this.leftLine = false;
        this.rightLine = false;
        this.filledByPlayer = FilledByPlayer.NOPLAYER;
    }

    public void addUpLine() {
        this.upLine = true;
    }

    public void addDownLine() {
        this.downLine = true;
    }

    public void addLeftLine() {
        this.leftLine = true;
    }

    public void addRightLine() {
        this.rightLine = true;
    }

    public void checkIfPlayerGetsField(int i) {
        if ((i % 2 == 0) && this.upLine && this.downLine && this.leftLine && this.rightLine) {
            this.filledByPlayer = FilledByPlayer.PLAYER1;
        }
        if ((i % 2 == 1) && this.upLine && this.downLine && this.leftLine && this.rightLine) {
            this.filledByPlayer = FilledByPlayer.PLAYER2;
        }
    }
}
