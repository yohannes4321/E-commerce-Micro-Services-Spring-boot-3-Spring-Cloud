package com.example.quiz.feign;

import com.example.quiz.QuizApplication;
import com.example.quiz.QuizDto;
import com.example.quiz.QuestionWrapper;
import com.example.quiz.Responce;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/send")
    public ResponseEntity<List<Integer>> sendQuestionByQuiz(@RequestParam String category);


    @PostMapping("question/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuestionid(@RequestBody List<Integer> questionid);

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Responce> responces);



}
