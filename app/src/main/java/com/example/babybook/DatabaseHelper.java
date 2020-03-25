package com.example.babybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "baby.db";


    // Table baby
    private static final String DB_TABLE = "baby_table";

    //Columns
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME+ " TEXT" + ")";



    //Table croissance
    public static final String BABY_TAILLE = "taille";
    public static final String BABY_TEMPS = "temps";
    public static final String BABY_CLE = "cle";


    public static final String TABLE_NAME_CROISSANCE = "Croissance";


    public static final String CREATE_TABLE_CROISSANCE =
            "CREATE TABLE " + TABLE_NAME_CROISSANCE + " (" +
                    BABY_CLE + " TEXT PRIMARY KEY, " +
                    BABY_TAILLE + " INTEGER, " +
                    BABY_TEMPS + " INTEGER);";




    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);


    }


    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_CROISSANCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

        //Table Baby
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);

        //table coissance
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CROISSANCE);

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
