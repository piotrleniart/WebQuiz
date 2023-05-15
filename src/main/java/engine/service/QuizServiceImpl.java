package engine.service;

import engine.model.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) quizRepository.findAll();
    }

    @Override
    public Page<Quiz> getAllQuizzesPaged(Pageable pageable) {
        return quizRepository.findAllQuizzesPaged(pageable);
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuizById(Long id, Quiz quizToUpdate) {
        Quiz quizFromDb = quizRepository.findById(id).get();
        quizFromDb.setTitle(quizToUpdate.getTitle());
        quizFromDb.setText(quizToUpdate.getText());
        quizFromDb.setOptions(quizToUpdate.getOptions());
        quizFromDb.setAnswer(quizToUpdate.getAnswer());

        return quizRepository.save(quizFromDb);
    }

    @Override
    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }
}
