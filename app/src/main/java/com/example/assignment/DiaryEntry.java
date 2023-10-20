package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DiaryEntry extends AppCompatActivity {

    TextView idTextView;
    EditText bookTitle, date, rating, pagesRead;
    Button saveButton, deleteButton, emailButton, backButton;
    MyDbAdapter helper;
    Intent DiaryEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);
        helper = new MyDbAdapter(this);

        String id = getIntent().getStringExtra("id");
        String BookTitle = getIntent().getStringExtra("bookTitle");
        String Date = getIntent().getStringExtra("dateTime");
        String Rating = getIntent().getStringExtra("rating");
        String PagesRead = getIntent().getStringExtra("pagesRead");


        idTextView = (TextView) findViewById(R.id.idView);
        idTextView.setText(id);


        bookTitle = (EditText) findViewById(R.id.editBookTitle);
        date = (EditText) findViewById(R.id.editDate);
        rating = (EditText) findViewById(R.id.editRating);
        pagesRead = (EditText) findViewById(R.id.editPagesRead);

        bookTitle.setText(BookTitle);
        date.setText(Date);
        rating.setText(Rating);
        pagesRead.setText(PagesRead);


        saveButton = (Button) findViewById(R.id.SaveBtn);
        deleteButton = (Button) findViewById(R.id.deleteBtn);
        backButton = (Button) findViewById(R.id.BackBtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                saveEntry(v);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEntry(view);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaryEntries = new Intent(getApplicationContext(), DiaryEntries.class);
                startActivity(DiaryEntries);
            }
        });

        emailButton = (Button) findViewById(R.id.emailBtn);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    public void sendEmail() {

        String subject = "Diary Entry";
        String body = "Book title: "+bookTitle.getText().toString()+"\n";
        body += "Date/Time read: "+date.getText().toString()+"\n";
        body += "Rating given: "+rating.getText().toString()+"\n";
        body += "Pages read: "+pagesRead.getText().toString()+"\n";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.setType("plain/text");
        startActivity(Intent.createChooser(emailIntent, "Send Mail Using :"));
    }

    public void saveEntry(View view) {

        String id = idTextView.getText().toString();
        String BookTitle = bookTitle.getText().toString();
        String Date = date.getText().toString();
        String Rating = rating.getText().toString();
        String PagesRead = pagesRead.getText().toString();

        if (BookTitle.isEmpty() || Date.isEmpty() || Rating.isEmpty() || PagesRead.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            helper.updateData(id,BookTitle, Date, Rating, PagesRead);
            Toast.makeText(getApplicationContext(), "Diary Entry Saved", Toast.LENGTH_SHORT).show();
            DiaryEntries = new Intent(getApplicationContext(), DiaryEntries.class);
            startActivity(DiaryEntries);
        }



    }

    public void deleteEntry(View view) {
        String id = idTextView.getText().toString();
        helper.deleteData(id);
        Toast.makeText(getApplicationContext(), "Diary Entry Deleted", Toast.LENGTH_SHORT).show();
        DiaryEntries = new Intent(getApplicationContext(), DiaryEntries.class);
        startActivity(DiaryEntries);
    }

}

