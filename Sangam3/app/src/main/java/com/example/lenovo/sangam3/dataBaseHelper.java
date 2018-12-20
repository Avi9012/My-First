package com.example.lenovo.sangam3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.FontsContract;

import java.util.ArrayList;
import java.util.List;


public class dataBaseHelper extends SQLiteOpenHelper {

    //DATABASE VERSION
    private static final int DATABASE_VERSION = 1;

    //DATABASE NAME
    private static final String DATABASE_NAME = "USER_MANAGER.db";

    //DATABASE TABLE NAME
    private static final String TABLE_USER = "user";

    //COLUMNS IN DATABASE TABLE
    private static final String COLUMN_1 = "USER_ID";
    private static final String COLUMN_2 = "USER_NAME";
    private static final String COLUMN_3 = "USER_EMAIL";
    private static final String COLUMN_4 = "USER_PASS";

    //CREATE TABLE SQL QUERY
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_2 + " TEXT," + COLUMN_3 + " TEXT," + COLUMN_4 + " TEXT" + ")";

    //CREATE DROP TABLE SQL QUERY
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public dataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop table if exists
        db.execSQL(DROP_USER_TABLE);

        //create table again
        onCreate(db);
    }
    /**
     * THIS METHOD IS CREATE USER RECORD
     */
    public void addUser(User banda)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COLUMN_2, banda.getName());
        contentvalues.put(COLUMN_3, banda.getEmail());
        contentvalues.put(COLUMN_4, banda.getPassword());

        //inserting row
        db.insert(TABLE_USER, null, contentvalues);
        db.close();
    }
    /**
     * THIS METHOD IS TO FETCH USERS AND RETURN THE LIST OF USER
     *
     */
    //public List<User> getAll()
    //{
      //  String [] columns = {
        //        COLUMN_1,
        //          COLUMN_2,
        //        COLUMN_3,
        //        COLUMN_4
        //};

        // SORTING ORDER
        //String SortedOrder = COLUMN_1 + "ASC";
        //List<User> UserList = new ArrayList<User>();
        //SQLiteDatabase db = this.getReadableDatabase();

        //Cursor cursor = db.query(TABLE_USER, columns, null, null, null, null, SortedOrder);

        //TRAVERSING THROUGH ALL ROWS
        //if(cursor.moveToFirst())
        //{
          //  do
            //{
            //    User user=  new User();
            //    user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_1))));
            //   user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_2)));
            //    user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_3)));
            //    user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_4)));
            //    UserList.add(user);
            //}while(cursor.moveToNext());
        //}
        //cursor.close();

        //db.close();

        //return UserList;
     //}

    public void Update(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_2, user.getName());
        values.put(COLUMN_3, user.getEmail());
        values.put(COLUMN_4, user.getPassword());

        db.update(TABLE_USER, values, COLUMN_1 + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();

    }

    public void delete(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_1 + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String email)
    {
        String [] column = {COLUMN_1};
        SQLiteDatabase db = this.getReadableDatabase();

        String basis = COLUMN_3 + " = ?";

        String [] selecArg = {email};
        Cursor cursor = db.query(TABLE_USER, column, basis, selecArg, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password)
    {
        String [] column = {COLUMN_1};
        SQLiteDatabase db = this.getReadableDatabase();

        String basis = COLUMN_3 + " = ?" + " AND " + COLUMN_4 + " = ?";

        String [] selecArg = {email, password};
        Cursor cursor = db.query(TABLE_USER, column, basis, selecArg, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }
}
