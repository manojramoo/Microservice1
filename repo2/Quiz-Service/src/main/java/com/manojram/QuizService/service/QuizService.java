package com.manojram.QuizService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manojram.QuizService.DAO.QuizDAO;
import com.manojram.QuizService.Feign.QuizInterface;
import com.manojram.QuizService.bean.QuestionWrapper;
import com.manojram.QuizService.bean.Quiz;
import com.manojram.QuizService.bean.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDAO quizDAO;
	
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		List<Integer> questions=quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIDs(questions);
		quizDAO.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int quizid) {
		// TODO Auto-generated method stub
		Quiz quiz=quizDAO.findById(quizid).get();
		List<Integer> questionIDs=quiz.getQuestionIDs();
		
		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIDs);
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(int quizid, List<Response> responses) {
		// TODO Auto-generated method stub
		
		return quizInterface.getScore(responses);
	}
}
