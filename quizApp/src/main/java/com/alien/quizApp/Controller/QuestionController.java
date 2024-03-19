package com.alien.quizApp.Controller;

import com.alien.quizApp.Model.QuestionModel;
import com.alien.quizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.QuitEvent;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }
    //fetching all questions.
    @GetMapping("allQuestions")
    public ResponseEntity<List<QuestionModel>> getAllQuestion(){
        //return "Hi, These are your Questions";
        return this.questionService.getAllQuestions();
    }

    //fetching by category.
    @GetMapping("category/{category}")
    public ResponseEntity<List<QuestionModel>> getAllQuestionByCategory(@PathVariable() String category){
        return this.questionService.getAllQuestionByCategory(category);
    }

    //adding a question in db
    @PostMapping("create")
    public ResponseEntity<QuestionModel> createQuestion(@RequestBody QuestionModel questionModel){
        return this.questionService.createQuestion(questionModel);

    }
    //deleting a question by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        return this.questionService.deleteById(id);
    }

    //updating a question
//    @PutMapping("update/{id}")
//    public String update(@PathVariable int id, Map<String, Object> update){
//         return this.questionService.updateQuestion(id,update);
//    }


    @PutMapping("update/{id}")
    public ResponseEntity <QuestionModel> updateQuiz(@PathVariable int id,
                                                     @RequestBody QuestionModel questionModel){
        //setting id to find the specific quiz
        questionModel.setId(id);
        System.out.println(questionModel.getId());

        if(questionService.existId(id)){
            QuestionModel result = this.questionService.updateEntity(questionModel).getBody();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
