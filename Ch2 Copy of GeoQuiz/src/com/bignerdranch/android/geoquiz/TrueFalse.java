package com.bignerdranch.android.geoquiz;

//TrueFalse is model for true/false question
public class TrueFalse 
{
	private int mQuestion; //id number of question string
	private boolean mTrueQuestion; //whether the statement is true or false 
	
	public TrueFalse(int question, boolean trueQuestion)
	{
		mQuestion = question;
		mTrueQuestion = trueQuestion;
	}

	/**
	 * @return the question
	 */
	public int getQuestion() {
		return mQuestion;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(int question) {
		mQuestion = question;
	}

	/**
	 * @return the trueQuestion
	 */
	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	/**
	 * @param trueQuestion the trueQuestion to set
	 */
	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}
}
