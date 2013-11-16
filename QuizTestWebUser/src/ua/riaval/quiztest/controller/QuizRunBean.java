package ua.riaval.quiztest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ua.riaval.quiztest.entity.AnswerResult;
import ua.riaval.quiztest.entity.QuestionResult;
import ua.riaval.quiztest.entity.QuizResult;

@ManagedBean
@SessionScoped
public class QuizRunBean {

	private QuizResult quiz;
	private QuestionResult currentQuestion;
	private long timeLeft;
	private Timer timer = new Timer();

	List<String> multiAnswers;
	String singleAnswer;

//	public void inject(int requestId) {
//		if (quiz != null) {
//			return;
//		}
//
//		Quiz defaultQuiz = DAOFactory.getQuizDAO().findByID(requestId);
//		this.quiz = new QuizResult(defaultQuiz);
//
//		timeLeft = defaultQuiz.getTimeLimit() * 60;
//		currentQuestion = this.quiz.getQuestionResults().iterator().next();
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				timeLeft--;
//			}
//		}, 5, 1000);
//	}

	public String getTimeLeft() {
		String min = String.valueOf(timeLeft / 60);
		String sec = String.valueOf(timeLeft % 60);
		return min + " : " + sec;
	}

	public String getSingleAnswer() {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			if (answerResult.isChecked()) {
				return answerResult.getText();
			}
		}
		return null;
	}

	public List<String> getMultiAnswers() {
		List<String> answers = new ArrayList<String>();
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			if (answerResult.isChecked()) {
				answers.add(answerResult.getText());
			}
		}
		return answers;
	}
	
	public String getOpenAnswer() {
		return getSingleAnswer();
	}

	public void setSingleAnswer(String answer) {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
//			System.out.println(answerResult.getText().equals(answer) + " : " + answerResult.getText() + " : " + answer);
			if (answerResult.getText().equals(answer)) {
				answerResult.setChecked(true);
				return;
			}
			answerResult.setChecked(false);
		}
	}

	public void setMultiAnswers(List<String> answers) {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			for (String answer : answers) {
				if (answerResult.getText().equals(answer)) {
					answerResult.setChecked(true);
				}
			}
		}
	}
	
	public void setOpenAnswer(String answer) {
		for (AnswerResult answerResult : currentQuestion.getAnswerResults()) {
			if (answerResult.isChecked()) {
				answerResult.setText(answer);
				return;
			}
		}
		
		AnswerResult ar = new AnswerResult(answer, true);
		currentQuestion.getAnswerResults().add(ar);
	}

	public void finish() {

	}

	public QuizResult getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizResult quiz) {
		this.quiz = quiz;
	}

	public QuestionResult getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(QuestionResult currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

}