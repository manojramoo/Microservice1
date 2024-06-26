package com.manojram.QuizService.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.manojram.QuizService.bean.QuestionWrapper;
import com.manojram.QuizService.bean.Response;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam int num);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
	
}
