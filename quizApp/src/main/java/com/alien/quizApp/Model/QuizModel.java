package com.alien.quizApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_table")
public class QuizModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;//1
    private String title; //javaQ
    @ManyToMany
    private List<QuestionModel> questionList;

    public QuizModel(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
