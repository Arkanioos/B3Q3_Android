package maxm.androidb3.androidb3.features.database.data;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import maxm.androidb3.androidb3.features.classSelection.data.dao.ClassDao;
import maxm.androidb3.androidb3.features.database.model.exception.DatabaseCreationException;
import maxm.androidb3.androidb3.features.note.data.dao.NoteDao;
import maxm.androidb3.androidb3.features.note.data.entity.NoteEntity;
import maxm.androidb3.androidb3.features.note.model.Note;
import maxm.androidb3.androidb3.features.student.data.dao.StudentDao;
import maxm.androidb3.androidb3.features.student.data.entity.StudentEntity;
import maxm.androidb3.androidb3.features.test.data.dao.TestDao;
import maxm.androidb3.androidb3.features.test.data.entity.TestEntity;
import maxm.androidb3.androidb3.features.test.model.Test;
import maxm.androidb3.androidb3.features.year.data.dao.YearDao;
import maxm.androidb3.androidb3.features.classSelection.data.entity.ClassEntity;
import maxm.androidb3.androidb3.features.year.data.entity.YearEntity;

@Database(entities = {ClassEntity.class, YearEntity.class, StudentEntity.class, TestEntity.class, NoteEntity.class},
        version = 17)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;
    public abstract YearDao yearDao();
    public abstract ClassDao classDao();
    public abstract StudentDao studentDao();
    public abstract TestDao testDao();
    public abstract NoteDao noteDao();

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

            NoteDao dao = getInstance().noteDao();
            for(NoteEntity entity : dao.getAll()){
                //Note note = entity.toModel();
                Log.d("TEST_DB", "RETRIEVED : " + entity.getId() + " - " + entity.getTestId());
            }

            TestDao tdao = getInstance().testDao();
            for(TestEntity entity : tdao.getAll()){
                //Note note = entity.toModel();
                Log.d("TEST_DB", "RETRIEVED : " + entity.getId() + " - " + entity.getParentId());
            }

        } catch (DatabaseCreationException e) {
            throw e;
        } catch (Exception e){
            throw new DatabaseCreationException("The database couldn't be created, please check the configuration.", e);
        }
    }
}
