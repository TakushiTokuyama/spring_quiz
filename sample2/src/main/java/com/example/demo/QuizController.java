package com.example.demo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class QuizController {

	@Autowired
	QuizMapper quizMapper;

	@GetMapping("question")
	public ModelAndView quiz(ModelAndView mav) {

		 mav.setViewName("question");

		ArrayList<Quiz> quizLists = quizMapper.findAll();

		int quizCount = quizLists.size();

		int ploblemNnumbers = (quizCount / 4);

		Random random = new SecureRandom();

		int r = random.nextInt(ploblemNnumbers) + 1;

		ArrayList<Quiz> quizList = new ArrayList<>();

		int questionNumber = r * 4 - 4;

		quizList = quizMapper.findQuiz(questionNumber);

		mav.addObject("quizList", quizList);

		return mav;
	}

	/* DBなし用

	 mav.addObject("r",r);

	ArrayList<String> questions = new ArrayList<String>();

	questions.add("次の配列式で正しくないものはどれですか？");
	questions.add("サーバー側が保持する一次データの集まりを何という？");

	ArrayList<String> answers = new ArrayList<String>();


	answers.add("int[] a = new int {1,2,3}");
	answers.add("int[3] a = new int[]");
	answers.add("int[] a = {0}");
	answers.add("int a[] = new int[1]");

	answers.add("Transaction");
	answers.add("Cookie");
	answers.add("Session");
	answers.add("Listener");


	//DBなし用
	//mav.addObject("questions",questions);
	// mav.addObject("answers",answers);

	return mav;
	}
	*/
	/*  DBなし用

	  @PostMapping("/question")
	  public ModelAndView quizAnswer(ModelAndView mav , @RequestParam String answer,
			  @RequestParam String question) {




		  if(answer.equals("") && question.equals("")) {
			  return new ModelAndView("/question");
		  }

		  if(question.equals("1")){
			  if(answer.equals("2")){
				  mav.addObject("answer", answer + "正解");
			  }else {
				  mav.addObject("answer", answer + "不正解");
			  }
		  }

		  if(question.equals("2")){
			  if(answer.equals("3")){
				  mav.addObject("answer", answer + "正解");
			  }else {
				  mav.addObject("answer", answer + "不正解");
			  }
		  }


		  mav.addObject("restart" , "次の問題へ");


		  return mav;

	  }

	*/
	@PostMapping("question")
	public ModelAndView quizAnswer(ModelAndView mav, @RequestParam String ploblemNumber, @RequestParam String result) {

		mav.setViewName("answer");

		ArrayList<Quiz> quizResult = quizMapper.quizResult(ploblemNumber, result);

		mav.addObject("quizResult", quizResult);

		mav.addObject("ploblemNumber", ploblemNumber);

		mav.addObject("restart", "次の問題へ");

		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView answer(ModelAndView mav) {

		mav.setViewName("index");

		return mav;
	}

	@GetMapping("createQuiz")
	public ModelAndView createQuiz(ModelAndView mav) {

		ArrayList<Quiz> quizList = quizMapper.findAll();

		int quizCount = quizList.size();

		int ploblemNumber = (quizCount / 4) + 1;

		mav.addObject("ploblemNumber", ploblemNumber);

		return mav;
	}

	@PostMapping("createQuiz")
	public ModelAndView createQuiz(@Valid @ModelAttribute("formModel") Quiz quiz, BindingResult bindingResult,
			@RequestParam String ploblemNumber, @RequestParam String question,
			@RequestParam String answerOne, @RequestParam String answerTwo, @RequestParam String answerThree,
			@RequestParam String answerFour, @RequestParam String result, ModelAndView mav) {

		if (bindingResult.hasErrors()) {

			ArrayList<Quiz> quizList = quizMapper.findAll();

			int quizCount = quizList.size();

			int ploblemNumberPost = (quizCount / 4) + 1;

			mav.addObject("ploblemNumber", ploblemNumberPost);

			mav.addObject("message", "問題と答えを入力してください");

			return mav;

		}

		mav.setViewName("index");

		switch (result) {

		case "1":
			quizMapper.quizCreate(ploblemNumber, "1", question, answerOne, "正解");
			quizMapper.quizCreate(ploblemNumber, "2", question, answerTwo, "不正解");
			quizMapper.quizCreate(ploblemNumber, "3", question, answerThree, "不正解");
			quizMapper.quizCreate(ploblemNumber, "4", question, answerFour, "不正解");
			break;

		case "2":
			quizMapper.quizCreate(ploblemNumber, "1", question, answerOne, "不正解");
			quizMapper.quizCreate(ploblemNumber, "2", question, answerTwo, "正解");
			quizMapper.quizCreate(ploblemNumber, "3", question, answerThree, "不正解");
			quizMapper.quizCreate(ploblemNumber, "4", question, answerFour, "不正解");
			break;

		case "3":
			quizMapper.quizCreate(ploblemNumber, "1", question, answerOne, "不正解");
			quizMapper.quizCreate(ploblemNumber, "2", question, answerTwo, "不正解");
			quizMapper.quizCreate(ploblemNumber, "3", question, answerThree, "正解");
			quizMapper.quizCreate(ploblemNumber, "4", question, answerFour, "不正解");
			break;

		case "4":
			quizMapper.quizCreate(ploblemNumber, "1", question, answerOne, "不正解");
			quizMapper.quizCreate(ploblemNumber, "2", question, answerTwo, "不正解");
			quizMapper.quizCreate(ploblemNumber, "3", question, answerThree, "不正解");
			quizMapper.quizCreate(ploblemNumber, "4", question, answerFour, "正解");
			break;

		default:
			break;
		}

		mav.addObject("createPloblemNumber", ploblemNumber);

		return mav;
	}

	@GetMapping("showQuiz")
	public ModelAndView showQuiz(ModelAndView mav) {

		mav.setViewName("showQuiz");

		ArrayList<Quiz> questionList = quizMapper.findQuestion();

		int a = questionList.size();

		mav.addObject("a", a);

		mav.addObject("questionList", questionList);

		return mav;
	}

	@GetMapping("editQuiz/{ploblemNumber}")
	public ModelAndView editQuiz(ModelAndView mav, @PathVariable String ploblemNumber) {
		mav.setViewName("editQuiz");

		ArrayList<Quiz> quizList = quizMapper.findAll();

		int ploblemNums = quizList.size() / 4;

		mav.addObject("lastPloblemNumber", ploblemNums);


		ArrayList<Quiz> quiz = quizMapper.findOne(ploblemNumber);

		mav.addObject("quiz", quiz);

		return mav;
	}

	@PostMapping("editQuiz")
	public ModelAndView updateQuiz(@RequestParam String ploblemNumber, @RequestParam String question,
			@RequestParam String answerOne, @RequestParam String answerTwo, @RequestParam String answerThree,
			@RequestParam String answerFour, @RequestParam String result, ModelAndView mav) {

		mav.setViewName("index");



		switch (result) {

		case "1":
			quizMapper.quizUpdate(question, answerOne, "正解", ploblemNumber, "1");
			quizMapper.quizUpdate(question, answerTwo, "不正解", ploblemNumber, "2");
			quizMapper.quizUpdate(question, answerThree, "不正解", ploblemNumber, "3");
			quizMapper.quizUpdate(question, answerFour, "不正解", ploblemNumber, "4");
			break;

		case "2":
			quizMapper.quizUpdate(question, answerOne, "不正解", ploblemNumber, "1");
			quizMapper.quizUpdate(question, answerTwo, "正解", ploblemNumber, "2");
			quizMapper.quizUpdate(question, answerThree, "不正解", ploblemNumber, "3");
			quizMapper.quizUpdate(question, answerFour, "不正解", ploblemNumber, "4");
			break;

		case "3":
			quizMapper.quizUpdate(question, answerOne, "不正解", ploblemNumber, "1");
			quizMapper.quizUpdate(question, answerTwo, "不正解", ploblemNumber, "2");
			quizMapper.quizUpdate(question, answerThree, "正解", ploblemNumber, "3");
			quizMapper.quizUpdate(question, answerFour, "不正解", ploblemNumber, "4");
			break;

		case "4":
			quizMapper.quizUpdate(question, answerOne, "不正解", ploblemNumber, "1");
			quizMapper.quizUpdate(question, answerTwo, "不正解", ploblemNumber, "2");
			quizMapper.quizUpdate(question, answerThree, "不正解", ploblemNumber, "3");
			quizMapper.quizUpdate(question, answerFour, "正解", ploblemNumber, "4");
			break;

		default:
			break;
		}

		mav.addObject("updatePloblemNumber", ploblemNumber);

		return mav;
	}

	@GetMapping("deleteQuiz/{ploblemNumber}")
	public ModelAndView deleteQuiz(ModelAndView mav, @PathVariable String ploblemNumber) {
		mav.setViewName("deleteQuiz");

		ArrayList<Quiz> quiz = quizMapper.findOne(ploblemNumber);

		mav.addObject("quiz", quiz);

		return mav;
	}

	@PostMapping("deleteQuiz")
	public ModelAndView deleteQuizPost(@RequestParam String ploblemNumber, ModelAndView mav) {

		mav.setViewName("index");

		ArrayList<Quiz> quizList = quizMapper.findAll();

		int ploblemNums = quizList.size() / 4;


		int ploblemNum = Integer.parseInt(ploblemNumber);

		if (ploblemNum == 1) {

			quizMapper.quizDelete(ploblemNumber);

			for (int i = 1; i <= ploblemNums - 1; i++) {

				int ploblemUpNumber = ploblemNum + i;

				String ploblemUpNum = String.valueOf(ploblemUpNumber);

				String ploblemN = String.valueOf(i);

				quizMapper.ploblemNumberUpdate(ploblemN, ploblemUpNum);

			}

			mav.addObject("deletePloblemNumber", ploblemNumber);

			return mav;

		} else if (ploblemNum == ploblemNums) {

			quizMapper.quizDelete(ploblemNumber);

			mav.addObject("deletePloblemNumber", ploblemNumber);

			return mav;
		}

		else {

			quizMapper.quizDelete(ploblemNumber);

			for (int i = ploblemNum + 1; ploblemNum < ploblemNums; i++) {

				String ploblemUpNum = String.valueOf(i);

				String ploblemN = String.valueOf(ploblemNum++);

				quizMapper.ploblemNumberUpdate(ploblemN, ploblemUpNum);

			}

			mav.addObject("deletePloblemNumber", ploblemNumber);

			return mav;
		}

	}

}
