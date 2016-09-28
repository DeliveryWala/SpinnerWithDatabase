package com.example.arif.spinnerwithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arif on 26/09/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentinfo_database";
    private static final String TABLE_NAME = "student_table";
    private static final int VERSION = 3;
    private static final String UID = "_id";
    private static final String NAME = "name";
    private static final String SUBJECT = "subject";
    private static final String TOPIC = "topic";
    private Context context;
    ContentValues cValues;
    SQLiteDatabase db;


    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            SUBJECT + " TEXT, " +
            TOPIC + " TEXT );";
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Message.message(context, "Constructor called");
        this.context = context;
    }


    //CREATE TABLE

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            Message.message(context, "onCreate called");
        } catch (SQLException e) {
            Message.message(context, "" + e);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(DROP_TABLE);
            Message.message(context, "onUpgrade called");
            onCreate(db);
        } catch (SQLException e) {
            Message.message(context, "" + e);


        }
    }

    public long insert(String name, String subject, String topics) {
        cValues = new ContentValues();
        db = this.getWritableDatabase();
        cValues.put(NAME, name);
        cValues.put(SUBJECT, subject);
        cValues.put(TOPIC, topics);
        long id = db.insert(TABLE_NAME, null, cValues);
        return id;

    }
    public List<String> getNameLabels()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        List<String> name=new ArrayList<>();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_NAME,null);
        if (cursor.moveToFirst()) {
        do {
            name.add(cursor.getString(1));
        } while (cursor.moveToNext());
    }
        cursor.close();
        db.close();
        return name;

    }
    public List<String> getSubjectLabels(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        List<String> subject=new ArrayList<>();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_NAME+" where "+NAME+" = '"+name+"';",null);
        if (cursor.moveToFirst()) {
            do {
                subject.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return subject;

    }
    public String[] getTopics(String name,String subject)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] topics= new String[20];
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_NAME+" where "+NAME+" = '"+name+"' AND "+SUBJECT+" = '"+subject+"';",null);
        if(cursor.moveToFirst())
        {
            for(int i=0;cursor.moveToNext();i++)
            {
                topics[i]=cursor.getString(3);
            }
        }
        return topics;

    }
}
