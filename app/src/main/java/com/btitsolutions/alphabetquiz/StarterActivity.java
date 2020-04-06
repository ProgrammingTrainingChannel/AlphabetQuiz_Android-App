package com.btitsolutions.alphabetquiz;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.gms.ads.MobileAds;

public class StarterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAmharicAlphabet, btnAmharicNumbers, btnEnglishAlphabet, btnEnglishNumber;
    Button btnRateUs, btnSetting;

    RadioButton rdbtnBeginner, rdbtnAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        MobileAds.initialize(this, "ca-app-pub-8168171128315421~7023220617");

        btnRateUs = (Button)findViewById(R.id.btnRateUs);
        btnSetting = (Button)findViewById(R.id.btnSetting);

        btnAmharicAlphabet = (Button)findViewById(R.id.btnAmharicAlphabet);
        btnAmharicNumbers = (Button)findViewById(R.id.btnAmharicNumbers);
        btnEnglishAlphabet = (Button)findViewById(R.id.btnEnglishAlphabet);
        btnEnglishNumber = (Button)findViewById(R.id.btnEnglishNumber);

        btnRateUs.setOnClickListener(this);
        btnSetting.setOnClickListener(this);

        btnAmharicAlphabet.setOnClickListener(this);
        btnAmharicNumbers.setOnClickListener(this);
        btnEnglishAlphabet.setOnClickListener(this);
        btnEnglishNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnRateUs.getId()){
            SharedPreferences prefs = this.getSharedPreferences("apprater", 0);
            if (prefs.getBoolean("dontshowagain", false)) { return ; }
            SharedPreferences.Editor editor = prefs.edit();

            RateThisApp.showRateDialog(this, editor);
        }
        else if(view.getId() == btnSetting.getId()){
            OpenSettingDialog(this);
        }
        else if(view.getId() == btnAmharicAlphabet.getId()){
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("TrainingType", "AmharicAlphabet");
            startActivity(intent);
        }
        else if(view.getId() == btnAmharicNumbers.getId()){
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("TrainingType", "AmharicNumber");
            startActivity(intent);
        }
        else if(view.getId() == btnEnglishAlphabet.getId()){
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("TrainingType", "EnglishAlphabet");
            startActivity(intent);
        }
        else if(view.getId() == btnEnglishNumber.getId()){
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("TrainingType", "EnglishNumber");
            startActivity(intent);
        }
    }

    public void OpenSettingDialog(Context context) {
        final Dialog dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.setting_dialog);
        dialog.setTitle("Game Setting");
        dialog.setCancelable(true);
        dialog.show();

        rdbtnBeginner = (RadioButton)dialog.findViewById(R.id.rdbtnBeginner);
        rdbtnAdvanced = (RadioButton)dialog.findViewById(R.id.rdbtnAdvanced);

        Button btnDialogSave = (Button) dialog.findViewById(R.id.btnDialogSave);
        btnDialogSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences Game_Setting = getSharedPreferences("Game_Setting", Context.MODE_PRIVATE);
                String SelectedLevel = "";

                if(rdbtnBeginner.isChecked() == true){
                    SelectedLevel = "Beginner";
                }
                else if(rdbtnAdvanced.isChecked() == true){
                    SelectedLevel = "Advanced";
                }

                SharedPreferences.Editor editor = Game_Setting.edit();
                editor.putString("SelectedLevel", SelectedLevel);

                editor.commit();

                dialog.dismiss();
            }
        });
    }
}
