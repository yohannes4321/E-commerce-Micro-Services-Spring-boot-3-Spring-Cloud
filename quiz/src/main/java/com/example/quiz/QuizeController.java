package com.example.quiz;

import com.example.quiz.QuestionWrapper;
import com.example.quiz.QuizServices;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()

public class QuizeController {
    @Autowired
    QuizServices quizServices;
    @PostMapping("/create")
    public ResponseEntity<String> addQuiz(@RequestBody QuizDto quizDto ) {
        return quizServices.save_quize(quizDto.getCategoryname(),quizDto.getTitle());
    }
    @GetMapping("/getquize/{id}")
    public ResponseEntity<List<QuestionWrapper>> getquestionbyid(@PathVariable("id") Integer id ) {
        return quizServices.getQuizQuestions(id);
    }
    @GetMapping("/score")
    public ResponseEntity<Integer> getscore(@PathVariable Integer quizid,List<Responce> responces) {
       return  quizServices.getscore(quizid,responces);

    }

}
