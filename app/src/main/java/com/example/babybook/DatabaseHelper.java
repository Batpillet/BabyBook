package com.example.babybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "baby.db";
    private static final String DB_TABLE = "baby_table";

    //Columns
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME+ " TEXT" + ")";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);

        onCreate(sqLiteDatabase);
    }

    //Method to insert data
    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; //if result = -1, n'insere pas la donnée
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public ArrayList getAllrecord(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DB_TABLE, null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
        }
        return arrayList;
    }
}
