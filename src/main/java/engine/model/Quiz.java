package engine.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "quiz_id")
    private long quizId;
    @NotBlank
    //@Column(name = "title")
    private String title;
    @NotBlank
    //@Column(name = "text")
    private String text;
    @Size(min = 2)
    @NotNull
    @ElementCollection
    @CollectionTable(name = "quiz_options", joinColumns = @JoinColumn(name = "id"))
    //@Column(name = "options")
    private List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    @CollectionTable(name = "quiz_answer", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "answer")
    private List<Integer> answer;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<QuizCompleted> completedQuizzes = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer, User user) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.user = user;
    }


    public boolean isCorrectOpt(List<Integer> answer) {
        return answer.equals(this.answer); //Arrays.equals(this.answer, answer);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setId(long id) {
        this.quizId = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
