package com.manojram.QuizService.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manojram.QuizService.bean.Quiz;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer>{

}
