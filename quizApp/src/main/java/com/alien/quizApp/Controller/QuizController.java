package com.alien.quizApp.Controller;

import com.alien.quizApp.Dao.QuizDao;
import com.alien.quizApp.Model.QuestionWrapper;
import com.alien.quizApp.Model.QuizModel;
import com.alien.quizApp.Model.Response;
import com.alien.quizApp.Service.QuizService;
import jakarta.persistence.Id;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizService quizService;

    //testing
    @GetMapping("test")
    public String test(){
        return "Testing our new controller";
    }

    //quiz creation
    @PostMapping("/created")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numOfQuestion,
                                             @RequestParam String title){
    return this.quizService.createQuiz(category,numOfQuestion,title);
    }

    //Showing all questions to user by category
    @GetMapping("/allQuestions/{category}")
    public ResponseEntity<List<QuestionWrapper>> _showAllQuestions_ToUser(@PathVariable  String category){
         return this.quizService.getAllQuestionsForUser(category);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuesions(@PathVariable Integer id){
    return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){

    return this.quizService.calculateResult(id, response);
    }


}
