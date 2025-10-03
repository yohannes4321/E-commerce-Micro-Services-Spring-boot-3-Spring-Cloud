package com.example.quiz;

import com.example.quiz.Quiz;

import com.example.quiz.QuizDao;


import com.example.quiz.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServices {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuizInterface quizInterface;
    public List<Quiz> findAll(){
        return quizDao.findAll();
    }
        public ResponseEntity<List<Quiz>> getAllValues(){
        return new ResponseEntity<>(quizDao.findAll(),HttpStatus.OK);

    }
    public ResponseEntity<String>save_quize(String category, String title) {
    List<Integer> questionsid=quizInterface.sendQuestionByQuiz(category).getBody();
    Quiz quiz=new Quiz();
    quiz.setTitle(title);
    quiz.setQuestionid(questionsid);
    quizDao.save(quiz);
    System.out.println(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizid) {
        Quiz quiz=quizDao.findById(quizid).get();
        List <Integer> questionsid=quiz.getQuestionid();
        ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsByQuestionid(questionsid);
        return questions;
    }

    public ResponseEntity<Integer> getscore(Integer id,List<Responce> responces) {

        ResponseEntity<Integer> score=quizInterface.getScore(responces);
        return score;
    }
}
