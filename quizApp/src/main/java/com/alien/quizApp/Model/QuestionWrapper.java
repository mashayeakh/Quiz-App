package com.alien.quizApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
public class QuestionWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  questionTitle;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper() {

    }

    public static QuestionWrapper createFromQuestionModel(QuestionModel questionModel) {
        QuestionWrapper qw = new QuestionWrapper();

        qw.setId(questionModel.getId());
        qw.setCategory(questionModel.getCategory());
        qw.setOption1(questionModel.getOption1());
        qw.setOption2(questionModel.getOption2());
        qw.setOption3(questionModel.getOption3());
        qw.setOption4(questionModel.getOption4());
        qw.setQuestionTitle(questionModel.getQuestionTitle());

        return qw;
    }

    public QuestionWrapper(Integer id, String questionTitle, String category, String option1, String option2, String option3, String option4) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.category = category;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    @Override
    public String toString() {
        return "QuestionWrapper{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", category='" + category + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                '}';
    }
}
