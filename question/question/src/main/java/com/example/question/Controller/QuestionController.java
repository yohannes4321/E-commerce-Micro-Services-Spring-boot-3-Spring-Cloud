package com.example.question.Controller;

import com.example.question.Responce;
import com.example.question.model.Question;

import com.example.question.services.QuestionServices;
import com.example.question.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionServices questionServices;

    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionServices.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category){

        return questionServices.getQuestionByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionServices.addQuestion(question);
    }

    @GetMapping("send")
    public ResponseEntity<List<Integer>> sendQuestionByQuiz(@RequestParam String category){
        return questionServices.getQuestionsForQuiz(category);
    }

    @PostMapping("getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuestionid(@RequestBody List<Integer> questionid){
        return questionServices.getQuestionByQuestionid(questionid);
    }

    @PostMapping("/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Responce> responces){
    return questionServices.getScore(responces);
    }




    //generate

    //getQuestion (questionid)
    // get score


}
