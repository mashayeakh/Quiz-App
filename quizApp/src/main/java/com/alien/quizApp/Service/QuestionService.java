package com.alien.quizApp.Service;

import com.alien.quizApp.Config.ModelMapperConfig;
import com.alien.quizApp.Dao.QuestionDao;
import com.alien.quizApp.Model.QuestionModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        try{
            return new ResponseEntity<>(this.questionDao.findAll(), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionModel>> getAllQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(this.questionDao.findAllByCategory(category),HttpStatus.FOUND);

        }catch (Exception exception ){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public boolean existId(int id){
         Optional <QuestionModel> existId = this.questionDao.findById(id);

//         boolean exist = existId.isPresent();
        boolean exist =  existId.map(QuestionModel->QuestionModel.getId()).isPresent();
         if(exist){
             System.out.println(exist);
             return true;
         }else
             return false;
    }

    //create question
    public ResponseEntity<QuestionModel> createQuestion(QuestionModel questionModel) {

        try{
            return new ResponseEntity<>(this.questionDao.save(questionModel),HttpStatus.CREATED);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(this.questionDao.save(questionModel),HttpStatus.CONFLICT);
//         return "Created";
    }

    //deleting specific id
    public ResponseEntity<String> deleteById(int id) {

        if(this.questionDao.existsById(id)){
            try {
                return new ResponseEntity<>("Deleted!", HttpStatus.OK);
            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>("Error occurred during deletion", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<QuestionModel> updateEntity(QuestionModel updatedEntity) {
        // perform update logic, save to the database, etc.
        return new ResponseEntity<>(questionDao.save(updatedEntity),HttpStatus.OK);
    }

}
