package engine.model;

public class Ans {
    private boolean success;
    private String feedback;

    public Ans setAns(boolean success) {
        this.success = success;
        if (success) {
            this.feedback = "Congratulations, you're right!";
        } else {
            this.feedback = "Wrong answer! Please, try again.";
        }
        return this;
    }

    public Ans() {
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
