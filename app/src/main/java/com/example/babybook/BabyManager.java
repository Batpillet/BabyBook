package com.example.babybook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BabyManager {

    private static final String TABLE_NAME = "baby";
    public static final String KEY_ID_BABY="id_baby";
    public static final String KEY_NAME_BABY="name_baby";
    public static final String KEY_LASTNAME_BABY = "lastName_baby";
    public static final String KEY_DATE_BABY = "date_baby";
    public static final String CREATE_TABLE_BABY = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_BABY+" INTEGER primary key," +
            " "+KEY_NAME_BABY+" TEXT" +
            " "+KEY_LASTNAME_BABY+ "TEXT" +
<<<<<<< HEAD
            " "+KEY_DATE_BABY + "INTEGER" +
=======
            " "+KEY_DATE_BABY+ "INTEGER" +
>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public BabyManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addBaby(Baby baby) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
<<<<<<< HEAD
        values.put(KEY_ID_BABY, baby.getId_baby());
=======
>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344
        values.put(KEY_NAME_BABY, baby.getName_baby());
        values.put(KEY_LASTNAME_BABY, baby.getLastName_baby());
        values.put(KEY_DATE_BABY, baby.getDate_baby());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modBaby(Baby baby) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_BABY, baby.getName_baby());

        String where = KEY_ID_BABY+" = ?";
        String[] whereArgs = {baby.getId_baby()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int suppBaby(Baby baby) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_BABY+" = ?";
        String[] whereArgs = {baby.getId_baby()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Baby getBaby(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Baby a=new Baby(0,"", "", 1998);

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_BABY+"="+id, null);
        if (c.moveToFirst()) {
            a.setId_baby(c.getInt(c.getColumnIndex(KEY_ID_BABY)));
            a.setName_baby(c.getString(c.getColumnIndex(KEY_NAME_BABY)));
            a.setLastName_baby(c.getString(c.getColumnIndex(KEY_LASTNAME_BABY)));
            a.setDate_baby(c.getInt(c.getColumnIndex(KEY_DATE_BABY)));
            c.close();
        }
<<<<<<< HEAD
=======

>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344
        return a;
    }

    public Cursor getBaby() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
}
