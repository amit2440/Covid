package com.med.disease.tracking.app.controller;

import com.med.disease.tracking.app.model.Question;
import com.med.disease.tracking.app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addQuestion(@RequestBody Question question){
        questionRepository.saveQuestion(question);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> fetchAllQuestions(){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(questionRepository.selectAllQuestion());
    }

    @DeleteMapping(value = "/delete/{questionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestion(@PathVariable Integer questionId){
        questionRepository.deleteQuestion(questionId);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateQuestion(@RequestBody Question question){
        questionRepository.updateQuestion(question);
    }
}
