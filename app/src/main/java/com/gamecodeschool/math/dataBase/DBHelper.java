package com.gamecodeschool.math.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gamecodeschool.math.Gamer;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mygamer.db";
    private static final int DB_VERSION = 1;
    public static final String KEY_LEVEL = "level";
    public static final String KEY_MIN_INT = "min_int";
    public static final String KEY_MAX_INT = "max_int";
    public static final String KEY_MOYENNE = "moyenne";
    public static final String TABLE_GAMER = "gamer";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table " + TABLE_GAMER + "(" + KEY_LEVEL + " integer primary key, " + KEY_MIN_INT + " integer, " + KEY_MAX_INT + " integer, " + KEY_MOYENNE + " integer)";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete_query = "DROP Table " + TABLE_GAMER + "";
        db.execSQL(delete_query);
// Création de la nouvelle structure.
        onCreate(db);
    }

    public ArrayList<Gamer> getArrayList() {
        SQLiteDatabase sqLite = getReadableDatabase();
        String select_query = "select * from " + TABLE_GAMER;
        Cursor cursor = sqLite.rawQuery(select_query, null);
        ArrayList<Gamer> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int lv = cursor.getInt(cursor.getColumnIndex(KEY_LEVEL));
//                int min = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_MIN_INT));
//                int max = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_MAX_INT));
                int moy = cursor.getInt(cursor.getColumnIndex(KEY_MOYENNE));
                Gamer gamer = new Gamer(moy, lv);
                arrayList.add(gamer);
                //Toast.makeText(this, "level="+lv+"min="+min+"max="+max+"moye= "+moy, Toast.LENGTH_LONG).show();

            } while (cursor.moveToNext());
        }
        return arrayList;
    }

}