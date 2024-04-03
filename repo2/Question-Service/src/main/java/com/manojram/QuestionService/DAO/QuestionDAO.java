package com.manojram.QuestionService.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manojram.QuestionService.bean.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{

	 
	List<Question> findAllByCategory(String cat);
	
	@Query(value="SELECT q.id FROM QUESTION q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
	List<Integer> findRandomQuestonsByCategory(String category, int numQ);
}
