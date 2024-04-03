package com.manojram.QuizService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manojram.QuizService.bean.QuestionWrapper;
import com.manojram.QuizService.bean.QuizDto;
import com.manojram.QuizService.bean.Response;
import com.manojram.QuizService.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
		return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumQ(),quizDto.getTitle());
	}
	
	@GetMapping("get/{quizid}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int quizid){
		return quizService.getQuiz(quizid);
	}
	
	@PostMapping("submit/{quizid}")
	public ResponseEntity<Integer> getresult(@PathVariable int quizid,@RequestBody List<Response> responses){
		//return new ResponseEntity<Integer>(0,HttpStatus.ACCEPTED);
		return quizService.calculateResult(quizid,responses);
	}
}
