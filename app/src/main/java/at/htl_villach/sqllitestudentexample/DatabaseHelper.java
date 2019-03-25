package at.htl_villach.sqllitestudentexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by pupil on 3/19/19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "STUDENTS1";
    // Table columns
    public static final String ID = "_id";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String DB_NAME = "HTL_VILLACH_STUDENTS.DB";
    public static final int DB_VERSION =1;

    private static final String CREATE_TABLE_STUDENT = "create table "+TABLE_NAME+" ("+ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRSTNAME + " TEXT NOT NULL, "
            + LASTNAME + " TEXT NOT NULL); ";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHelper(Context context ){
        super( context, DB_NAME, null, DB_VERSION );


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
