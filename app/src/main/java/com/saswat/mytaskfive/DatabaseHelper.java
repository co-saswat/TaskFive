package com.saswat.mytaskfive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Detail_Info.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "information";
    private static final String COL_USER_ID = "id";
    private static final String COL_USER_NAME = "user_name";
    private static final String COL_USER_EMAIL = "user_email";
    private static final String COL_USER_PHONE = "user_phone";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_NAME + " TEXT, " +
                COL_USER_EMAIL + " TEXT, " +
                COL_USER_PHONE + " INTEGER);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addDataToDatabase(String user_name, String user_email, long user_phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USER_NAME, user_name);
        cv.put(COL_USER_EMAIL, user_email);
        cv.put(COL_USER_PHONE, user_phone);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor retriveDataToDatabase() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dblite = this.getReadableDatabase();
        Cursor cursor = null;
        if (dblite != null) {
            cursor = dblite.rawQuery(query, null);
        }
        return cursor;
    }
}
