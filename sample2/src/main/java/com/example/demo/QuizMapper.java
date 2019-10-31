package com.example.demo;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuizMapper {

	ArrayList<Quiz> findAll();

	ArrayList<Quiz> findQuestion();

	ArrayList<Quiz> findOne(String ploblemNumber);

	ArrayList<Quiz> findQuiz(int num);

	ArrayList<Quiz> quizResult(@Param("ploblemNumber") String ploblemNumber, @Param("result") String result);

	ArrayList<Quiz> quizCreate(@Param("ploblemNumber") String ploblemNumber, @Param("orderNumber") String orderNumber,
			@Param("question") String question,
			@Param("answer") String answer, @Param("result") String result);

	ArrayList<Quiz> quizUpdate(@Param("question") String question, @Param("answer") String answer,
			@Param("result") String result,
			@Param("ploblemNumber") String ploblemNumber, @Param("orderNumber") String orderNumber);

	void quizDelete(String ploblemNumber);

	void ploblemNumberUpdate(@Param("ploblemN") String ploblemN, @Param("ploblemUpNum") String ploblemUpNum);
}
