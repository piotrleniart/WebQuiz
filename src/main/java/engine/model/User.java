package engine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long userId;

    @NotBlank
    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email is not valid")
    private String email;

    @Size(min = 5, message = "Password should be at least 5 characters")
    private String password;

    private String role;
    @OneToMany(mappedBy = "user")
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "completedQuizUser")
    private List<QuizCompleted> completedQuizzes = new ArrayList<>();

    public User() {
    }

    public User(long userId, String email, String password, List<Quiz> quizzes, List<QuizCompleted> completedQuizzes) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.quizzes = quizzes;
        this.completedQuizzes = completedQuizzes;
    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<QuizCompleted> getCompletedQuizzes() {
        return completedQuizzes;
    }

    public void setCompletedQuizzes(List<QuizCompleted> completedQuizzes) {
        this.completedQuizzes = completedQuizzes;
    }
}
