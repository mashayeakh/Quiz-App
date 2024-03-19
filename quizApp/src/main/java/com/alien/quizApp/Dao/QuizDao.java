package com.alien.quizApp.Dao;

import com.alien.quizApp.Model.QuestionModel;
import com.alien.quizApp.Model.QuizModel;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizDao extends JpaRepository<QuizModel, Integer> {


    //;List<QuestionModel> generateRandomQuestion();
}
