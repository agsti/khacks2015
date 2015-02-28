package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iain on 2/28/15.
 */
abstract class SingToLearnOpenHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "singtolearn";
    protected static final int DATABASE_VERSION = 2;
    protected static String TABLE_CREATE;
    protected static String TABLE_NAME;
    protected final Context context;


    public SingToLearnOpenHelper(Context context, String table_name, String table_create) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        TABLE_NAME = table_name;
        TABLE_CREATE = table_create;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP " + TABLE_NAME + ";");
        db.execSQL(TABLE_CREATE);
    }

    public void onSetup() {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        onSetup();
    }


}
