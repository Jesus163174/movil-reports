package com.example.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "estasix2";
    private static final String TABLE = "table_users";
    private static final String KEY_ID = "_id";
    ArrayList<String> lista = new ArrayList<String>();

    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    public void deleteDataUser(String id){
        /*String QUERY = " DELETE FROM " + TABLE + " where " + KEY_EMAIL + " = " + email;

        db.execSQL(QUERY);*/
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, KEY_ID + "=?", new String[]{id});
    }

    public ArrayList getDataUser(int indice){
        lista.clear();
        String QUERY = "SELECT * FROM "+ TABLE;
        String totalData = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY,null);

        if (cursor.moveToFirst()){
            do{
                String ID    = cursor.getString(cursor.getColumnIndex(KEY_ID));
                lista.add(ID);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

        return lista;
    }

    public void insertUser (String _id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ID,_id);

        long newRodId = db.insert(TABLE, null, cValues);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
