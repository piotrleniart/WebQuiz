package engine.service;

import engine.model.Quiz;
import engine.model.QuizCompleted;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizCompletedService {

    List<QuizCompleted> getAllQuizzesCompleted();

    Page<QuizCompleted> getAllCompletedQuizzesByUser(Long userId, Pageable pageable);

    QuizCompleted saveQuizCompletedByUser(QuizCompleted quizCompleted);

}
