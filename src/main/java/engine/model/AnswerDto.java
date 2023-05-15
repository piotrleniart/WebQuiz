package engine.model;

import java.util.List;

public class AnswerDto {

    private List<Integer> answer;

    public AnswerDto(List<Integer> answer) {
        this.answer = answer;
    }

    public AnswerDto() {
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
