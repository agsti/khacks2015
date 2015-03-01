package org.khacks.singandlearn.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iain on 2/28/15.
 */
abstract class SingToLearnOpenHelper extends SQLiteOpenHelper {
    protected final String DATABASE_NAME;
    protected final int DATABASE_VERSION;
    protected final String TABLE_CREATE;
    protected final String TABLE_NAME;
    protected final Context context;


    public SingToLearnOpenHelper(Context context, String table_name, String table_create, String songsDatabaseName, int songsDatabaseVersion) {
        super(context, songsDatabaseName, null, songsDatabaseVersion);
        this.context = context;
        TABLE_NAME = table_name;
        TABLE_CREATE = table_create;
        DATABASE_NAME = songsDatabaseName;
        DATABASE_VERSION = songsDatabaseVersion;
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
