package com.example.androidb3.features.database.data;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidb3.features.classSelection.data.dao.ClassDao;
import com.example.androidb3.features.database.model.exception.DatabaseCreationException;
import com.example.androidb3.features.studentSelection.data.dao.StudentDao;
import com.example.androidb3.features.studentSelection.data.entity.StudentEntity;
import com.example.androidb3.features.yearSelection.data.dao.YearDao;
import com.example.androidb3.features.classSelection.data.entity.ClassEntity;
import com.example.androidb3.features.yearSelection.data.entity.YearEntity;

@Database(entities = {ClassEntity.class, YearEntity.class, StudentEntity.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;
    public abstract YearDao yearDao();
    public abstract ClassDao classDao();
    public abstract StudentDao studentDao();

    protected AppDatabase() {}

    public static synchronized AppDatabase getInstance() throws IllegalStateException{
        if(instance == null) throw new IllegalStateException("Database not created, call createDatabase() first");
        return instance;
    }

    public static void createDatabase(Context context) throws DatabaseCreationException{ //Database path: /data/user/0/com.example.androidb3/databases/students_database
        try{
            if(instance != null) throw new DatabaseCreationException("The database is already created.");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "students_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        } catch (DatabaseCreationException e) {
            throw e;
        } catch (Exception e){
            throw new DatabaseCreationException("The database couldn't be created, please check the configuration.", e);
        }
    }
}
