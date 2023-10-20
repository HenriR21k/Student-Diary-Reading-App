package com.example.assignment;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DiaryAdapter extends ArrayAdapter<Diary> {
    public DiaryAdapter(Context context, ArrayList<Diary> entries) {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diary_entry, parent, false);
        }

        // Get the data item for this position
        Diary entry = getItem(position);

        // Lookup view for data population
        TextView tvBookName = (TextView) convertView.findViewById(R.id.tvBookName);

        // Populate the data into the template view using the data object
        tvBookName.setText(entry.getBookTitle());

        // Return the completed view to render on screen
        return convertView;
    }
}
