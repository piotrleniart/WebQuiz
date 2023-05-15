package engine.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "completed_quizzes")
public class QuizCompleted {


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id
    @Column(name = "completed_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long complId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "completed_by")
    private User completedQuizUser;

    @ManyToOne //(targetEntity = Quiz.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")//, insertable = false, updatable = false)
    @JsonIncludeProperties(value = "id")
    private Quiz id;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;


    public QuizCompleted() {
    }

    public QuizCompleted(User completedQuizUser, Quiz id, LocalDateTime completed) {
        this.completedQuizUser = completedQuizUser;
        this.id = id;
        this.completedAt = completed;
    }

    public Long getComplId() {
        return complId;
    }

    public void setComplId(Long complId) {
        this.complId = complId;
    }

    public User getCompletedQuizUser() {
        return completedQuizUser;
    }

    public void setCompletedQuizUser(User completedQuizUser) {
        this.completedQuizUser = completedQuizUser;
    }

    public Long getId() {
        return id.getId();
    }

    public void setId(Quiz id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
