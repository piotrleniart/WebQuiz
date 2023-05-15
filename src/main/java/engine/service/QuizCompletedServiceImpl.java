package engine.service;

import engine.model.Quiz;
import engine.model.QuizCompleted;
import engine.model.User;
import engine.repository.QuizCompletedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizCompletedServiceImpl implements QuizCompletedService{

    @Autowired
    private QuizCompletedRepository quizCompletedRepository;

    @Override
    public List<QuizCompleted> getAllQuizzesCompleted() {
        return (List<QuizCompleted>) quizCompletedRepository.findAll();
    }

    @Override
    public Page<QuizCompleted> getAllCompletedQuizzesByUser(Long userId, Pageable pageable) {
        return quizCompletedRepository.findAllQuizzesCompletedByUser(userId, pageable);
    }

    @Override
    public QuizCompleted saveQuizCompletedByUser(QuizCompleted quizCompleted) {
        return quizCompletedRepository.save(quizCompleted);
    }
}
