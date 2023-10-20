package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDbAdapter {
    myDbHelper myhelper;

    public MyDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public void insertData(String bookTitle, String date, String rating, String pagesRead)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.BOOKTITLE, bookTitle);
        contentValues.put(myDbHelper.DATE, date);
        contentValues.put(myDbHelper.RATING, rating);
        contentValues.put(myDbHelper.PAGESREAD, pagesRead);

        dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);

    }

    public void updateData(String id, String bookTitle, String date, String rating, String pagesRead)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.UID, id);
        contentValues.put(myDbHelper.BOOKTITLE, bookTitle);
        contentValues.put(myDbHelper.DATE, date);
        contentValues.put(myDbHelper.RATING, rating);
        contentValues.put(myDbHelper.PAGESREAD, pagesRead);
        dbb.update(myDbHelper.TABLE_NAME, contentValues, "_id = ?", new String[] {id});

    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();

        return db.delete(myDbHelper.TABLE_NAME, "_id = ?", new String[] { id });

    }




    public void populateDiaryEntries()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.BOOKTITLE,myDbHelper.DATE,myDbHelper.RATING,myDbHelper.PAGESREAD};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME,columns, null, null, null, null, null);

        Diary.diaryArrayList.clear();

        while (cursor.moveToNext())
        {
            int cid = cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String bookTitle = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.BOOKTITLE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.DATE));
            String rating = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.RATING));
            String pagesRead = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PAGESREAD));

            Diary diary = new Diary(cid, bookTitle, date, rating, pagesRead);
            Diary.diaryArrayList.add(diary);
        }

    }





    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "Diary";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String BOOKTITLE = "BookTitle";
        private static final String DATE = "Date";
        private static final String RATING = "Rating";
        private static final String PAGESREAD = "PagesRead";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+BOOKTITLE+" VARCHAR(255) ,"+DATE+" VARCHAR(255) ,"+RATING+" VARCHAR(255) ,"+PAGESREAD+" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, ""+e);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, ""+e);
            }
        }
    }
}


