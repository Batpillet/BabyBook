package com.example.babybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.example.babybook.DatabaseHelper;

import static com.example.babybook.DatabaseHelper.TABLE_NAME_CROISSANCE;
import static com.example.babybook.DatabaseHelper.BABY_TAILLE;
import static com.example.babybook.DatabaseHelper.BABY_TEMPS;




import java.util.ArrayList;
import java.util.List;

public class CroissanceDAO {


    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    Context context;
    private String[] allColumns = {
            DatabaseHelper.BABY_TAILLE,
            DatabaseHelper.BABY_TEMPS,
            DatabaseHelper.BABY_CLE
    };

    public CroissanceDAO(Context context){
        this.context = context;
        dbHelper = DatabaseHelper.getHelper(context);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<String> getAllTaille(){
        List<String> taille = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_CROISSANCE,
                new String[]
                        {
                                DatabaseHelper.BABY_TAILLE
                        },
                DatabaseHelper.BABY_CLE, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            taille.add(cursor.getString(0));
        }
        return taille;
    }

    // getting a list of all croissance to show in mainactivity
    public List<Croissance> getAllCroissance() {
        List<Croissance> croissance = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_CROISSANCE,

                new String[] {
                        DatabaseHelper.BABY_TAILLE,
                        DatabaseHelper.BABY_TEMPS,
                        DatabaseHelper.BABY_CLE},
                DatabaseHelper.BABY_CLE, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Croissance myCroissance = new Croissance();
            myCroissance.setTaille(cursor.getDouble(0));
            myCroissance.setTemps(cursor.getInt(1));
            myCroissance.setCle(cursor.getInt(2));
            croissance.add(myCroissance);
        }
        return croissance;
    }

    public void createCroissance(Croissance myCroissance) {

        ContentValues values = new ContentValues();
        values.put(BABY_TAILLE, myCroissance.getTaille());
        values.put(BABY_TEMPS, myCroissance.getTemps());

        // insert row
        this.database.insert(TABLE_NAME_CROISSANCE, null, values);
    }


}
