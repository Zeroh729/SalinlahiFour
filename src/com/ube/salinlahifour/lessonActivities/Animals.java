package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.animation.AnimatedButtonListener;
import com.ube.salinlahifour.enumTypes.LevelType;

public class Animals extends AbstractLessonActivity implements OnClickListener{
	private ImageButton[] btn_up;
	private ImageButton[] btn_down;
	private ImageButton[] btn_cards;
	
	private ImageView[] iv_signs;

	private ArrayList<Card> englishCards;
	private ArrayList<Card> soundCards;
	private ArrayList<Card> pictureCards;
	private ArrayList<Card> filipinoCards;
	
	private ArrayList<Card> englishCardsOnHand;
	private ArrayList<Card> soundCardsOnHand;
	private ArrayList<Card> pictureCardsOnHand;
	private ArrayList<Card> filipinoCardsOnHand;

	private int englishCardIndex;
	private int soundCardIndex;
	private int pictureCardIndex;
	private int filipinoCardIndex;
	
	private int fixedIndex;
	private Card answerCard;
	private int questionno;
	
	private ImageButton btn_function;
	private TextView tv_feedback;
	//private TextView tv_questionno;
	
	private AnimatedButtonListener touchListener;
	private MediaPlayer sound;
	private MediaPlayer soundEnglish;
	private MediaPlayer soundFilipino;
	private MediaPlayer soundAnimal;
	
	private enum functionState{
		CHECK, NEXT
	}

	public Animals(){
		layoutID = R.layout.lessonactivity_animals;
	}
	
	@Override
	protected void configureEasyLevel() {
	}

	@Override
	protected void configureMediumLevel() {
	}

	@Override
	protected void configureHardLevel() {
	}
	
	@Override
	protected void initiateViews() {		
		btn_up = new ImageButton[4];
		btn_down = new ImageButton[4];
		btn_cards = new ImageButton[4];
		iv_signs = new ImageView[4];
		
		questionno = 0;
		
		btn_up[0] = (ImageButton)findViewById(R.id.btn_english_up);
		btn_up[1] = (ImageButton)findViewById(R.id.btn_sound_up);
		btn_up[2] = (ImageButton)findViewById(R.id.btn_picture_up);
		btn_up[3] = (ImageButton)findViewById(R.id.btn_filipino_up);
	
		btn_down[0] = (ImageButton)findViewById(R.id.btn_english_down);
		btn_down[1] = (ImageButton)findViewById(R.id.btn_sound_down);
		btn_down[2] = (ImageButton)findViewById(R.id.btn_picture_down);
		btn_down[3] = (ImageButton)findViewById(R.id.btn_filipino_down);
	
		btn_cards[0] = (ImageButton)findViewById(R.id.btn_englishcard);
		btn_cards[1] = (ImageButton)findViewById(R.id.btn_soundcard);
		btn_cards[2] = (ImageButton)findViewById(R.id.btn_picturecard);
		btn_cards[3] = (ImageButton)findViewById(R.id.btn_filipinocard);

		iv_signs[0] = (ImageView)findViewById(R.id.sign1);
		iv_signs[1] = (ImageView)findViewById(R.id.sign2);
		iv_signs[2] = (ImageView)findViewById(R.id.sign3);
		iv_signs[3] = (ImageView)findViewById(R.id.sign4);
		
		btn_function = (ImageButton)findViewById(R.id.btn_function);
		tv_feedback = (TextView)findViewById(R.id.tv_dialog);
		//tv_questionno = (TextView)findViewById(R.id.tv_questionno);
		
		//tv_questionno.setTypeface(SalinlahiFour.getFontPlaytime());
		//((TextView)findViewById(R.id.tv_score)).setTypeface(SalinlahiFour.getFontPlaytime());
		tv_feedback.setTypeface(SalinlahiFour.getFontAndy());
		
		touchListener = new AnimatedButtonListener();
		
		for(int i = 0; i < 4; i++){
			btn_up[i].setOnTouchListener(touchListener);
			btn_down[i].setOnTouchListener(touchListener);
			btn_up[i].setOnClickListener(this);
			btn_down[i].setOnClickListener(this);
			btn_cards[i].setOnClickListener(this);
		}
		//evaluation.setLexiconDir("lexicon_animals.xml");

		btn_function.setOnTouchListener(touchListener);
		btn_function.setOnClickListener(this);
		
		//((RelativeLayout)findViewById(R.id.parent_view)).addView(getPauseButton());
		if(SalinlahiFour.getLoggedInUser().getGender() == "female"){
			((ImageView)findViewById(R.id.img_dialog)).setImageResource(R.drawable.animals_pepaitalking);
		}else{
			((ImageView)findViewById(R.id.img_dialog)).setImageResource(R.drawable.animals_popoitalking);
		}
		
		if(activityLevel == LevelType.EASY){
			setCntQuestions(4);
		}else if(activityLevel == LevelType.MEDIUM){
			setCntQuestions(6);
		}else{
			setCntQuestions(8);
		}
		
		initiateCards();

	}
	
	private void initiateCards(){
		englishCards = new ArrayList<>();
		soundCards = new ArrayList<>();
		pictureCards = new ArrayList<>();
		filipinoCards = new ArrayList<>();
		
		englishCardsOnHand = new ArrayList<>();
		filipinoCardsOnHand = new ArrayList<>();
		soundCardsOnHand = new ArrayList<>();
		pictureCardsOnHand = new ArrayList<>();
		
		Log.d("ANIMALS: size: " + items.size(), "TEST");
		
		for(int i = 0; i < items.size(); i++){
			setCard(items.get(i));
		}
		
		Log.d("ANIMALS: englishCard size: " + englishCards.size(), "TEST");
	}

	@Override
	protected void update() {
		englishCardIndex = 0;
		filipinoCardIndex = 0;
		soundCardIndex = 0;
		pictureCardIndex = 0;
		
		btn_function.setTag(functionState.CHECK);
		
		for(int i = 0; i < btn_up.length; i++){
			btn_up[i].setVisibility(View.VISIBLE);
			btn_down[i].setVisibility(View.VISIBLE);
		}

		btn_up[0].setImageResource(R.drawable.animal_btn_yellow);
		btn_up[1].setImageResource(R.drawable.animal_btn_green);
		btn_up[2].setImageResource(R.drawable.animal_btn_red);
		btn_up[3].setImageResource(R.drawable.animal_btn_blue);

		selectFixed();
		setGame();
		updateQuestionNo();
		setCardOrder();
	}
	
	private void updateQuestionNo(){
		//tv_questionno.setText((questionno + 1) + "");
		//((TextView)findViewById(R.id.tv_score)).setText(" / " + questions.size());
		
		//UPDATE THIS
		tv_feedback.setText("Can you match this? yah?");
		
		String question = "";
		if(fixedIndex == 0){
			question = "What is the animal sound, picture, and the Filipino word of\n" + englishCardsOnHand.get(0).answer + "?";
		}else if(fixedIndex == 1){
			question = "What is the English word, picture, and the Filipino word of\n that animal sound?";	
		}else if(fixedIndex == 2){
			question = "What is the English word, animal sound, and the Filipino word of\n the animal in the picture?";	
		}else if(fixedIndex == 3){
			question = "What is the English word, animal sound, and the picture of\n" + filipinoCardsOnHand.get(0).answer + "?";	
		}
		tv_feedback.setText(question);
	}
	
	private void selectFixed(){
		fixedIndex = new Random().nextInt(8);

		if(fixedIndex > 3)
			fixedIndex = 3;
		
		for(int i = 0; i < iv_signs.length; i++){
			iv_signs[i].setVisibility(View.INVISIBLE);
		}
		
		iv_signs[fixedIndex].setVisibility(View.VISIBLE);
		
		btn_up[fixedIndex].setVisibility(View.INVISIBLE);
		btn_down[fixedIndex].setVisibility(View.INVISIBLE);
		
		answerCard = findAnswerCard(questions.get(questionno).getWord());		
	}
	
	private Card findAnswerCard(String word){
		for(int i = 0; i < englishCards.size(); i ++){
			if(englishCards.get(i).answer.equals(word)){
				return englishCards.get(i);
			}
		}
		return null;
	}
	
	private void setGame(){
		int[] answerIndexes;

		englishCardsOnHand.clear();
		filipinoCardsOnHand.clear();
		soundCardsOnHand.clear();
		pictureCardsOnHand.clear();
		
		btn_function.setImageResource(R.drawable.animal_btn_checkanswer);
		
		if(activityLevel.equals(LevelType.EASY)){
			answerIndexes = new int[4];
			Log.d("ANIMALS: EASY", "TEST");
		}else{
			answerIndexes = new int[5];
		}

		answerIndexes[0] = englishCards.indexOf(answerCard);
		Log.d("ANIMALS: answerIndex: " + answerIndexes[0], "TEST");
		for(int i = 1; i < answerIndexes.length; i++){
			int random;
			do{
				random = new Random().nextInt(englishCards.size()+1);
				random %= items.size();
			}while(doesContain(random, answerIndexes, i));
			Log.d("ANIMALS: index " + i +": got: " + random, "TEST");
			answerIndexes[i] = random;
		}
		
		for(int i = 0; i < answerIndexes.length; i++){
			englishCardsOnHand.add(englishCards.get(answerIndexes[i]));
			filipinoCardsOnHand.add(filipinoCards.get(answerIndexes[i]));
			soundCardsOnHand.add(soundCards.get(answerIndexes[i]));
			pictureCardsOnHand.add(pictureCards.get(answerIndexes[i]));
		}
		
		
	}
	
	private boolean doesContain(int x, int[] arrays, int limit){
		for(int i = 0; i < limit; i++){
			if(x == arrays[i]){
				return true;
			}
		}
		return false;
	}
	
	private void setCardOrder(){
		Random rand = new Random();
		englishCardIndex = rand.nextInt(englishCardsOnHand.size());
		soundCardIndex = rand.nextInt(soundCardsOnHand.size());
		pictureCardIndex = rand.nextInt(pictureCardsOnHand.size());
		filipinoCardIndex = rand.nextInt(filipinoCardsOnHand.size());
		
		btn_cards[0].setImageResource(englishCardsOnHand.get(englishCardIndex).drawableResID);
		btn_cards[1].setImageResource(soundCardsOnHand.get(soundCardIndex).drawableResID);
		btn_cards[2].setImageResource(pictureCardsOnHand.get(pictureCardIndex).drawableResID);
		btn_cards[3].setImageResource(filipinoCardsOnHand.get(filipinoCardIndex).drawableResID);
		
		switch(fixedIndex){
		case 0:
			englishCardIndex = 0;
			btn_cards[fixedIndex].setImageResource(englishCardsOnHand.get(0).drawableResID);
			break;
		case 1:
			soundCardIndex = 0;
			btn_cards[fixedIndex].setImageResource(soundCardsOnHand.get(0).drawableResID);
			break;
		case 2:
			pictureCardIndex = 0;
			btn_cards[fixedIndex].setImageResource(pictureCardsOnHand.get(0).drawableResID);
			break;
		case 3:
			filipinoCardIndex = 0;
			btn_cards[fixedIndex].setImageResource(filipinoCardsOnHand.get(0).drawableResID);
			break;
		}
	}

	protected boolean checkAnswer(String answer) {
		int cnt_wrong = 0;
		String encouragement = "";
		String feedback = "Try to match the ";
		
		if(cnt_wrong < 3){
			encouragement = "Almost there!\n";
		}else{
			encouragement = "Oops. Try again!\n";
		}
		
		Log.d("English: " + englishCards.get(englishCardIndex).itemno 
				+ " Sound: " + soundCards.get(soundCardIndex).itemno
				+ " Picture: " + pictureCards.get(pictureCardIndex).itemno
				+ " Filipino: " + filipinoCards.get(filipinoCardIndex).itemno, "TEST");		
		
		if(fixedIndex == 0){
			if(soundCardsOnHand.get(soundCardIndex).itemno == answerCard.itemno)
				markCorrect(1);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[1]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "animal sound";
			}
			if(pictureCardsOnHand.get(pictureCardIndex).itemno == answerCard.itemno)
				markCorrect(2);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[2]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "picture";
			}
			if(btn_down[3].getVisibility() == View.VISIBLE){ //Evaluate only if it is not yet checked
				if(filipinoCardsOnHand.get(filipinoCardIndex).itemno == answerCard.itemno){
					markCorrect(3);
					evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, filipinoCardsOnHand.get(filipinoCardIndex).answer, UserID);
				}
				else{
					YoYo.with(Techniques.Swing).playOn(btn_cards[3]);
					if(cnt_wrong > 0)
						feedback += ", ";
					cnt_wrong++;
					feedback += "Filipino word";
					evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, "", UserID);
				}
			}
		}else if(fixedIndex == 1){
			if(englishCardsOnHand.get(englishCardIndex).itemno == answerCard.itemno)
				markCorrect(0);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[0]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "English word";
			}
			if(pictureCardsOnHand.get(pictureCardIndex).itemno == answerCard.itemno)
				markCorrect(2);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[2]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "picture";
			}
			if(btn_down[3].getVisibility() == View.VISIBLE){	//Evaluate only if it is not yet checked
				if(filipinoCardsOnHand.get(filipinoCardIndex).itemno == answerCard.itemno){
					markCorrect(3);
					evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, filipinoCardsOnHand.get(filipinoCardIndex).answer, UserID);
				}
				else{
					YoYo.with(Techniques.Swing).playOn(btn_cards[3]);
					if(cnt_wrong > 0)
						feedback += ", ";
					cnt_wrong++;
					feedback += "Filipino Word";
					evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, "", UserID);
				}
			}
		}else if(fixedIndex == 2){
			if(englishCardsOnHand.get(englishCardIndex).itemno == answerCard.itemno)
				markCorrect(0);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[0]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "English word";
			}
			if(soundCardsOnHand.get(soundCardIndex).itemno == answerCard.itemno)
				markCorrect(1);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[1]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "animal sound";
			}
			if(btn_down[3].getVisibility() == View.VISIBLE){	//Evaluate only if it is not yet checked
				if(filipinoCardsOnHand.get(filipinoCardIndex).itemno == answerCard.itemno){
					markCorrect(3);
					evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, filipinoCardsOnHand.get(filipinoCardIndex).answer, UserID);
				}
				else{
					YoYo.with(Techniques.Swing).playOn(btn_cards[3]);
					if(cnt_wrong > 0)
						feedback += ", ";
					cnt_wrong++;
					feedback += "Filipino word";
					evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, "", UserID);
				}
			}
		}else if(fixedIndex == 3){
			if(englishCardsOnHand.get(englishCardIndex).itemno == answerCard.itemno)
				markCorrect(0);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[0]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "English word";
			}
			if(soundCardsOnHand.get(soundCardIndex).itemno == answerCard.itemno)
				markCorrect(1);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[1]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "animal sound";
			}
			if(pictureCardsOnHand.get(pictureCardIndex).itemno == answerCard.itemno)
				markCorrect(2);
			else{
				YoYo.with(Techniques.Swing).playOn(btn_cards[2]);
				if(cnt_wrong > 0)
					feedback += ", ";
				cnt_wrong++;
				feedback += "picture";
			}
			if(cnt_wrong == 0){
				evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, filipinoCardsOnHand.get(filipinoCardIndex).answer, UserID);
//				feedback = "MAGALING! HABA NG HERR";
				feedback = evaluation.getImmediateFeedback(questions.get(questionno).getQ_num(), questions.get(questionno).getWord(), lesson.getLessonNumber());
				tv_feedback.setText(encouragement + feedback);
				return true;
			}else{
				evaluation.evaluateAnswer(filipinoCardsOnHand.get(filipinoCardIndex).answer, "", UserID);
				if(feedback.contains("English word")){
					feedback = evaluation.getImmediateFeedback(questions.get(questionno).getQ_num(), englishCardsOnHand.get(englishCardIndex).answer, lesson.getLessonNumber());
				}else if(feedback.contains("picture")){
					feedback = evaluation.getImmediateFeedback(questions.get(questionno).getQ_num(), pictureCardsOnHand.get(pictureCardIndex).answer, lesson.getLessonNumber());
				}else{
					feedback = evaluation.getImmediateFeedback(questions.get(questionno).getQ_num(), soundCardsOnHand.get(soundCardIndex).answer, lesson.getLessonNumber());
				}
//				feedback = "BOBSKI!!";
				tv_feedback.setText(encouragement + feedback);
				return false;
			}
			//UPDATE THIS
//			feedback = evaluation.getImmediateFeedback(questions.get(itemno).getQ_num(), answer, lesson.getLessonNumber());			
		}			
		
		if(fixedIndex != 3){
			feedback += " again.";
			feedback = "Try to match the other cards again!";
			
			//UPDATE THIS
			if(cnt_wrong == 0){
	//			feedback = "MAGALING! HABA NG HERR";
				feedback = "You got it right!";
				tv_feedback.setText(feedback);
				return true;
			}else{
	//			feedback = "BOBSKI!!";
				tv_feedback.setText(encouragement + feedback);
				return false;
			}	
		}
		return false;
	}
	
	private void markCorrect(int index){
		btn_up[index].setImageResource(R.drawable.animal_correct);
		btn_down[index].setVisibility(View.INVISIBLE);
	}
	
	private class Card{
		public String answer;
		public int drawableResID;
		public int soundResID;
		public int itemno;
		
		public Card(String answer, int drawableResID, int soundResID, int itemno){
			this.answer = answer;
			this.drawableResID = drawableResID;
			this.soundResID = soundResID;
			this.itemno = itemno;
		}		
	}
	
	private void setCard(Item item){
		int itemno;
		switch(item.getWord()){
		case "Aso":
			itemno = 0;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_dog, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_dog, R.raw.animals_sound_dog, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_aso, item.getVoiceFilID(), itemno));
			break;
		case "Pusa":
			itemno = 1;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_cat, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_cat, R.raw.animals_sound_meow, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_pusa, item.getVoiceFilID(),itemno));
			break;
		case "Manok":
			itemno = 2;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_chicken, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_chicken, R.raw.animals_sound_chicken, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_manok, item.getVoiceFilID(),itemno));
			break;
		case "Kalabaw":
			itemno = 3;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_carabao, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_carabao, R.raw.animals_sound_carabao, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_kalabaw, item.getVoiceFilID(),itemno));
			break;
		case "Palaka":
			itemno = 4;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_frog, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_frog, R.raw.animals_sound_frog, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_palka, item.getVoiceFilID(),itemno));
			break;
		case "Unggoy":
			itemno = 5;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_monkey, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_monkey, R.raw.animals_sound_monkey, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_unggoy, item.getVoiceFilID(),itemno));
			break;
		case "Ibon":
			itemno = 6;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_bird, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_bird, R.raw.animals_sound_bird, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_ibon, item.getVoiceFilID(),itemno));
			break;
		case "Daga":
			itemno = 7;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_mouse, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_rat, R.raw.animals_sound_mouse, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_daga, item.getVoiceFilID(),itemno));
			break;
		case "Elefante":
			itemno = 8;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_elephant, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_elephant, R.raw.animals_sound_elephant, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_elefante, item.getVoiceFilID(),itemno));
			break;
		case "Oso":
			itemno = 9;
			englishCards.add(new Card(item.getWord(), R.drawable.animals_eng_bear, item.getVoiceEngID(), itemno));
			soundCards.add(new Card(item.getWord(), R.drawable.animals_sound_bear, R.raw.animals_sound_bear, itemno));
			pictureCards.add(new Card(item.getWord(), item.getImageID(), 0, itemno));
			filipinoCards.add(new Card(item.getWord(), R.drawable.animals_fil_oso, item.getVoiceFilID(),itemno));
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btn_english_up:
				if((englishCardIndex+1) > englishCardsOnHand.size()-1)
					englishCardIndex = 0;
				else
					englishCardIndex += 1;
				btn_cards[0].setImageResource(englishCardsOnHand.get(englishCardIndex).drawableResID);

				soundEnglish = MediaPlayer.create(this,englishCardsOnHand.get(englishCardIndex).soundResID);
				soundEnglish.start();
				break;
			case R.id.btn_english_down:
				if((englishCardIndex-1) < 0)
					englishCardIndex = englishCardsOnHand.size()-1;
				else
					englishCardIndex -= 1;
				btn_cards[0].setImageResource(englishCardsOnHand.get(englishCardIndex).drawableResID);
				soundEnglish = MediaPlayer.create(this,englishCardsOnHand.get(englishCardIndex).soundResID);
				soundEnglish.start();
				break;
			case R.id.btn_filipino_up:
				if((filipinoCardIndex+1) > filipinoCardsOnHand.size()-1)
					filipinoCardIndex = 0;
				else
					filipinoCardIndex += 1;
				btn_cards[3].setImageResource(filipinoCardsOnHand.get(filipinoCardIndex).drawableResID);
				soundFilipino = MediaPlayer.create(this,filipinoCardsOnHand.get(filipinoCardIndex).soundResID);
				soundFilipino.start();
				break;
			case R.id.btn_filipino_down:
				//
				if((filipinoCardIndex-1) < 0)
					filipinoCardIndex = filipinoCardsOnHand.size()-1;
				else
					filipinoCardIndex -= 1;
				btn_cards[3].setImageResource(filipinoCardsOnHand.get(filipinoCardIndex).drawableResID);
				soundFilipino = MediaPlayer.create(this,filipinoCardsOnHand.get(filipinoCardIndex).soundResID);
				soundFilipino.start();
				break;
			case R.id.btn_sound_up:
				if((soundCardIndex+1) > soundCardsOnHand.size()-1)
					soundCardIndex = 0;
				else
					soundCardIndex += 1;
				btn_cards[1].setImageResource(soundCardsOnHand.get(soundCardIndex).drawableResID);
				soundAnimal = MediaPlayer.create(this,soundCardsOnHand.get(soundCardIndex).soundResID);
				soundAnimal.start();
				break;
			case R.id.btn_sound_down:
				if((soundCardIndex-1) < 0)
					soundCardIndex = soundCardsOnHand.size()-1;
				else
					soundCardIndex -= 1;
				btn_cards[1].setImageResource(soundCardsOnHand.get(soundCardIndex).drawableResID);
				soundAnimal = MediaPlayer.create(this,soundCardsOnHand.get(soundCardIndex).soundResID);
				soundAnimal.start();
				break;
			case R.id.btn_picture_up:
				if((pictureCardIndex+1) > pictureCardsOnHand.size()-1)
					pictureCardIndex = 0;
				else
					pictureCardIndex += 1;
				btn_cards[2].setImageResource(pictureCardsOnHand.get(pictureCardIndex).drawableResID);
				break;
			case R.id.btn_picture_down:
				if((pictureCardIndex-1) < 0)
					pictureCardIndex = pictureCardsOnHand.size()-1;
				else
					pictureCardIndex -= 1;
				btn_cards[2].setImageResource(pictureCardsOnHand.get(pictureCardIndex).drawableResID);
				break;
				
			//PICTURE CARDS
			case R.id.btn_englishcard:
				soundEnglish = MediaPlayer.create(this,englishCardsOnHand.get(englishCardIndex).soundResID);
				soundEnglish.start();
				break;
			case R.id.btn_picturecard:
//				sound = MediaPlayer.create(this,pictureCards.get(pictureCardIndex).soundResID);
//				sound.start();
				break;
			case R.id.btn_filipinocard:
				soundFilipino = MediaPlayer.create(this,filipinoCardsOnHand.get(filipinoCardIndex).soundResID);
				soundFilipino.start();
				break;
			case R.id.btn_soundcard:
				soundAnimal = MediaPlayer.create(this,soundCardsOnHand.get(soundCardIndex).soundResID);
				soundAnimal.start();
				break;
			//BTN_FUNCTION
			case R.id.btn_function:
				if(v.getTag().equals(functionState.CHECK)){
					if(checkAnswer("")){
						v.setTag(functionState.NEXT);
						((ImageButton)v).setImageResource(R.drawable.animal_btn_nextanimal);
						sound = MediaPlayer.create(this, R.raw.sfx_correct);
						sound.start();
					}else{
						
					}
				}else{
					if(questionno++ < questions.size()-1)
						update();
					else{
						evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), UserID);
						showReportCard(this);
					}
				}
				break;
		}
	}

	@Override
	protected void ifAnswerIsCorrect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ifAnswerIsWrong() {
		// TODO Auto-generated method stub
		
	}

}
