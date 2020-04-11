package com.btitsolutions.alphabetquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    List<String> trainingList = new ArrayList<String>();
    String[] currentRowItems = new String[]{};

    Button btnFirst, btnSecond, btnThird, btnFourth,
            btnFifth, btnSixth, btnSeventh, btnEight, btnNinth;

    Button btnPrevious, btnNext;

    int currentRowIndex = 0;
    int currentItemID = 1;
    int errorCounter = 0;

    SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        soundPlayer = new SoundPlayer(this);

        btnFirst = (Button)findViewById(R.id.btnFirst);
        btnSecond = (Button)findViewById(R.id.btnSecond);
        btnThird = (Button)findViewById(R.id.btnThird);
        btnFourth = (Button)findViewById(R.id.btnFourth);
        btnFifth = (Button)findViewById(R.id.btnFifth);
        btnSixth = (Button)findViewById(R.id.btnSixth);
        btnSeventh = (Button)findViewById(R.id.btnSeventh);
        btnEight = (Button)findViewById(R.id.btnEight);
        btnNinth = (Button)findViewById(R.id.btnNinth);

        btnPrevious = (Button)findViewById(R.id.btnPrevious);
        btnNext = (Button)findViewById(R.id.btnNext);

        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
        btnThird.setOnClickListener(this);
        btnFourth.setOnClickListener(this);
        btnFifth.setOnClickListener(this);
        btnSixth.setOnClickListener(this);
        btnSeventh.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNinth.setOnClickListener(this);

        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        String trainingType = getIntent().getStringExtra("TrainingType");
        if(trainingType.equals("AmharicAlphabet")){
            PrepareAmharicAlphabets();
        }
        else if(trainingType.equals("AmharicNumber")){
            PrepareAmharicNumbers();
        }
        else if(trainingType.equals("EnglishAlphabet")){
            PrepareEnglishAlphabets();
        }
        else if(trainingType.equals("EnglishNumber")){
            PrepareEnglishNumbers();
        }

        SharedPreferences Game_Setting = getSharedPreferences("Game_Setting", Context.MODE_PRIVATE);
        String level = Game_Setting.getString("SelectedLevel", "Beginner");
        if(level.equals("Beginner")){
            LoadSingleRow();
        }
        else if(level.equals("Advanced")){
            Collections.shuffle(trainingList);

            LoadSingleRow();
        }
    }

    private void PrepareAmharicAlphabets(){
        trainingList.add("ሀ/1,ሁ/2,ሂ/3,ሃ/4,ሄ/5,ህ/6,ሆ/7");
        trainingList.add("ለ/1,ሉ/2,ሊ/3,ላ/4,ሌ/5,ል/6,ሎ/7");
        trainingList.add("ሐ/1,ሑ/2,ሒ/3,ሓ/4,ሔ/5,ሕ/6,ሖ/7");
        trainingList.add("መ/1,ሙ/2,ሚ/3,ማ/4,ሜ/5,ም/6,ሞ/7");
        trainingList.add("ሠ/1,ሡ/2,ሢ/3,ሣ/4,ሤ/5,ሥ/6,ሦ/7");
        trainingList.add("ረ/1,ሩ/2,ሪ/3,ራ/4,ሬ/5,ር/6,ሮ/7");
        trainingList.add("ሰ/1,ሱ/2,ሲ/3,ሳ/4,ሴ/5,ስ/6,ሶ/7");
        trainingList.add("ሸ/1,ሹ/2,ሺ/3,ሻ/4,ሼ/5,ሽ/6,ሾ/7");
        trainingList.add("ቀ/1,ቁ/2,ቂ/3,ቃ/4,ቄ/5,ቅ/6,ቆ/7");
        trainingList.add("በ/1,ቡ/2,ቢ/3,ባ/4,ቤ/5,ብ/6,ቦ/7");
        trainingList.add("ቨ/1,ቩ/2,ቪ/3,ቫ/4,ቬ/5,ቭ/6,ቮ/7");
        trainingList.add("ተ/1,ቱ/2,ቲ/3,ታ/4,ቴ/5,ት/6,ቶ/7");
        trainingList.add("ቸ/1,ቹ/2,ቺ/3,ቻ/4,ቼ/5,ች/6,ቾ/7");
        trainingList.add("ኅ/1,ኁ/2,ኂ/3,ኃ/4,ኄ/5,ኅ/6,ኆ/7");
        trainingList.add("ነ/1,ኑ/2,ኒ/3,ና/4,ኔ/5,ን/6,ኖ/7");
        trainingList.add("ኘ/1,ኙ/2,ኚ/3,ኛ/4,ኜ/5,ኝ/6,ኞ/7");
        trainingList.add("አ/1,ኡ/2,ኢ/3,ኣ/4,ኤ/5,እ/6,ኦ/7");
        trainingList.add("ከ/1,ኩ/2,ኪ/3,ካ/4,ኬ/5,ክ/6,ኮ/7");
        trainingList.add("ኽ/1,ኹ/2,ኺ/3,ኻ/4,ኼ/5,ኽ/6,ኾ/7");
        trainingList.add("ወ/1,ዉ/2,ዊ/3,ዋ/4,ዌ/5,ው/6,ዎ/7");
        trainingList.add("ዕ/1,ዑ/2,ዒ/3,ዓ/4,ዔ/5,ዕ/6,ዖ/7");
        trainingList.add("ዘ/1,ዙ/2,ዚ/3,ዛ/4,ዜ/5,ዝ/6,ዞ/7");
        trainingList.add("ዥ/1,ዡ/2,ዢ/3,ዣ/4,ዤ/5,ዥ/6,ዦ/7");
        trainingList.add("የ/1,ዩ/2,ዪ/3,ያ/4,ዬ/5,ይ/6,ዮ/7");
        trainingList.add("ደ/1,ዱ/2,ዲ/3,ዳ/4,ዴ/5,ድ/6,ዶ/7");
        trainingList.add("ጀ/1,ጁ/2,ጂ/3,ጃ/4,ጄ/5,ጅ/6,ጆ/7");
        trainingList.add("ገ/1,ጉ/2,ጊ/3,ጋ/4,ጌ/5,ግ/6,ጎ/7");
        trainingList.add("ጠ/1,ጡ/2,ጢ/3,ጣ/4,ጤ/5,ጥ/6,ጦ/7");
        trainingList.add("ጨ/1,ጩ/2,ጪ/3,ጫ/4,ጬ/5,ጭ/6,ጮ/7");
        trainingList.add("ጰ/1,ጱ/2,ጲ/3,ጳ/4,ጴ/5,ጵ/6,ጶ/7");
        trainingList.add("ጸ/1,ጹ/2,ጺ/3,ጻ/4,ጼ/5,ጽ/6,ጾ/7");
        trainingList.add("ፀ/1,ፁ/2,ፂ/3,ፃ/4,ፄ/5,ፅ/6,ፆ/7");
        trainingList.add("ፈ/1,ፉ/2,ፊ/3,ፋ/4,ፌ/5,ፍ/6,ፎ/7");
        trainingList.add("ፐ/1,ፑ/2,ፒ/3,ፓ/4,ፔ/5,ፕ/6,ፖ/7");
    }

    private void PrepareAmharicNumbers(){
        trainingList.add("፩/1,፪/2,፫/3,፬/4,፭/5");
        trainingList.add("፮/1,፯/2,፰/3,፱/4,፲/5");
        trainingList.add("፲፩/1,፲፪/2,፲፫/3,፲፬/4,፲፭/5");
        trainingList.add("፲፮/1,፲፯/2,፲፰/3,፲፱/4,፳/5");
        trainingList.add("፴/1,፵/2,፶/3,፷/4,፸/5");
        trainingList.add("፹/1/፺/2,፻/3,፼/4");
    }

    private void PrepareEnglishAlphabets(){
        trainingList.add("A/1,B/2,C/3,D/4,E/5,F/6,G/7,H/8,I/9");
        trainingList.add("J/1,K/2,L/3,M/4,N/5,O/6,P/7,Q/8,R/9");
        trainingList.add("S/1,T/2,U/3,V/4,W/5,X/6,Y/7,Z/8");
    }

    private void PrepareEnglishNumbers(){
        trainingList.add("0/1,1/2,2/3,3/4,4/5,5/6,6/7,7/8,8/9");
        trainingList.add("9/1,10/2,11/3,12/4,13/5,14/6,15/7,16/8,17/9");
        trainingList.add("18/1,19/2,20/3,21/4,22/5,23/6,24/7,25/8,26/9");
        trainingList.add("27/1,28/2,29/3,30/4,31/5,32/6,33/7,34/8,35/9");
        trainingList.add("36/1,37/2,38/3,39/4,40/5,41/6,42/7,43/8,44/9");
        trainingList.add("45/1,46/2,47/3,48/4,49/5,50/6,51/7,52/8,53/9");
        trainingList.add("54/1,55/2,56/3,57/4,58/5,59/6,60/7,61/8,62/9");
        trainingList.add("63/1,64/2,65/3,66/4,67/5,68/6,69/7,70/8,71/9");
        trainingList.add("72/1,73/2,74/3,75/4,76/5,77/6,78/7,79/8,80/9");
        trainingList.add("81/1,82/2,83/3,84/4,85/5,86/6,87/7,88/8,89/9");
        trainingList.add("90/1,91/2,92/3,93/4,94/5,95/6,96/7,97/8,98/9");

        trainingList.add("99/1,100/2,1000/3,10000/4,100000/5,1000000/6,10000000/7,100000000/8,1000000000/9");
    }

    private void LoadSingleRow(){
        currentRowItems = trainingList.get(currentRowIndex).split(",");

        List<String> currentArray = new ArrayList<>();
        for(int i = 0; i < currentRowItems.length; i++){
            currentArray.add(currentRowItems[i]);
        }

        Collections.shuffle(currentArray);

        btnFirst.setText(currentArray.get(0).split("/")[0]);
        btnFirst.setTag(currentArray.get(0).split("/")[1]);
        btnFirst.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        btnFirst.setEnabled(true);

        btnSecond.setText(currentArray.get(1).split("/")[0]);
        btnSecond.setTag(currentArray.get(1).split("/")[1]);
        btnSecond.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        btnSecond.setEnabled(true);

        btnThird.setText(currentArray.get(2).split("/")[0]);
        btnThird.setTag(currentArray.get(2).split("/")[1]);
        btnThird.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        btnThird.setEnabled(true);

        btnFourth.setText(currentArray.get(3).split("/")[0]);
        btnFourth.setTag(currentArray.get(3).split("/")[1]);
        btnFourth.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        btnFourth.setEnabled(true);

        btnFifth.setText(currentArray.get(4).split("/")[0]);
        btnFifth.setTag(currentArray.get(4).split("/")[1]);
        btnFifth.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        btnFifth.setEnabled(true);

        if(currentRowItems.length > 5){
            btnSixth.setText(currentArray.get(5).split("/")[0]);
            btnSixth.setTag(currentArray.get(5).split("/")[1]);
            btnSixth.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
            btnSixth.setEnabled(true);
        }
        else{
            btnSixth.setText("");
            btnSixth.setEnabled(false);
            btnSixth.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        }

        if(currentRowItems.length > 6){
            btnSeventh.setText(currentArray.get(6).split("/")[0]);
            btnSeventh.setTag(currentArray.get(6).split("/")[1]);
            btnSeventh.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
            btnSeventh.setEnabled(true);
        }
        else{
            btnSeventh.setText("");
            btnSeventh.setEnabled(false);
            btnSeventh.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        }

        if(currentRowItems.length > 7){
            btnEight.setText(currentArray.get(7).split("/")[0]);
            btnEight.setTag(currentArray.get(7).split("/")[1]);
            btnEight.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
            btnEight.setEnabled(true);
        }
        else{
            btnEight.setText("");
            btnEight.setEnabled(false);
            btnEight.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        }

        if(currentRowItems.length > 8){
            btnNinth.setText(currentArray.get(8).split("/")[0]);
            btnNinth.setTag(currentArray.get(8).split("/")[1]);
            btnNinth.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
            btnNinth.setEnabled(true);
        }
        else{
            btnNinth.setText("");
            btnNinth.setEnabled(false);
            btnNinth.setCompoundDrawablesWithIntrinsicBounds(0 , 0, 0, 0);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnNext.getId()){
            if((currentItemID - 1) == currentRowItems.length){
                if(currentRowIndex < (trainingList.size() - 1)){
                    currentItemID = 1;
                    currentRowIndex += 1;

                    LoadSingleRow();
                }
            }
            else{
                //you have to finish this level first
                Toast.makeText(this, "You should complete this level first.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId() == btnPrevious.getId()){
            if(currentRowIndex > 0){
                currentItemID = 1;
                currentRowIndex -= 1;

                LoadSingleRow();
            }
        }
        else{
            if(Integer.valueOf(view.getTag().toString()) == (currentItemID)){
                currentItemID += 1;
                errorCounter = 0;

                //play correct sound
                //soundPlayer.PlayCorrectSound();

                //android:drawableStart="@android:drawable/checkbox_on_background"
                //((Button)view).setCompoundDrawablesWithIntrinsicBounds(R.drawable.correct_mark , 0, 0, 0);
                ((Button)view).setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.checkbox_on_background , 0, 0, 0);
                ((Button)view).setEnabled(false);

                if((currentItemID - 1) == currentRowItems.length){
                    //play level completed sound
                    Toast.makeText(this, "Level completed !", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                //play wrong sound
                soundPlayer.PlayInCorrectSound();
                errorCounter += 1;

                if (errorCounter == 3){
                    currentItemID = 1;
                    LoadSingleRow();
                }
            }
        }
    }
}
