package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

     Button DiaryEntries;
     Button AddEntry;
     TextView testCheck;
     Intent DiaryList;
     Intent AddDiaryToList;

     MyDbAdapter helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiaryEntries = (Button) findViewById(R.id.DiaryEntries);
        AddEntry = (Button)  findViewById(R.id.AddEntry);
        helper = new MyDbAdapter(this);

        loadFromDBToMemory();

        /*
        testCheck = (TextView) findViewById(R.id.textView);
        String test = "";
        for (Diary diary : Diary.diaryArrayList) {

            test = test + diary.getBookTitle();
        }
        testCheck.setText(test);

         */


        DiaryEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DiaryList = new Intent(getApplicationContext(), DiaryEntries.class);
                startActivity(DiaryList);
            }
        });

        AddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AddDiaryToList = new Intent(getApplicationContext(), AddDiaryEntry.class);
                startActivity(AddDiaryToList);
            }
        });

    }

    private void loadFromDBToMemory() {
        helper.populateDiaryEntries();

    }
}