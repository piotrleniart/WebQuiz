package engine.repository;

import engine.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {


    @Query(value = "SELECT * FROM quizzes",
            countQuery = "SELECT COUNT(*) FROM quizzes",
            nativeQuery = true)
    Page<Quiz> findAllQuizzesPaged(Pageable pageable);

}
