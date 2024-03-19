package com.alien.quizApp.Service;

import com.alien.quizApp.Dao.QuestionDao;
import com.alien.quizApp.Dao.QuizDao;
import com.alien.quizApp.Model.QuestionModel;
import com.alien.quizApp.Model.QuestionWrapper;
import com.alien.quizApp.Model.QuizModel;
import jakarta.persistence.Id;
import com.alien.quizApp.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;



    public ResponseEntity<String> createQuiz(String category, int numOfQuestion, String title) {
        //quizDao.generateRandomQuestion();
        List<QuestionModel> randomQuestion = questionDao.findRandomQuestionByCategory(category, numOfQuestion);

        QuizModel _newQuizModel = new QuizModel();

        _newQuizModel.setTitle(title);
        //_newQuizModel.set
        _newQuizModel.setQuestionList(randomQuestion);
        this.quizDao.save(_newQuizModel);

        return new ResponseEntity<>(" Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getAllQuestionsForUser(String category) {

        if(category==null) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}

        try{
                List<QuestionModel> questions = this.questionDao.findAllQuestionsByCategory(category);
                System.out.println(questions);

                QuestionModel questionModel;

                List<QuestionWrapper> questionWrappers = questions.stream()
                        .map(QuestionWrapper::createFromQuestionModel)  // Assuming QuestionWrapper has a constructor that takes a QuestionModel
                        .collect(Collectors.toList());

                System.out.println(questionWrappers);

                return new ResponseEntity<>(questionWrappers, HttpStatus.FOUND);
        }catch (EmptyResultDataAccessException exception){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<QuizModel> quizModel = quizDao.findById(id);
        System.out.println(quizModel);
        if(quizModel.isEmpty()){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        try{
            List<QuestionModel> qm = quizModel.get().getQuestionList();
            System.out.println(qm);
            List<QuestionWrapper> qw = qm.stream()
                    .map(QuestionWrapper::createFromQuestionModel)
                    .collect(Collectors.toList());
            System.out.println(qw);
            return new ResponseEntity<>(qw, HttpStatus.FOUND);
        }catch (Exception exception){return new ResponseEntity<>((HttpStatus.NOT_FOUND));}
    }

    //calculating the corrected ans.
 public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {

        //fetching all the questions that matches with the java id
     //Optional<QuizModel> qm = this.quizDao.findById(id);
     QuizModel qm = this.quizDao.findById(id).get();
     System.out.println(qm);

     //fetching questions.
     List<QuestionModel> question = qm.getQuestionList();
     System.out.println(question);
     int right=0;
    int option =0;
     for(Response res : response){
         if (res.getResponse().trim().equalsIgnoreCase(question.get(option).getRightAnswer().trim())) {
             right++;
             System.out.println("Ans: " +right);
         }
         option++;
     }
     return new ResponseEntity<>(right,HttpStatus.OK);
     //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

