package com.alien.quizApp.Dao;

import com.alien.quizApp.Model.QuestionModel;
import com.alien.quizApp.Model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionDao extends JpaRepository<QuestionModel,Integer> {

    List<QuestionModel> findAllByCategory(String category);


    @Query(value = "select  * from question_table qt where qt.category=:category ORDER BY RAND() Limit :numOfQuestion",nativeQuery = true)
    List<QuestionModel> findRandomQuestionByCategory(String category, int numOfQuestion);


    @Query(value = "SELECT * FROM question_table qt WHERE qt.category = :category", nativeQuery = true)
    List<QuestionModel> findAllQuestionsByCategory(String category);


    @Query(value = "SELECT * FROM question_table qt WHERE qt.id = :id", nativeQuery = true)
    Optional<QuizModel> findRandomQuestionById(@Param("id") int id);

}
