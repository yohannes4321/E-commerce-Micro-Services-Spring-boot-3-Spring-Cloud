package com.example.question.Dao;

import com.example.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

     List<Question> findByCategory(String category);
     @Query(value=" SELECT q.id FROM question q ORDER BY RANDOM();",nativeQuery=true)
 List<Integer> findRandomQuestionsBCategory(String category);
}
