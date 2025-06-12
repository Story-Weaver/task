package by.roman.test_app.business_logic.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class SQLiteDatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "radio_app.db";
    private static final int DATABASE_VERSION = 1;
    public SQLiteDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(ModelDao.CREATE_TABLE_MODEL);
    }
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ModelDao.TABLE_MODEL);
        onCreate(db);
    }
}
