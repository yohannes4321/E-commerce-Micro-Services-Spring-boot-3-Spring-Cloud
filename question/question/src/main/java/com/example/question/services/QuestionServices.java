package com.example.question.services;

import com.example.question.Responce;
import com.example.question.model.Question;
import com.example.question.QuestionWrapper;
import com.example.question.Dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServices {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
    catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<Question>> getQuestionByCategory(String catagory){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(catagory), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String > addQuestion(Question que){
         questionDao.save(que);

        return new ResponseEntity<>("Question added successfully", HttpStatus.OK);
    }
    public ResponseEntity<List<Integer>> getQuestionsForQuiz (String category){
        return new ResponseEntity<>(questionDao.findRandomQuestionsBCategory(category),HttpStatus.OK);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuestionByQuestionid(List<Integer> questionid){
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for (Integer id: questionid){
            questions.add(questionDao.findById(id).get());
        }
        for (Question question: questions){
            QuestionWrapper wrapper= new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);

    }
    public ResponseEntity<Integer>getScore(List<Responce> responces){
        int right=0;
        for (Responce responced: responces){
            Question question=questionDao.findById(responced.getId()).get();
            if(responced.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
