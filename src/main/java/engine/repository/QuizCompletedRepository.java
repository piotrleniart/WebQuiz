package engine.repository;


import engine.model.QuizCompleted;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizCompletedRepository extends CrudRepository<QuizCompleted, Long> {


    @Query(value = "SELECT * FROM completed_quizzes WHERE completed_by = ?1",
            countQuery = "SELECT COUNT(*) FROM completed_quizzes WHERE completed_by = ?1",
            nativeQuery = true)
    Page<QuizCompleted> findAllQuizzesCompletedByUser(Long userId, Pageable pageable);

}
