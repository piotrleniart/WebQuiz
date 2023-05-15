package engine.controller;

import engine.model.*;
import engine.service.QuizCompletedService;
import engine.service.QuizService;
import engine.service.UserService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizCompletedService quizCompletedService;
    private final Ans ans = new Ans();
    //private final List<Quiz> quizList = new ArrayList<>();


    @PostMapping("/quizzes")
    public Quiz postQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal UserDetails auth) {
        //quiz.setId(quizList.size());
        //this.quizList.add(quiz);
        quiz.setUser(userService.getUserByEmail(auth.getUsername()));
        quizService.saveQuiz(quiz);
        //quiz.setUser(null);
        return quiz;
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable Long id) {

        try {
            return quizService.getQuizById(id);
            // return quizList.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }


    /*
    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
        //return quizList;
    }
    */

    @GetMapping("/quizzes")
    public Page<Quiz> getAllQuizzesPaged(@RequestParam(required = false, defaultValue = "0") Integer page) {
        return quizService.getAllQuizzesPaged(PageRequest.of(page, 10));
        //return quizList;
    }

    @GetMapping("/quizzes/completed")
    public Page<QuizCompleted> getAllCompletedQuizzesPaged(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @AuthenticationPrincipal UserDetails auth) {

        return quizCompletedService.getAllCompletedQuizzesByUser(
                userService.getUserByEmail(auth.getUsername()).getUserId(),
                PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "completed_at")));
    }

    @GetMapping("/comp")
    public List<QuizCompleted> getAllQuizzes() {
        return quizCompletedService.getAllQuizzesCompleted();
    }


    @PostMapping("/quizzes/{id}/solve")
    @ResponseBody
    public Ans solveQuizById(@PathVariable Long id, @RequestBody AnswerDto answer, @AuthenticationPrincipal UserDetails auth) {
        Quiz quizById;
        try {
            quizById = quizService.getQuizById(id);
            //quizById = quizList.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        this.ans.setAns(quizById.isCorrectOpt(answer.getAnswer()));
        if(quizById.isCorrectOpt(answer.getAnswer())) {
            this.quizCompletedService.saveQuizCompletedByUser(
                    new QuizCompleted(userService.getUserByEmail(auth.getUsername()), quizById, LocalDateTime.now())
            );
        }
        return ans;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody User user) {

        // check if the email is already registered
        if(userService.getUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userService.saveUser(user);
    }

    @DeleteMapping("/quizzes/{id}")
    public void deleteQuiz(@PathVariable Long id, @AuthenticationPrincipal UserDetails auth) {
        Quiz quiz;
        //find quiz by given id
        try{
            quiz = quizService.getQuizById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz not found"
            );
        }

        //check if current user is owner of the quiz
        if(!auth.getUsername().equals(quiz.getUser().getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        quizService.deleteQuizById(id);

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/quizzes/{id}")
    public void updateQuiz(@PathVariable Long id, @RequestBody Quiz newQuiz, @AuthenticationPrincipal UserDetails auth) {
        Quiz quiz;
        //find quiz by given id
        try{
            quiz = quizService.getQuizById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz not found"
            );
        }

        //check if current user is owner of the quiz
        if(!auth.getUsername().equals(quiz.getUser().getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        quizService.updateQuizById(id, newQuiz);
    }
}