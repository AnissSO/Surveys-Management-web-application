package com.stage.WebApp21.model;


import java.util.ArrayList;

import lombok.Data;

@Data
public class ArrayListQuestion {
	
	private ArrayList<PageQuestionnaire> listPageQuestionnaire;
	private ArrayList<Question> listQuestions;
	private ArrayList<QuestionOption> listQuestionOptions;
}
