package com.manojram.QuestionService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manojram.QuestionService.DAO.QuestionDAO;
import com.manojram.QuestionService.bean.Question;
import com.manojram.QuestionService.bean.QuestionWrapper;
import com.manojram.QuestionService.bean.Response;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDAO questionDAO;
	
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionDAO.findAll();
	}
	public List<Question> getAllQuestionsByCat(String cat) {
		// TODO Auto-generated method stub
		return questionDAO.findAllByCategory(cat);
	}
	public String addQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDAO.save(question);
		return "success";
	}
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int num) {
		// TODO Auto-generated method stub
		List<Integer> quest=questionDAO.findRandomQuestonsByCategory(category,num);
		return new ResponseEntity<List<Integer>>(quest,HttpStatus.ACCEPTED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		for(Integer id:questionIds) {
			questions.add(questionDAO.findById(id).get());
		}
		for(Question question:questions) {
			QuestionWrapper wrapper=new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<List<QuestionWrapper>>(wrappers,HttpStatus.ACCEPTED);
	}
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		// TODO Auto-generated method stub
		
		int right=0;
		
		for(Response response:responses) {
			Question question=questionDAO.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) {
				right++;
			}
		}
		return new ResponseEntity<Integer>(right,HttpStatus.ACCEPTED);
	}

}
