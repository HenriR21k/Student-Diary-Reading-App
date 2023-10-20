package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DiaryEntries extends AppCompatActivity {

    private ListView ViewEntry;
    TextView bookTitle, date, rating, pagesRead;
    MyDbAdapter helper;
    Intent Homepage;
    Button backButton, searchButton;
    EditText searchEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entries);

        backButton = (Button) findViewById(R.id.BackBtn);

        populateUsersList();
        ViewEntry = (ListView) findViewById(R.id.lvEntries);

        ViewEntry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Diary selectedEntry = (Diary) ViewEntry.getItemAtPosition(position);
                Intent DiaryEntry = new Intent(getApplicationContext(), DiaryEntry.class);

                System.out.println("The ID is = " + String.valueOf(selectedEntry.getId()));

                DiaryEntry.putExtra("id", String.valueOf(selectedEntry.getId()));
                DiaryEntry.putExtra("bookTitle", selectedEntry.getBookTitle());
                DiaryEntry.putExtra("dateTime", selectedEntry.getDateTime());
                DiaryEntry.putExtra("rating", selectedEntry.getRating());
                DiaryEntry.putExtra("pagesRead", selectedEntry.getPagesRead());

                startActivity(DiaryEntry);
            }
        });

        searchEntries = (EditText) findViewById(R.id.editSearch);
        searchButton = (Button) findViewById(R.id.searchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEntry();
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

    private void populateUsersList() {
        // Construct the data source
        helper = new MyDbAdapter(this);
        helper.populateDiaryEntries();
        ArrayList<Diary> arrayOfDiaries = Diary.getDiaryArrayList();
        // Create the adapter to convert the array to views
        DiaryAdapter adapter = new DiaryAdapter(this, arrayOfDiaries);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvEntries);

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    private void searchEntry() {

        // Set the value to be compared
        String searchedBookName = searchEntries.getText().toString();
        System.out.println("The searched book name is: ("+searchedBookName+")");

        if (searchedBookName.isEmpty()) {

            ArrayList<Diary> arrayOfDiaries = Diary.getDiaryArrayList();
            // Create the adapter to convert the array to views
            DiaryAdapter adapter = new DiaryAdapter(this, arrayOfDiaries);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.lvEntries);

            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
        }
        else if (!searchedBookName.isEmpty()) {

            ArrayList<Diary> arrayOfDiaries = Diary.getDiaryArrayList();
            //Sort through arrayOfDiaries and retrieve objects that match searchedBookName
            ArrayList<Diary> searchedDiaries = new ArrayList<Diary>();
            for (Diary arrayOfDiary : arrayOfDiaries) {
                if (arrayOfDiary.getBookTitle().equals(searchedBookName)) {
                    searchedDiaries.add(arrayOfDiary);
                }
            }
            // Create the adapter to convert the array to views
            DiaryAdapter adapter = new DiaryAdapter(this, searchedDiaries);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.lvEntries);

            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);

        }

    }



    }



