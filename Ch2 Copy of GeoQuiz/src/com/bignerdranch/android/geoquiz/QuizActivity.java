package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*The QuizActivity class is a subclass of Activity that serves as the controller.
*It ties view (i.e. Buttons, TextView) and model objects(i.e. TrueFalse class) together.
*It contains the application logic and designed to respond to events triggered by user.
*
*
*/
public class QuizActivity extends Activity 
{	
	//All widgets are View instances or subclasses (i.e. Button, TextView, etc.)
	private Button mTrueButton; //widget (i.e.view object) that responds to user input
	private Button mFalseButton; //widget (i.e.view object) that responds to user input
	private Button mNextButton; //widget (i.e.view object) that responds to user input
	private Button mPrevButton; //widget (i.e.view object) that responds to user input
	private TextView mQuestionTextView; //widget (i.e.view object) that responds to user input
	
	/*Array to hold TrueFalse objects that are the question and their corresponding answer.
	 *Notice the parameter passed to TrueFalse constructor is int and boolean  
	 *
	*/
	private TrueFalse[] mQuestionBank = new TrueFalse[] 
	{
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
	};
	
	
	private int mCurrentIndex = 0; //for index of mQuestionBank array
	
	
	//The updateQuestion method sets text of TextView that shows a question to user
	private void updateQuestion()
	{
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	
	/*
	 * The checkAnswer method passes the correct toast to makeText using ID
	 */
	private void checkAnswer(boolean userPressedTrue)
	{
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		
		if(userPressedTrue == answerIsTrue)
		{
			messageResId = R.string.correct_toast;
		}
		else
		{
			messageResId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}
	
	
	
	/*
	 *OnCreate is called by OS after Activity instance is created
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override //overridden to prepare specifics (i.e. inflate, setting listeners, etc.)
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz); //inflate activity_quiz layout resource
		
		
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		
		updateQuestion();
		
		//Advance question when the mQuestionTextView is clicked
		mQuestionTextView.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) 
			{
				//update question and increment array index value
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
		
				
		//register listener for mTrueButton to toast user on click
		mTrueButton = (Button) findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) 
			{
				checkAnswer(true);
			}
		});
		
		
		//register listener for mFalseButton to toast user on click
		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				checkAnswer(false);				
			}
		});
		
		//register listener for mPrevButton to supply next question on click
		mPrevButton = (Button) findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) 
			{
				if(mCurrentIndex > 0)
				{
					mCurrentIndex = (mCurrentIndex - 1);
					updateQuestion();
				}
			}
		});

		
		//register listener for mNextButton to supply next question on click
		mNextButton = (Button) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) 
			{
				//update question and increment array index value
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();				
			}
		});		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
}
