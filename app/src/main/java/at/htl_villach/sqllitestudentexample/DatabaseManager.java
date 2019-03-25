package at.htl_villach.sqllitestudentexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pupil on 3/19/19.
 */

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager( Context context ) {
        this.context = context;
    }

    public DatabaseManager open() throws SQLException{
        this.dbHelper = new DatabaseHelper(this.context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        this.dbHelper.close();
    }

    public void insert( String firstName, String lastName ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FIRSTNAME, firstName);
        contentValues.put( DatabaseHelper.LASTNAME, lastName);
        database.insert( DatabaseHelper.TABLE_NAME, null, contentValues );
    }

    public Cursor fetch(){
        String[] columns = new String[]{ DatabaseHelper.ID, DatabaseHelper.FIRSTNAME, DatabaseHelper.LASTNAME};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null );

        if( cursor != null ){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update( long id, String firstName, String lastName ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FIRSTNAME, firstName);
        contentValues.put( DatabaseHelper.LASTNAME, lastName);
        int i = database.update( DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.ID + " = " + id, null );
        return i;
    }

    public void delete( long id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + " ="+id, null);
    }

}
