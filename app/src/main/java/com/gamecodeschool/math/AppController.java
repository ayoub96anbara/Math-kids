package com.gamecodeschool.math;

import android.app.Application;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.gamecodeschool.math.dataBase.DBHelper;

public class AppController extends Application {
    private static AppController mInstance;
    private DBHelper databaseManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        databaseManager = new DBHelper(mInstance);
// Initialize your Database here
        SQLiteDatabase db = databaseManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(databaseManager.KEY_LEVEL,1);
        values.put(databaseManager.KEY_MIN_INT, 0);
        values.put(databaseManager.KEY_MAX_INT, 11);
        values.put(databaseManager.KEY_MOYENNE, 0);

        db.insert("gamer", null, values);
db.close();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}

