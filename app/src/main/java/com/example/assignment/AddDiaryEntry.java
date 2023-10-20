package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddDiaryEntry extends AppCompatActivity {

    private EditText editBookTitle, editDate, editRating, editPagesRead;
    Button submitBtn, backButton;
    MyDbAdapter helper;
    Intent DiaryEntries;
    Intent Homepage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary_entry);

        editBookTitle = findViewById(R.id.BookTitle);
        editDate = findViewById(R.id.Date);
        editRating = findViewById(R.id.BookRating);
        editPagesRead = findViewById(R.id.PagesRead);
        submitBtn = (Button) findViewById(R.id.submit);
        backButton = (Button) findViewById(R.id.BackBtn);

        helper = new MyDbAdapter(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                saveEntry(v);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Homepage = new Intent(getApplicationContext(), Homepage.class);
                startActivity(Homepage);
            }
        });

    }


    public void saveEntry(View view) {

        String BookTitle = editBookTitle.getText().toString();
        String date = editDate.getText().toString();
        String Rating = editRating.getText().toString();
        String PagesRead = editPagesRead.getText().toString();

        if (BookTitle.isEmpty() || date.isEmpty() || Rating.isEmpty() || PagesRead.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            helper.insertData(BookTitle, date, Rating, PagesRead);

            DiaryEntries = new Intent(getApplicationContext(), DiaryEntries.class);
            startActivity(DiaryEntries);
        }


    }


}