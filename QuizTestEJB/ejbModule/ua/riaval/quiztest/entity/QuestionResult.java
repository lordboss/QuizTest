package ua.riaval.quiztest.entity;

// Generated 04.11.2013 10:49:58 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * QuestionResult generated by hbm2java
 */
@Entity
@Table(name = "QuestionResult", catalog = "QuizTest")
public class QuestionResult implements java.io.Serializable {

	private Integer id;
	private QuestionType questionType;
	private QuizResult quizResult;
	private String text;
	private byte[] image;
	private boolean isLatex;
	private Set<AnswerResult> answerResults = new LinkedHashSet<AnswerResult>();

	public QuestionResult() {
	}

	public QuestionResult(QuestionType questionType, QuizResult quizResult,
			boolean isLatex) {
		this.questionType = questionType;
		this.quizResult = quizResult;
		this.isLatex = isLatex;
	}

	public QuestionResult(QuestionType questionType, QuizResult quizResult,
			String text, byte[] image, boolean isLatex,
			Set<AnswerResult> answerResults) {
		this.questionType = questionType;
		this.quizResult = quizResult;
		this.text = text;
		this.image = image;
		this.isLatex = isLatex;
		this.answerResults = answerResults;
	}
	
	public QuestionResult(Question question) {
		this.questionType = question.getQuestionType();
		this.text = question.getText();
		for (Answer answer : question.getAnswers()) {
			AnswerResult ar = new AnswerResult(answer);
			ar.setQuestionResult(this);
			answerResults.add(ar);
		}
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionType_id", nullable = false)
	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quizResult_id", nullable = false)
	public QuizResult getQuizResult() {
		return this.quizResult;
	}

	public void setQuizResult(QuizResult quizResult) {
		this.quizResult = quizResult;
	}

	@Column(name = "text", length = 500)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "isLatex", nullable = false)
	public boolean isIsLatex() {
		return this.isLatex;
	}

	public void setIsLatex(boolean isLatex) {
		this.isLatex = isLatex;
	}

	@OrderBy
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionResult", cascade = CascadeType.ALL)
	public Set<AnswerResult> getAnswerResults() {
		return this.answerResults;
	}

	public void setAnswerResults(Set<AnswerResult> answerResults) {
		this.answerResults = answerResults;
	}

}
