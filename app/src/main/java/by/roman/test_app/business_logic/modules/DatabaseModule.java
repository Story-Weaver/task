package by.roman.test_app.business_logic.modules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Singleton;

import by.roman.test_app.business_logic.data.database.ModelDao;
import by.roman.test_app.business_logic.data.database.SQLiteDatabaseManager;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public SQLiteOpenHelper provideDatabase(@ApplicationContext Context context) {
        return new SQLiteDatabaseManager(context);
    }
    @Provides
    @Singleton
    public SQLiteDatabase provideWritableDatabase(SQLiteOpenHelper helper) {
        return helper.getWritableDatabase();
    }
    @Provides
    @Singleton
    public ModelDao provideModelDao(SQLiteDatabase db) {
        return new ModelDao(db);
    }
}
