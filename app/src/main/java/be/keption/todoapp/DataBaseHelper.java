package be.keption.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";
    public static final String TABLE_NAME = "data";
    public static final String ID_COL = "_id";
    public static final String TASK_COL = "task";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY,"
                + TASK_COL + " TEXT"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int insert(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(TASK_COL, task);
        long rawId = db.insert(TABLE_NAME, null, value);
        return 1;
    }

    public int delete(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        long rawId = db.delete(TABLE_NAME, task, new String[]{});
        return 1;
    }

    public ArrayList<String> getData() {
        ArrayList<String> taskList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String statement = "SELECT * FROM "+TABLE_NAME;
        Cursor c = db.rawQuery(statement,null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String dir = c.getString(c.getColumd"nIndex(TASK_COL));
                    taskList.add(dir);
                }while (c.moveToNext());
            }
        }
        c.close();
        return taskList;
    }
}
