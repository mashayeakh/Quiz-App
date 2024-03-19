package com.alien.quizApp.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    //this class is responsible to submit all the correct ans along with the id,
    // Bcause, only id is the primary key
    private Integer id;
    private String response;
}
