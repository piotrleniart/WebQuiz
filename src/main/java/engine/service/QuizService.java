package engine.service;

import engine.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {

    List<Quiz> getAllQuizzes();

    Page<Quiz> getAllQuizzesPaged(Pageable pageable);

    Quiz getQuizById(Long id);

    Quiz saveQuiz(Quiz quiz);

    Quiz updateQuizById(Long id, Quiz quizToUpdate);

    void deleteQuizById(Long id);

}
